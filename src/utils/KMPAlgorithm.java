package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * KMP 字符串匹配算法
 *  http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 *
 */

public class KMPAlgorithm {


    public static void main(String[] args) {

        String a = "ABCDABD";
        String b = "ABCC";
        System.out.println(kmp(a,b));

    }

    public static boolean kmp(String a,String b){

        char[] chara = a.toCharArray();
        char[] charb = b.toCharArray();

        int[] table = calStringMatch(b);

        int places = 0;
        first:for (int i = 0; i < chara.length; i++) {
            for (int j = i; j < charb.length; j++) {
                if (chara[i] == charb[j]){
                    places ++;
                    if (places == charb.length){
                        return true;
                    }
                    continue first;
                } else {
                    i += places - table[j];
                    places = 0;
                }
                if (places == charb.length){
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 计算匹配表
     * @param v
     * @return
     */
    public static int[] calStringMatch(String v){
        if (StringUtils.isBlank(v)){
            throw new IllegalArgumentException("string is blank");
        }
        int[] result = new int[v.length()];


        for (int i = 0; i < v.length(); i++) {

            String temp = v.substring(0,i + 1);

            List<String> prefixs = new ArrayList<>();
            List<String> suffixs = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                String prefix = temp.substring(0,j);
                prefixs.add(prefix);
                String suffix = temp.substring(temp.length() - j,temp.length());
                suffixs.add(suffix);
            }
            List<String> intersection = prefixs.stream().filter(item -> suffixs.contains(item)).collect(Collectors.toList());
            if (!intersection.isEmpty()){
                result[i] = intersection.stream().findFirst().get().length();
            }
        }
        return result;
    }




}
