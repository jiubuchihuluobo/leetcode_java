package com.leetcode;

import java.util.ArrayList;

/**
 * https://leetcode.cn/submissions/detail/472407398/
 */
public class StockSpanner {
    ArrayList<Integer> priceHistory;
    Integer count;

    public StockSpanner() {
        priceHistory = new ArrayList<>();
    }

    public int next(int price) {
        priceHistory.add(price);

        count = 1;

        for (int i = priceHistory.size() - 2; i >= 0; i--) {
            if (priceHistory.get(i) <= priceHistory.get(priceHistory.size() - 1)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
