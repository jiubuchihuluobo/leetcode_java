package com.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/movement-of-robots/description/
 */
public class MovementOfRobots {
    int times;
    int totalDistance;
    Map<Integer, Character> directionMap;

    public MovementOfRobots() {
        times = 1;
        totalDistance = 0;
        directionMap = new HashMap<>();
    }

    public <K, V> Map<Integer, Character> arrayToMap(int[] keys, char[] values) {
        Map<Integer, Character> result = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            result.put(i, values[i]);
        }
        return result;
    }

    public int sumDistance(int[] nums, String s, int d) {
        char[] direction = s.toCharArray();

        directionMap = this.arrayToMap(nums, direction);
        Set<Integer> keySet = directionMap.keySet();

        Map<Integer, List<Integer>> countRobots = new HashMap<>();

        while (times <= d) {
            times++;

            for (Integer key : countRobots.keySet()) {
                if (countRobots.get(key).size() != 1) {
                    if (directionMap.get(countRobots.get(key).get(0)) == 'R' && directionMap.get(countRobots.get(key).get(0)) != 'L') {
                        directionMap.put(countRobots.get(key).get(0), 'L');
                    } else {
                        directionMap.put(countRobots.get(key).get(0), 'R');
                    }
                    if (directionMap.get(countRobots.get(key).get(1)) == 'R' && directionMap.get(countRobots.get(key).get(0)) != 'L') {
                        directionMap.put(countRobots.get(key).get(1), 'L');
                    } else {
                        directionMap.put(countRobots.get(key).get(1), 'R');
                    }
                }
            }

            countRobots.clear();

            for (Integer key : keySet) {
                if (directionMap.get(key) == 'R') {
                    nums[key] = nums[key] + 1;
                    if (!countRobots.containsKey(nums[key])) {
                        List<Integer> robotsLocation = new ArrayList<>();
                        robotsLocation.add(key);
                        countRobots.put(nums[key], robotsLocation);
                    } else {
                        countRobots.get(nums[key]).add(key);
                    }
                } else {
                    nums[key] = nums[key] - 1;
                    if (!countRobots.containsKey(nums[key])) {
                        List<Integer> robotsLocation = new ArrayList<>();
                        robotsLocation.add(key);
                        countRobots.put(nums[key], robotsLocation);
                    } else {
                        countRobots.get(nums[key]).add(key);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        List<List<Integer>> result = getIndexCombinationsOfTwo(nums.length);
        for (List<Integer> combination : result) {
            int index1 = combination.get(0);
            int index2 = combination.get(1);
            int distance = Math.abs(nums[index1] - nums[index2]);
            totalDistance += distance;
        }
        System.out.println(totalDistance);
        return totalDistance;
    }

    public static List<List<Integer>> getIndexCombinationsOfTwo(int length) {
        List<List<Integer>> result = new ArrayList<>();
        combine(length, 0, new ArrayList<>(), result);
        return result;
    }

    private static void combine(int length, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == 2) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < length; i++) {
            current.add(i);
            combine(length, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }


    public static void main(String[] args) {
        MovementOfRobots move = new MovementOfRobots();
        int result = move.sumDistance(new int[]{-2, 0, 2}, "RLL", 3);
        System.out.println(result);
    }
}
