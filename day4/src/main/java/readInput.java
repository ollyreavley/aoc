package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class readInput {
    private final String file;
    public int lineCount;
    public readInput(String input){
        file = input;
    }

    public String readFromInputStream() {
        Path path = Paths.get(file);
        String line;
        StringBuilder lines = new StringBuilder();

        try {
            BufferedReader reader = Files.newBufferedReader(path);
            while((line = reader.readLine()) != null){
                lines.append(line);
                lines.append(System.lineSeparator());
                lineCount++;
            }
            reader.close();
        } catch (IOException e){
            throw new RuntimeException();
        }
        return lines.toString();
    }
}
