package main.java;


public class Main {

    public static void main(String[] args) {
        readInput inputStream = new readInput("src/main/resources/input.txt");
        String input = inputStream.readFromInputStream();
        ProcessString processor = new ProcessString(input);
        processor.splitInput();
    }
}