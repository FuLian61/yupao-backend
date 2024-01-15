-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    profile      varchar(512)                       null comment '个人简介',
    phone        varchar(128)                       null comment '电话',
    userPassword varchar(512)                       not null comment '密码',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    email        varchar(512)                       null comment '邮箱',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    planetCode   varchar(512)                       null comment '星球编号',
    tags         varchar(1024)                      null comment '标签 json 列表',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员'
)
    comment '用户';

alter table user add column tags varchar(1024) null comment '标签列表';

create table tag
(
    id           bigint auto_increment comment 'id'
        primary key,
    tagName     varchar(256)                       null comment '标签名称',
    userId      bigint                             null comment '用户id',
    parentId    bigint                             null comment '父标签',
    isParent    tinyint                            null comment '0 - 不是，1 - 父标签',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除'
)
    comment '标签';
