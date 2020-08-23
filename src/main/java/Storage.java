package main.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Storage {
    private String filePath;
//    private String directoryName = "./data";
    private Scanner inputSource;

    public Storage(String filePath) {
        try {
            //if (Files.isDirectory(Path.of(directoryName))) {
            this.filePath = filePath;
            inputSource = new Scanner(new File(filePath));
//            } else {
//                throw new NotDirectoryException("Directory: " + directoryName + " cannot be found!");
//            }
        } catch (FileNotFoundException e) {
            System.out.println("File: " + filePath + " cannot be found!");
            System.exit(0);
        }
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        while (inputSource.hasNextLine()) {
            String currentInput = inputSource.nextLine();
            String[] inputArray = currentInput.split("\\|");
            if (inputArray[0].trim().equals("T")) {
                list.add(new Todo(inputArray[2].trim()));
            } else if (inputArray[0].trim().equals("D")) {
                list.add(new Deadline(inputArray[2].trim(), inputArray[3].trim()));
            } else if (inputArray[0].trim().equals("E")) {
                list.add(new Event(inputArray[2].trim(), inputArray[3].trim()));
            }

            if (inputArray[1].trim().equals("1")) {
                list.get(list.size()-1).markAsDone();
            }
        }
        return list;
    }

    public void saveTasks(List<Task> list) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(filePath));
        for(Task task: list) {
            printWriter.println(task.toStringFile());
        }
        printWriter.close();
    }
}

