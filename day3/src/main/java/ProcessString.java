package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessString {
    private String input;
    Map<Integer, String> mult;
    List<Integer> doStr;
    List<Integer> dont;
    public ProcessString(String fileContents){
        input = fileContents;
        mult = new TreeMap<>();
        doStr = new ArrayList<>();
        dont = new ArrayList<>();
    }

    public void searchString(){
        Pattern pattern = Pattern.compile("mul\\(\\d{1,4},\\d{1,4}\\)");
        Pattern pDo = Pattern.compile("do\\(\\)");
        Pattern pDont = Pattern.compile("don't\\(\\)");
        Matcher matcher = pattern.matcher(input);
        Matcher mDo = pDo.matcher(input);
        Matcher mDont = pDont.matcher(input);
        while(matcher.find()){
            mult.put(matcher.start(), matcher.group());
            System.out.println(matcher.group());
        }
        while(mDo.find()){
            mult.put(mDo.start(), mDo.group());
        }
        while(mDont.find()){
            mult.put(mDont.start(), mDont.group());
        }
        multiply();
    }

    public void multiply(){
        int sum = 0;
        int doIndex = 0;
        int dontIndex = 0;
        boolean conditional = true;
        for(Map.Entry<Integer, String> entry : mult.entrySet()){
            if(entry.getValue().equals("don't()")){
                conditional = false;
            }
            if(entry.getValue().equals("do()")){
                conditional = true;
            }

            if(conditional && entry.getValue().contains("mul")){
                String[] splitArr = entry.getValue().split(",");
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
        }
        System.out.println(sum);
    }
}
