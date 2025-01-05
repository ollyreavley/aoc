package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessString {
    private String input;
    private char[][] grid;
    public int lineCount;
    public ProcessString(String fileContents){
        input = fileContents;
    }
    public void convertInputToGrid(){
        char[] inputArr = input.toCharArray();
        int i = 0, count = 0;
        while(inputArr[i] == 'X' || inputArr[i] == 'M' || inputArr[i] == 'A' || inputArr[i] == 'S'){
            count++;
            i++;
        }
        grid = new char[lineCount][count];
        char[] inputArr2 = new char[lineCount * count];
        int arrCount = 0;
        for(int p = 0; p < inputArr2.length; p++){
            if(inputArr[arrCount] == 'X' || inputArr[arrCount] == 'M' || inputArr[arrCount] == 'A' || inputArr[arrCount] == 'S'){
                inputArr2[p] = inputArr[arrCount];
            } else{
                p--;
            }
            arrCount++;
        }
        arrCount = 0;
        for(int n = 0; n < grid.length; n++){
            for(int m = 0; m < grid[0].length; m++){
                grid[n][m] = inputArr2[arrCount];
                arrCount++;
            }
        }
        searchforX_Mas();
    }

    private void searchforX_Mas(){
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int n = 0; n < grid[0].length; n++){
                if(grid[i][n] == 'A'){
                    if(i > 0 && n > 0 && i < grid.length - 1 && n < grid[0].length - 1 && grid[i - 1][n - 1] == 'M' && grid[i + 1][n + 1] == 'S' && grid[i - 1][n + 1] == 'M' && grid[i + 1][n - 1] == 'S'){
                        count++;
                    }
                    if(i > 0 && n > 0 && i < grid.length - 1 && n < grid[0].length - 1 && grid[i - 1][n - 1] == 'S' && grid[i + 1][n + 1] == 'M' && grid[i - 1][n + 1] == 'S' && grid[i + 1][n - 1] == 'M'){
                        count++;
                    }
                    if(i > 0 && n > 0 && i < grid.length - 1 && n < grid[0].length - 1 && grid[i - 1][n - 1] == 'M' && grid[i + 1][n + 1] == 'S' && grid[i - 1][n + 1] == 'S' && grid[i + 1][n - 1] == 'M'){
                        count++;
                    }
                    if(i > 0 && n > 0 && i < grid.length - 1 && n < grid[0].length - 1 && grid[i - 1][n - 1] == 'S' && grid[i + 1][n + 1] == 'M' && grid[i - 1][n + 1] == 'M' && grid[i + 1][n - 1] == 'S'){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private void searchForXmas(){
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int n = 0; n < grid[0].length; n++){
                if(grid[i][n] == 'X'){
                    if(n < grid[0].length - 3 && grid[i][n + 1] == 'M' && grid[i][n + 2] == 'A' && grid[i][n + 3] == 'S'){
                        count++;
                    }
                    if(n >= 3 && grid[i][n - 1] == 'M' && grid[i][n - 2] == 'A' && grid[i][n - 3] == 'S'){
                        count++;
                    }
                    if(i < grid.length - 3 && grid[i + 1][n] == 'M' && grid[i + 2][n] == 'A' && grid[i + 3][n] == 'S'){
                        count++;
                    }
                    if(i >= 3 && grid[i - 1][n] == 'M' && grid[i - 2][n] == 'A' && grid[i - 3][n] == 'S'){
                        count++;
                    }
                    if(i >= 3 && n >= 3 && grid[i - 1][n - 1] == 'M' && grid[i - 2][n - 2] == 'A' && grid[i - 3][n - 3] == 'S'){
                        count++;
                    }
                    if(i < grid.length - 3 && n < grid[0].length - 3 && grid[i + 1][n + 1] == 'M' && grid[i + 2][n + 2] == 'A' && grid[i + 3][n + 3] == 'S'){
                        count++;
                    }
                    if(i >= 3 && n < grid[0].length - 3 && grid[i - 1][n + 1] == 'M' && grid[i - 2][n + 2] == 'A' && grid[i - 3][n + 3] == 'S'){
                        count++;
                    }
                    if(i < grid.length - 3 && n >= 3 && grid[i + 1][n - 1] == 'M' && grid[i + 2][n - 2] == 'A' && grid[i + 3][n - 3] == 'S'){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
