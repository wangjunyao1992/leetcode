package com.wjy;

import java.util.Arrays;

/**
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例1:
 *
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 *
 *
 * 示例 2:
 *
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 *
 *
 * 示例 3:
 *
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * @author wangjunyao
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年12月02日 13:49:00
 */
public class SpliceMaxNum {

    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 4, 6, 5};
        int[] nums2 = new int[]{9, 1, 2, 5, 8, 3};
        SpliceMaxNum spliceMaxNum = new SpliceMaxNum();
        int[] maxNum = spliceMaxNum.maxNum(nums1, nums2, 5);
        for (int i = 0; i < maxNum.length; i++) {
            System.out.print(maxNum[i] + " ");
        }
    }
    
    public int[] maxNum(int[] nums1, int[] nums2, int k){
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] maxSubSeq = new int[k];
        int start = Math.max(0, k - len2);
        int end = Math.min(k, len1);
        for (int i = start; i <= end; i++) {
            int[] maxSubSeq1 = maxSubSeq(nums1, i);
            int[] maxSubSeq2 = maxSubSeq(nums2, k - i);
            int[] curMaxSubSeq = merge(maxSubSeq1, maxSubSeq2);
            if (compare(curMaxSubSeq, 0, maxSubSeq, 0) > 0){
                System.arraycopy(curMaxSubSeq, 0, maxSubSeq, 0, k);
            }
        }
        return maxSubSeq;
    }

    /**
     * 返回已知数组nums的长度k的最大子数组
     * @param nums 已知数组
     * @param k  返回的数组长度
     * @return
     */
    public int[] maxSubSeq(int[] nums, int k){
        int[] maxSubSeq = new int[k];
        //数组长度
        int len = nums.length;
        //可以剩余的个数
        int remain = len - k;
        //maxSubSeq[]  下标
        int top = -1;
        //遍历数组
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            //从前往后，取出能跳过的个数中最大的那个值
            while (top >= 0 && remain > 0 && maxSubSeq[top] < num ){
                top--;
                remain--;
            }
            if (top < k - 1){
                maxSubSeq[++top] = num;
            }else {
                remain--;
            }
        }
        return maxSubSeq;
    }

    public int[] merge(int[] subSeq1, int[] subSeq2){
        int len1 = subSeq1.length;
        int len2 = subSeq2.length;
        if (len1 == 0){
            return subSeq2;
        }
        if (len2 == 0){
            return subSeq1;
        }
        int mergeLen = len1 + len2;
        int[] merged = new int[mergeLen];
        int subSeq1_index = 0;
        int subSeq2_index = 0;
        for (int i = 0; i < mergeLen; i++) {
            if (compare(subSeq1, subSeq1_index, subSeq2, subSeq2_index) > 0){
                merged[i] = subSeq1[subSeq1_index++];
            }else {
                merged[i] = subSeq2[subSeq2_index++];
            }
        }
        return merged;
    }

    public int compare(int[] nums1, int index1, int[] nums2, int index2){
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (index1 < len1 && index2 < len2){
            int difference = nums1[index1] - nums2[index2];
            if (difference != 0){
                return difference;
            }
            index1++;
            index2++;
        }
        return (len1 - index1) - (len2 - index2);
    }
}
