package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class KMPAlgorithm {


    public static void main(String[] args) {

        System.out.println(calStringMatch("ABCDABD"));
    }

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