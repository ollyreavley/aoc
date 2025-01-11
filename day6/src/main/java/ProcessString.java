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
    private List<List<Integer>> visited2;
    private int direction;
    private char[][] gridSpare;
    private boolean part2;
    public ProcessString(String fileContents){
        input = fileContents;
        visited = new ArrayList<>();
        part2 = false;
        visited2 = new ArrayList<>();
    }
    public void convertInputToGrid(){
        char[] inputArr = input.toCharArray();
        int i = 0, count = 0;
        while(inputArr[i] == '.' || inputArr[i] == '#' || inputArr[i] == '^'){
            count++;
            i++;
        }
        grid = new char[lineCount][count];
        gridSpare = new char[lineCount][count];
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
        for(int n = 0; n < grid.length; n++){
            System.arraycopy(grid[n], 0, gridSpare[n], 0, gridSpare[n].length);
        }
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
                    grid[y][x] = 'X';
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
                    grid[y][x] = 'X';
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
                    grid[y][x] = 'X';
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
                    grid[y][x] = 'X';
                    return count + 1;
                }
            }
        }

        return 0;
    }

    private void placeX(){

        if(!part2){
            List<Integer> cell = new ArrayList<>(Arrays.asList(y, x, direction));
            visited.add(cell);
        }

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
        part2 = true;
        int count = 0;
        for(int i = 1; i < visited.size(); i++){
            System.out.println(i);
            for(int n = 0; n < grid.length; n++){
                System.arraycopy(gridSpare[n], 0, grid[n], 0, grid[n].length);
            }
            y = y0;
            x = x0;
            direction = N;
            grid[y][x] = '^';
            if(grid[visited.get(i).get(0)][visited.get(i).get(1)] != '^'){
                grid[visited.get(i).get(0)][visited.get(i).get(1)] = 'O';
                //System.out.println(i);
                if(inLoop()){
                    for(int n = 0; n < grid.length; n++){
                        System.out.println(grid[n]);
                    }
                    count++;
                    System.out.println("subtotal" + count);
                }
                visited2.clear();
            }

        }
        System.out.println("total " + count);
    }

    private boolean inLoop(){

        for(int n = 0; n < grid.length; n++){
            System.out.println(grid[n]);
        }
        System.out.println("break");
        while(y >= 0 && y < grid.length && x >= 0 && x < grid[0].length){

            //System.out.println("hi");
            if(grid[y][x] == '^'){
                //System.out.println("hit");
                if(y > 0){
                    if(grid[y - 1][x] == '.'){
                        placeX();
                    } else if(grid[y - 1][x] == 'X'){
                        placeX();

                    } else{
                        int corners = 0;
                        for(int i = 0; i < visited2.size() - 1; i++){

                            if(y == visited2.get(i).get(0) && x == visited2.get(i).get(1) && direction == visited2.get(i).get(2)){
                                corners++;
                            }
                            if(corners >= 10){
                                return true;
                            }
                        }
                        List<Integer> cell = new ArrayList<>(Arrays.asList(y, x, direction));
                        visited2.add(cell);
                        direction = E;
                        grid[y][x] = '>';
                    }
                } else{
                    return false;
                }
            } else if(grid[y][x] == '<'){
                //System.out.println("hot");
                if(x > 0){
                    if(grid[y][x - 1] == '.'){
                        placeX();
                    } else if(grid[y][x - 1] == 'X'){
                        placeX();
                    } else{
                        int corners = 0;
                        for(int i = 0; i < visited2.size() - 1; i++){

                            if(y == visited2.get(i).get(0) && x == visited2.get(i).get(1) && direction == visited2.get(i).get(2)){
                                corners++;
                            }
                            if(corners >= 10){
                                return true;
                            }
                        }
                        List<Integer> cell = new ArrayList<>(Arrays.asList(y, x, direction));
                        visited2.add(cell);
                        direction = N;
                        grid[y][x] = '^';
                    }
                } else{
                    return false;
                }
            } else if(grid[y][x] == '>'){
                //System.out.println("get");
                if(x < grid[0].length - 1){
                    if(grid[y][x + 1] == '.'){
                        placeX();
                    } else if(grid[y][x + 1] == 'X'){
                        placeX();

                    } else{
                        int corners = 0;
                        for(int i = 0; i < visited2.size() - 1; i++){

                            if(y == visited2.get(i).get(0) && x == visited2.get(i).get(1) && direction == visited2.get(i).get(2)){
                                corners++;
                            }
                            if(corners >= 10){
                                return true;
                            }
                        }
                        List<Integer> cell = new ArrayList<>(Arrays.asList(y, x, direction));
                        visited2.add(cell);
                        direction = S;
                        grid[y][x] = 'v';
                    }
                } else{
                    return false;
                }
            } else if(grid[y][x] == 'v'){
                //System.out.println("got");
                if(y < grid.length - 1){
                    if(grid[y + 1][x] == '.'){
                        placeX();
                    } else if(grid[y + 1][x] == 'X'){
                        placeX();

                    } else{
                        int corners = 0;
                        for(int i = 0; i < visited2.size() - 1; i++){

                            if(y == visited2.get(i).get(0) && x == visited2.get(i).get(1) && direction == visited2.get(i).get(2)){
                                corners++;
                            }
                            if(corners >= 10){
                                return true;
                            }
                        }
                        List<Integer> cell = new ArrayList<>(Arrays.asList(y, x, direction));
                        visited2.add(cell);
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
