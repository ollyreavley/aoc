package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessString {
    private String input;
    List<String> mult;
    public ProcessString(String fileContents){
        input = fileContents;
        mult = new ArrayList<>();
    }

    public void searchString(){
        Pattern pattern = Pattern.compile("mul\\(\\d{1,4},\\d{1,4}\\)");
        Pattern pDo = Pattern.compile("do()");
        Pattern pDont = Pattern.compile("don't()");
        Matcher matcher = pattern.matcher(input);
        
        while(matcher.find()){
            mult.add(matcher.group());
            System.out.println(matcher.group());
        }
        multiply();
    }

    public void multiply(){
        int sum = 0;
        for(int i = 0; i < mult.size(); i++){
            String[] splitArr = mult.get(i).split(",");
            Pattern p = Pattern.compile("\\d{1,4}");
            Matcher m1 = p.matcher(splitArr[0]);
            Matcher m2 = p.matcher(splitArr[1]);

            int m1Int = 0;
            int m2Int = 0;
            while(m1.find()){
                m1Int = Integer.parseInt(m1.group());
            }
            while(m2.find()){
                m2Int = Integer.parseInt(m2.group());
            }
            sum = sum + (m1Int * m2Int);
        }
        System.out.println(sum);
    }
}
