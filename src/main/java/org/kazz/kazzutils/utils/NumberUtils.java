package org.kazz.kazzutils.utils;

import java.util.TreeMap;

public class NumberUtils {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }


    public static int toInteger(String s) {
        int nums[] = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X' :
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }
        int sum = 0;
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] < nums[i+1])
                sum -= nums[i];
            else
                sum += nums[i];
        }
        return sum + nums[nums.length-1];
    }

    public static Integer getNumber(String str) {
        if (str.matches("\\d+")) {
            return Integer.parseInt(str);
        } else {
            return null;
        }
    }

}