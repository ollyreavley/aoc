package main.java;

import java.util.*;

public class Part2 {
    private String input;
    private char[][] grid;
    private Map<Integer, List<Integer>> printingRuleMap;
    private List<Map<Integer, List<Integer>>> printingOrderList;
    public Part2(String fileContents){
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
                Map<Integer, List<Integer>> updateOrder = new LinkedHashMap<>();
                String[] printingOrder = lines[i].split(",");
                for(int n = 0; n < printingOrder.length; n++){
                    updateOrder.put(Integer.parseInt(printingOrder[n]), printingRuleMap.get(Integer.parseInt(printingOrder[n])));

                }
                printingOrderList.add(updateOrder);
            }
        }
        checkOrder();
    }

    private void checkOrder(){
        List<Integer> subsequents = new ArrayList<>();
        int correct = 0;
        List<Integer> incorrectList = new ArrayList<>();
        for(int i = 0; i < printingOrderList.size(); i++){
            subsequents.clear();
            for(Map.Entry<Integer, List<Integer>> entry : printingOrderList.get(i).entrySet()){
                if(!subsequents.isEmpty()){
                    if(subsequents.contains(entry.getKey())){
                        correct++;
                    }
                }
                subsequents.clear();
                if(entry.getValue() != null){
                    subsequents.addAll(entry.getValue());
                }

            }
            System.out.println(correct);
            if(correct != printingOrderList.get(i).size() - 1){
                incorrectList.add(i);
            }
            correct = 0;
        }
        List<List<Integer>> reorderedLists = new ArrayList<>();
        for(int i = 0; i < incorrectList.size(); i++){
            List<Integer> reordered = new ArrayList<>();
            reOrder(printingOrderList.get(incorrectList.get(i)), reordered);
        }
    }


}
