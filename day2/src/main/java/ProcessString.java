package main.java;

import java.util.*;

public class ProcessString {
    private String input;
    private int safe;
    public ProcessString(String fileContents){
        input = fileContents;
    }

    public void splitInput(){
        String[] lines = input.split(System.lineSeparator());
        for(int i = 0; i < lines.length; i++){
            lines[i] = lines[i].trim();
            String[] line = lines[i].split(" ");
            isSafe(line);
        }
        System.out.println(safe);
    }

    private void isSafe(String[] line){
        int current = 0;
        int prev = 0;
        int unsafe = 0;
        boolean increase = false;
        for(int i = 0; i < line.length; i++){
            if(i > 0){
                prev = current;
            }
            current = Integer.parseInt(line[i]);
            int diff = current - prev;
            if(i == 1 && diff > 0){
                increase = true;
            }
            if(i > 0 && Math.abs(current - prev) > 3){
                unsafe = subSequenceCheck(line);
            } else if(i > 0 && current == prev){
                unsafe = subSequenceCheck(line);
            } else if(i > 0 && diff > 0 && !increase){
                unsafe = subSequenceCheck(line);
            } else if(i > 0 && diff < 0 && increase){
                unsafe = subSequenceCheck(line);
            }
        }
        if(unsafe < 2){
            safe++;
        }
    }

    private int subSequenceCheck(String[] line){
        int unsafe = 0;
        for(int i = 0; i < line.length; i++){
            List<String> newArr = Arrays.asList(line);
            newArr.remove(i);
            for(int n = 0; n < newArr.size(); n++){
                int current = 0;
                int prev = 0;

                boolean increase = false;
                if(n > 0){
                    prev = current;
                }
                current = Integer.parseInt(line[n]);
                int diff = current - prev;
                if(n == 1 && diff > 0){
                    increase = true;
                }
                if(n > 0 && Math.abs(current - prev) > 3){
                    unsafe++;
                } else if(n > 0 && current == prev){
                    unsafe++;
                } else if(n > 0 && diff > 0 && !increase){
                    unsafe++;
                } else if(n > 0 && diff < 0 && increase){
                    unsafe++;
                }
            }
        }
        return unsafe;

    }
}
