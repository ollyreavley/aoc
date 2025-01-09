package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessString {
    private static final int N = 0, E = 1, S = 3, W = 4;
    private String input;
    private char[][] grid;
    public int lineCount;
    private int x0;
    private int y0;
    private int x;
    private int y;
    private List<List<Integer>> visited;
    private int direction;
    public ProcessString(String fileContents){
        input = fileContents;
        visited = new ArrayList<>();
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
                        direction = N;
                        x = m;
                        y = n;
                        x0 = m;
                        y0 = n;
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
        moveGuard();
        placeObstacle();
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
                        direction = E;
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
                        direction = N;
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
                        direction = S;
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
                        direction = W;
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
        List<Integer> cell = new ArrayList<>(Arrays.asList(y, x, direction));
        visited.add(cell);
        grid[y][x] = 'X';
        if(direction == N){
            y = y - 1;
            grid[y][x] = '^';
        } else if(direction == E){
            x = x + 1;
            grid[y][x] = '>';
        } else if(direction == S){
            y = y + 1;
            grid[y][x] = 'v';
        } else{
            x = x - 1;
            grid[y][x] = '<';
        }
    }

    private void placeObstacle(){
        x = x0;
        y = y0;
        int count = 0;
        for(int i = 1; i < visited.size(); i++){
            System.out.println(i);
            if(grid[visited.get(i).get(0)][visited.get(i).get(1)] == 'X'){
                grid[visited.get(i).get(0)][visited.get(i).get(1)] = '#';
                if(!moveGuardObstacle()){
                    
                    y = visited.get(i).get(0);
                    x = visited.get(i).get(1);
                    direction = visited.get(i).get(2);
                } else{
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private boolean moveGuardObstacle(){
        while(y >= 0 && y < grid.length && x >= 0 && x < grid[0].length){
            System.out.println("Hi");
            if(grid[y][x] == '^'){
                if(y > 0){
                    if(grid[y - 1][x] == '.'){
                        placeX();
                    } else if(grid[y - 1][x] == 'X'){
                        placeX();
                        for(List<Integer> cell : visited){
                            if(y == cell.get(0) && x == cell.get(0) && direction == cell.get(2)){
                                System.out.println("Hit");
                                return true;
                            }
                        }
                    } else{
                        direction = E;
                        grid[y][x] = '>';
                    }
                } else{
                    return false;
                }
            } else if(grid[y][x] == '<'){
                if(x > 0){
                    if(grid[y][x - 1] == '.'){
                        placeX();
                    } else if(grid[y][x - 1] == 'X'){
                        placeX();
                        for(List<Integer> cell : visited){
                            if(y == cell.get(0) && x == cell.get(0) && direction == cell.get(2)){
                                System.out.println("Het");
                                return true;
                            }
                        }
                    } else{
                        direction = N;
                        grid[y][x] = '^';
                    }
                } else{
                    return false;
                }
            } else if(grid[y][x] == '>'){
                if(x < grid[0].length - 1){
                    if(grid[y][x + 1] == '.'){
                        placeX();
                    } else if(grid[y][x + 1] == 'X'){
                        placeX();
                        for(List<Integer> cell : visited){
                            if(y == cell.get(0) && x == cell.get(0) && direction == cell.get(2)){
                                System.out.println("Hot");
                                return true;
                            }
                        }
                    } else{
                        direction = S;
                        grid[y][x] = 'v';
                    }
                } else{
                    return false;
                }
            } else if(grid[y][x] == 'v'){
                if(y < grid.length - 1){
                    if(grid[y + 1][x] == '.'){
                        placeX();
                    } else if(grid[y + 1][x] == 'X'){
                        placeX();
                        for(List<Integer> cell : visited){
                            if(y == cell.get(0) && x == cell.get(0) && direction == cell.get(2)){
                                System.out.println("fucked");
                                return true;
                            }
                        }
                    } else{
                        direction = W;
                        grid[y][x] = '<';
                    }
                } else{
                    return false;
                }
            }
        }
        return false;
    }
}
