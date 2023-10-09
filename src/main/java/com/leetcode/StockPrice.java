package com.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * https://leetcode.cn/problems/stock-price-fluctuation/description/?envType=daily-question&envId=2023-10-08
 */
public class StockPrice {
    TreeMap<Integer, Integer> priceHistory;
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    HashMap<Integer, Integer> priceCount;

    public StockPrice() {
        priceHistory = new TreeMap<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        priceCount = new HashMap<>();
    }

    public void update(int timestamp, int price) {
        if (priceHistory.containsKey(timestamp)) {
            int oldPrice = priceHistory.get(timestamp);
            priceCount.put(oldPrice, priceCount.get(oldPrice) - 1);
        }
        priceHistory.put(timestamp, price);
        maxHeap.add(price);
        minHeap.add(price);
        priceCount.put(price, priceCount.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return priceHistory.lastEntry().getValue();
    }

    public int maximum() {
        while (!maxHeap.isEmpty() && priceCount.get(maxHeap.peek()) == 0) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public int minimum() {
        while (!minHeap.isEmpty() && priceCount.get(minHeap.peek()) == 0) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
