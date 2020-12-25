package com.wjy;

/**
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 *
 * 示例1：
 * 输入：[2,3,4,5]   返回值：4
 *
 * 示例2：
 * 输入：[2,2,3,4,3]    返回值：3
 *
 * @author wangjunyao
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年12月24日 17:49:00
 */
public class StrMaxLength {

    public static void main(String[] args) {
        StrMaxLength strMaxLength = new StrMaxLength();
        int[] arr = new int[]{2,2,3,4,3};
        int i = strMaxLength.maxLength(arr);
        System.out.println(i);
    }

    public int maxLength(int[] arr){
        int maxLength = 1;
        for (int i = 0; i < arr.length; i++) {
            int temp = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != arr[j]){
                    temp++;
                }else {
                    temp = 1;
                    break;
                }
            }
            if (temp > maxLength){
                maxLength = temp;
            }
        }
        return maxLength;
    }
}
