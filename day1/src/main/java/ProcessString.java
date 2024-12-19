package main.java;

import java.util.*;

public class ProcessString {
    private String input;
    private List<Integer> first;
    private List<Integer> second;
    Map<Integer, Integer> freqSecond;
    public ProcessString(String fileContents){
        input = fileContents;
        first = new ArrayList<>();
        second = new ArrayList<>();
    }

    public void splitInput(){
        String[] lines = input.split(System.lineSeparator());
        for(int i = 0; i < lines.length; i++){
            lines[i] = lines[i].trim();
            String[] line = lines[i].split(" ", 2);
            first.add(Integer.parseInt(line[0].trim()));
            second.add(Integer.parseInt(line[1].trim()));
        }
        Collections.sort(first);
        Collections.sort(second);
        int total = 0;
        for(int i = 0; i < first.size(); i++){
            total = total + Math.abs((first.get(i) - second.get(i)));
        }
        System.out.println(total);
        countFreq();
    }

    public void countFreq(){
        freqSecond = new HashMap<>();
        for(int i = 0; i < second.size(); i++){
            int temp = second.get(i);
            int count = 0;
            int n = i;
            while(n < second.size() && temp == second.get(n)){
                count++;
                n++;
            }
            freqSecond.putIfAbsent(temp, count);

        }
        similarity();
    }

    public void similarity(){
        int similarity = 0;
        for(int i = 0; i < first.size(); i++){
            int secondFreq = 0;
            if(freqSecond.get(first.get(i)) != null){
                secondFreq = freqSecond.get(first.get(i));
            }
            similarity = similarity + first.get(i) * secondFreq;
        }
        System.out.println(similarity);
    }
}
