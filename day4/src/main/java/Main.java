package main.java;

public class Main {
    public static void main(String[] args) {
        readInput inputStream = new readInput("src/main/resources/test.txt");
        String input = inputStream.readFromInputStream();
        int lineCount = inputStream.lineCount;
        ProcessString processor = new ProcessString(input);
        processor.lineCount = lineCount;
        processor.convertInputToGrid();
    }
}