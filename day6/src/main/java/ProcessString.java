package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessString {
    private String input;
    private char[][] grid;
    public int lineCount;

    private int x;
    private int y;
    public ProcessString(String fileContents){
        input = fileContents;
    }
    public void convertInputToGrid(){
        char[] inputArr = input.toCharArray();
        int i = 0, count = 0;
        while(inputArr[i] == '.' || inputArr[i] == '#' || inputArr[i] == '^'){
            count++;
            i++;
        }
        grid = new char[lineCount][count];
        int arrCount = 0;

        for(int n = 0; n < grid.length; n++){
            for(int m = 0; m < grid[0].length; m++){
                if(inputArr[arrCount] == '.' || inputArr[arrCount] == '#' || inputArr[arrCount] == '^'){
                    if(inputArr[arrCount] == '^'){
                        x = m;
                        y = n;
                    }
                    grid[n][m] = inputArr[arrCount];
                } else{
                    if(m > 0){
                        m--;
                    } else{
                        n--;
                        m = grid[0].length;
                    }
                }
                arrCount++;
            }
        }
        System.out.println(moveGuard());
    }

    private int moveGuard(){
        int count = 0;
        while(y >= 0 && y < grid.length && x >= 0 && x < grid[0].length){
            if(grid[y][x] == '^'){
                if(y > 0){
                    if(grid[y - 1][x] == '.'){
                        count++;
                        placeX();
                    } else if(grid[y - 1][x] == 'X'){
                        placeX();
                    } else{
                        grid[y][x] = '>';
                    }
                } else{
                    return count + 1;
                }
            } else if(grid[y][x] == '<'){
                if(x > 0){
                    if(grid[y][x - 1] == '.'){
                        count++;
                        placeX();
                    } else if(grid[y][x - 1] == 'X'){
                        placeX();
                    } else{
                        grid[y][x] = '^';
                    }
                } else{
                    return count + 1;
                }
            } else if(grid[y][x] == '>'){
                if(x < grid[0].length - 1){
                    if(grid[y][x + 1] == '.'){
                        count++;
                        placeX();
                    } else if(grid[y][x + 1] == 'X'){
                        placeX();
                    } else{
                        grid[y][x] = 'v';
                    }
                } else{
                    return count + 1;
                }
            } else if(grid[y][x] == 'v'){
                if(y < grid.length - 1){
                    if(grid[y + 1][x] == '.'){
                        count++;
                        placeX();
                    } else if(grid[y + 1][x] == 'X'){
                        placeX();
                    } else{
                        grid[y][x] = '<';
                    }
                } else{
                    return count + 1;
                }
            }
        }
        return 0;
    }

    private void placeX(){
        char direction = grid[y][x];
        grid[y][x] = 'X';
        if(direction == '^'){
            y = y - 1;
            grid[y][x] = '^';
        } else if(direction == '>'){
            x = x + 1;
            grid[y][x] = '>';
        } else if(direction == 'v'){
            y = y + 1;
            grid[y][x] = 'v';
        } else{
            x = x - 1;
            grid[y][x] = '<';
        }
    }
}
