package com.yupi.yupao.service;

import com.yupi.yupao.utils.AlgorithUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 算法工具类测试
 */
public class AlgorithUtilsTest {

    /**
     * 编辑距离算法（用于计算相似的两个字符串）
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1,String word2){
        int n = word1.length();
        int m = word2.length();

        if(n * m == 0){
            return n + m;
        }
        int[][] d = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            d[0][j] = j;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = d[i - 1][j] + 1;
                int down = d[i][j - 1] + 1;
                int left_down = d[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)){
                    left_down += 1;
                }
                d[i][j] = Math.min(left,Math.min(down,left_down));
            }
        }
        return d[n][m];
    }

    @Test
    void test(){
        String str1 = "鱼皮是狗";
        String str2 = "鱼皮不是狗";
        String str3 = "鱼皮是鱼不是狗";
        int score1 = AlgorithUtils.minDistance(str1,str2);
        int score2 = AlgorithUtils.minDistance(str1,str3);
        System.out.println(score1);
        System.out.println(score2);
    }

    @Test
    void testCompareTags(){
        List<String> tagList1 = Arrays.asList("java", "大一", "男");
        List<String> tagList2 = Arrays.asList("java", "大一", "女");
        List<String> tagList3 = Arrays.asList("python", "大二", "女");
        int score1 = AlgorithUtils.minDistance(tagList1,tagList2);
        int score2 = AlgorithUtils.minDistance(tagList1,tagList3);
        System.out.println(score1);
        System.out.println(score2);
    }
}
