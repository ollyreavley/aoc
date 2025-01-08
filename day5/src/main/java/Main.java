package main.java;

public class Main {
    public static void main(String[] args) {
        readInput inputStream = new readInput("src/main/resources/test.txt");
        String input = inputStream.readFromInputStream();
        Part2 processor = new Part2(input);
        processor.pageRules();
    }
}