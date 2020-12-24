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
