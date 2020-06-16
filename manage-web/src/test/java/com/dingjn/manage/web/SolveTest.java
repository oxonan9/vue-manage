package com.dingjn.manage.web;

import org.junit.jupiter.api.Test;

/**
 * @Auther: dingjn
 * @Desc:
 */
public class SolveTest {

    /**
     * 给定一个有序数组和元素，找出下标.
     */
    @Test
    public void binarySearch() {
        int[] sortedArray = {2, 5, 7, 9, 15};
        int targetValue = 3;
        int index = binarySearch(sortedArray, targetValue);
        System.out.println(index);
    }

    // 二分搜索法:先和中间的比较，小就往前找，大就往后找
    public static int binarySearch(int[] sortedArray, int targetValue) {
        //最小坐标
        int first = 0;
        //最大坐标
        int last = sortedArray.length - 1;
        //当最小的坐标超过了最大的坐标说明已经找过一轮都没找到
        while (first <= last) {
            int mid = (first + last) / 2;  //  //计算中间的坐标

            if (targetValue < sortedArray[mid]) {
                last = mid - 1;      //如果目标值小于中间的值，就从前半部分开始找，最大的坐标则为中间的坐标-1
            } else if (targetValue > sortedArray[mid]) {
                first = mid + 1;  // //如果目标值小于中间的值，就从前后 部分开始找，最小的坐标则为中间的坐标+1
            } else {
                return mid;     // 如果目标值等于中间的值，说明找到了，直接返回
            }
        }

        return -1;    //如果没找到返回-1
    }
}
