package com.yupi.yupao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yupao.common.BaseResponse;
import com.yupi.yupao.common.ErrorCode;
import com.yupi.yupao.common.ResultUtils;
import com.yupi.yupao.exception.BusinessException;
import com.yupi.yupao.model.domain.User;
import com.yupi.yupao.model.request.UserLoginRequst;
import com.yupi.yupao.model.request.UserRegisterRequest;
import com.yupi.yupao.model.vo.UserVO;
import com.yupi.yupao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.yupi.yupao.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 * @Author 浮涟
 * @Since 2023/11/8
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:5173"})
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
//        return new BaseResponse<>(0,result,"ok");
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequst userLoginRequst, HttpServletRequest request){
        if(userLoginRequst == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        String userAccount = userLoginRequst.getUserAccount();
        String userPassword = userLoginRequst.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
//        return new BaseResponse<>(0,user,"ok");
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout( HttpServletRequest request){
        if(request == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username,HttpServletRequest request){
        // 是否管理员
        if(!userService.isAdmin(request)){
           throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> {
            user.setUserPassword(null);
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList){
        if(CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUsersByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    // todo
    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommendUsers(long pageSize, long pageNum,HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        // 如果有缓存，直接读缓存
        String redisKey = String.format("yupao:user:recommed:%s",loginUser.getId());
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Page<User> userPage = (Page<User>)valueOperations.get(redisKey);
        if (userPage != null){
            return ResultUtils.success(userPage);
        }
        // 无缓存，查数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userPage = userService.page(new Page<>(pageNum,pageSize),queryWrapper);
        // 写缓存
        try {
            valueOperations.set(redisKey,userPage,30000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("redis set key error",e);
        }
        return ResultUtils.success(userPage);
    }

    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user, HttpServletRequest request){
        // 1.校验参数是否为空
        if (user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);

        int result = userService.updateUser(user,loginUser);
        return ResultUtils.success(result);
    }


    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if(id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 获取最匹配的用户
     * @param num
     * @param request
     * @return
     */
    @GetMapping("/match")
    public BaseResponse<List<User>> matchUsers(long num, HttpServletRequest request){
        if(num <= 0 || num >= 20){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.matchUsers(num,loginUser));
    }


}
