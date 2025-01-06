package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessString {
    private String input;
    private char[][] grid;
    private Map<Integer, List<Integer>> printingRuleMap;
    private List<List<Integer>> printingOrderList;
    public ProcessString(String fileContents){
        input = fileContents;
    }

    public void pageRules(){
        String[] lines = input.split(System.lineSeparator());
        printingRuleMap = new HashMap<>();
        printingOrderList = new ArrayList<>();
        for(int i = 0; i < lines.length; i++){
            if(lines[i].contains("|")){
                String[] printingRules = lines[i].split("\\|");
                int first = Integer.parseInt(printingRules[0]);
                List<Integer> subsequents = printingRuleMap.getOrDefault(first, null);
                if(subsequents == null){
                    List<Integer> second = new ArrayList<>(Integer.parseInt(printingRules[1]));
                    printingRuleMap.put(first, second);
                } else{
                    subsequents.add(Integer.parseInt(printingRules[1]));
                }
            }
            if(lines[i].contains(",")){
                List<Integer> updateOrder = new ArrayList<>();
                String[] printingOrder = lines[i].split(",");
                for(int n = 0; n < printingOrder.length; n++){
                    updateOrder.add(Integer.parseInt(printingOrder[n]));

                }
                printingOrderList.add(updateOrder);
            }
        }
        checkOrdering();
    }

    private void checkOrdering(){
        int sum = 0;
        for(int i = 0; i < printingOrderList.size(); i++){
            boolean correct = true;
            for(int n = 0; n < printingOrderList.get(i).size(); n++){
                List<Integer> updateList = printingRuleMap.get(printingOrderList.get(i).get(n));
                for(int p = 0; p < (printingOrderList.get(i).size() - (n + 1)); p++){
                    if(updateList == null || !updateList.contains(printingOrderList.get(i).get(p))){
                        correct = false;
                    }
                }
            }
            if(correct){
                sum = sum + printingOrderList.get(i).get(printingOrderList.get(i).size() / 2);
            }
        }
        System.out.println(sum);
    }
}
