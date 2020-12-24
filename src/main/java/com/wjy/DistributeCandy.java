package com.wjy;

/**
 * 分发糖果
 *
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例1：
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例2：
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 *
 * 方法一：两次遍历
 * 思路及解法
 *
 * 我们可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
 *
 * 左规则：当 ratings[i - 1] < ratings[i] 时，i 号学生的糖果数量将比 i - 1 号孩子的糖果数量多。
 *
 * 右规则：当 ratings[i] > ratings[i + 1] 时，i 号学生的糖果数量将比 i + 1 号孩子的糖果数量多。
 *
 * 我们遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。每个人最终分得的糖果数量即为这两个数量的最大值。
 *
 * 具体地，以左规则为例：我们从左到右遍历该数组，假设当前遍历到位置 i，如果有 ratings[i − 1] < ratings[i]，
 * 那么 i 号学生的糖果数量将比 i - 1 号孩子的糖果数量多，我们令 left[i] = left[i − 1] + 1 即可，否则我们令 left[i]=1。
 *
 * 在实际代码中，我们先计算出左规则 left 数组，在计算右规则的时候只需要用单个变量记录当前位置的右规则，同时计算答案即可。
 *
 *
 * @author wangjunyao
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年12月24日 17:11:00
 */
public class DistributeCandy {

    public static void main(String[] args) {
        DistributeCandy candy = new DistributeCandy();
        int[] ratings = new int[]{1,2,2};
        int candy1 = candy.candy(ratings);
        System.out.println(candy1);
    }

    public int candy(int[] ratings) {
        int candy = 0;
        //数组长度
        int len = ratings.length;
        //从左开始匹配 的结果
        int[] left = new int[len];
        for (int i = 0; i < len; i++){
            //第一个默认分配1个
            if (i > 0 && ratings[i] > ratings[i - 1]){
                left[i] = left[i - 1] + 1;
            }else{
                left[i] = 1;
            }
        }

        //从右开始匹配
        int right = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (i < len - 1 && ratings[i] > ratings[i + 1]){
                right++;
            }else {
                right = 1;
            }
            candy += Math.max(left[i], right);
        }
        return candy;
    }

}
