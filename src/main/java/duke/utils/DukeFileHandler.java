package duke.utils;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to handle the opening, writing and reading of file data.txt
 */
public class DukeFileHandler {
    private final String path;

    public DukeFileHandler(String path) {
        this.path = path;
    }


    /**
     * Reads the files from the file path.
     *
     * @return a List of Tasks created from the file data.txt
     * @throws FileNotFoundException if the file is not found, not created
     */
    public List<Task> readFile() throws FileNotFoundException {
        File file = new File(path);

        if (!file.exists()) {
            try {
                // todo find out why access is denied when creating file
                file.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();

        } else {
            Scanner scan = new Scanner(file);
            List<Task> toDoList = new ArrayList<>();

            while (scan.hasNext()) {
                toDoList.add(createTask(scan.nextLine()));
            }

            return toDoList;
        }
    }


    // input is of format TaskType | isDone | Detail | Date (can be null)
    private Task createTask(String input) {
        String[] inputArr = input.split("\\|");
        boolean isDone = inputArr[1].trim().equals("1");

        switch (inputArr[0].trim()) {
        case "T":
            return new Task(inputArr[2].trim(), isDone);
        case "D":
            return new Deadline(inputArr[2].trim(), isDone, inputArr[3].trim());
        case "E":
            return new Event(inputArr[2].trim(), isDone, inputArr[3].trim());

        default:
            return null;
        }
    }


    // todo the access denied to write file

    /**
     * Writes the list to the file.
     *
     * @param list the data to write to the file
     * @throws IOException if the file could not be found
     */
    public void writeToFile(List<Task> list) throws IOException {

        try {
            FileWriter fileWriter = new FileWriter(path);
            StringBuilder content = new StringBuilder();
            for (Task task : list) {
                content.append(task.toCustomString()).append(System.lineSeparator());
            }

            fileWriter.write(content.toString());
            fileWriter.close();

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

}