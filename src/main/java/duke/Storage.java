package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Storage {
    private ArrayList<String> lines;
    private String directoryPath;
    private String filePath;
    private File data;

    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
        this.lines = new ArrayList<String>();
    }

    public void processData() throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        data = new File(filePath);
        if (!data.exists()) {
            data.createNewFile();
        }
        Scanner fileReader = new Scanner(data);
        StringBuffer buffer = new StringBuffer();
        while (fileReader.hasNextLine()) {
            buffer.append(fileReader.nextLine()).append("\n");
        }
        fileReader.close();
        String fileContents = buffer.toString();
        if (fileContents.length() != 0) {
            String[] lineArray = fileContents.split("\n");
            Collections.addAll(lines, lineArray);
        }
    }

    public ArrayList<String> getData() {
        return lines;
    }

    public void saveData(ArrayList<String> finalLines) throws FileNotFoundException {
        StringBuffer finalLineList = new StringBuffer();
        for (int i = 0; i < finalLines.size(); i++) {
            String currentLine = finalLines.get(i);
            finalLineList.append(currentLine).append("\n");
        }
        PrintWriter prw = new PrintWriter(data);
        prw.println(finalLineList.toString());
        prw.close();
    }
}
