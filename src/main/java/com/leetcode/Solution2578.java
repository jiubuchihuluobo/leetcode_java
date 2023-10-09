package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution2578 {
    List<Integer> numList;
    List<Integer> numList1;
    List<Integer> numList2;

    public Solution2578() {
        numList = new ArrayList<>();
        numList1 = new ArrayList<>();
        numList2 = new ArrayList<>();
    }


    public int splitNum(int num) {
        while (num != 0) {
            numList.add(num % 10);
            num = num / 10;
        }
        numList.sort(null);

        if (!numList.isEmpty() && numList.get(1) == 0) {
            int startIndex = binarySearchNotZero(numList);
            numList = numList.subList(startIndex, numList.size());
        }


        if (numList.size() == 1) {
            return numList.get(0);
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < numList.size(); i++) {
            if (i % 2 == 0) {
                numList1.add(numList.get(i));
                sb1.append(numList.get(i));
            } else {
                numList2.add(numList.get(i));
                sb2.append(numList.get(i));
            }
        }
        int num1 = Integer.parseInt(sb1.toString());
        int num2 = Integer.parseInt(sb2.toString());
        return num1 + num2;
    }

    public int binarySearchNotZero(List<Integer> numList) {
        int low = 0;
        int high = numList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (numList.get(mid) == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 如果low指向列表外,或者最终指向的值为0
        if (low >= numList.size() || numList.get(low) == 0) {
            return -1;
        }
        return low;
    }

    public static void main(String[] args) {
        Solution2578 solution2578 = new Solution2578();
        System.out.println(solution2578.splitNum(1001));
    }
}
