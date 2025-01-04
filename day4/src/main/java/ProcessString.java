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
        char inputArr[] = input.toCharArray();
        int i = 0, count = 0;
        while(inputArr[i] != System.line.separator){
            count++;
            i++;
        }
        grid = new char[lineCount][count];
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
                    if(i >= 3 && n < grid[0].length - 3 && grid[i - 1][n + 1] == 'M' && grid[i - 2][m + 2] == 'A' && grid[i - 3][n + 3] == 'S'){
                        count++;
                    }
                    if(i < grid.length - 3 && n >= 3 && grid[i + 1][n - 1] == 'M' && grid[i + 2][n - 2] == 'A' && grid[i + 3][n + 3] == 'S'){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}