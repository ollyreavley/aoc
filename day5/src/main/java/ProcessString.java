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
                    List<Integer> second = new ArrayList<>();
                    second.add(Integer.parseInt(printingRules[1]));
                    printingRuleMap.put(first, second);
                } else{
                    subsequents.add(Integer.parseInt(printingRules[1]));
                    printingRuleMap.put(first, subsequents);
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
        correctOrdering();
    }

    private void checkOrdering(){
        int sum = 0;
        for(int i = 0; i < printingOrderList.size(); i++){
            int correct = 0;
            for(int n = 0; n < printingOrderList.get(i).size(); n++){
                List<Integer> updateOrder = printingRuleMap.get(printingOrderList.get(i).get(n));
                if(updateOrder != null){
                    for(int p = n + 1; p < printingOrderList.get(i).size(); p++){
                        if(updateOrder.contains(printingOrderList.get(i).get(p))){
                            correct++;
                        }
                    }
                }
            }
            int size = printingOrderList.get(i).size();
            if(correct == (size * (size + 1) / 2 - size)){
                sum = sum + printingOrderList.get(i).get(printingOrderList.get(i).size() / 2);
            }
        }
        System.out.println(sum);
    }

    private void correctOrdering(){
        List<Integer> incorrect = new ArrayList<>();
        for(int i = 0; i < printingOrderList.size(); i++){
            for(int n = 0; n < printingOrderList.get(i).size(); n++){
                List<Integer> updateOrder = printingRuleMap.get(printingOrderList.get(i).get(n));
                if(updateOrder != null){
                    for(int p = n + 1; p < printingOrderList.get(i).size(); p++){
                        if(!updateOrder.contains(printingOrderList.get(i).get(p))){
                            incorrect.add(i);
                        }
                    }
                }
            }
        }
        reOrder(incorrect);
        //System.out.println(sum);
    }

    private void reOrder(List<Integer> incorrect){
        List<Integer> newOrder = new ArrayList<>();
        for(int i : incorrect){
            newOrder = buildNewOrder(printingOrderList.get(i), newOrder);
            checkOrder(newOrder);
        }
    }

    private List<Integer> buildNewOrder(List<Integer> oldOrder, List<Integer> newOrder){
        for(int i = 0; i < oldOrder.size(); i++){
            List<Integer> subsequents = printingRuleMap.getOrDefault(oldOrder.get(i), null);
            if(subsequents == null){
                newOrder.add(oldOrder.get(i));
            }
        }
        return newOrder;
    }

    private boolean checkOrder(List<Integer> newOrder){
        for(int n = 0; n < newOrder.size(); n++){
            List<Integer> updateOrder = printingRuleMap.get(newOrder.get(n));
            if(updateOrder != null){
                for(int p = n + 1; p < newOrder.size(); p++){
                    if(!updateOrder.contains(newOrder.get(p))){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
