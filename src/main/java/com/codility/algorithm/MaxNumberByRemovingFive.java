package com.codility.algorithm;

public class MaxNumberByRemovingFive {

    public int maxNumber(int N) {
        String str = String.valueOf(N);
        int maxValue = Integer.MIN_VALUE;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '5') {
                var newStr = sb.append(str, 0, i).append(str.substring(i+1)).toString();
                maxValue = Math.max(maxValue, Integer.parseInt(newStr));
            }
            sb = new StringBuilder();
        }

        return maxValue;
    }
}
