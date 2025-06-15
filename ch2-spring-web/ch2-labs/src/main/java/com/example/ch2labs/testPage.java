package com.example.ch2labs;

import java.util.Arrays;

public class testPage {
    public static void main(String[] args) {
        String num = "123456";
        String[] nums = num.split("");
        for (int i =0 ; i< nums.length; i++){
            System.out.println((Integer.parseInt(nums[i])));
            }
    }

}
