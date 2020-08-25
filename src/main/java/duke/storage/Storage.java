package duke.storage;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // converts text in file to task
    public static Task textToTask(String input) {
        String des = "";
        Character first = input.charAt(0);
        Character num = input.charAt(4);
        if (first == 'T') {
            des = "todo ";
            des += input.substring(8);
        } else {
            String temp = input.substring(8);
            String taskDescription = temp.substring(0, temp.indexOf(" |"));
            String date = temp.substring(temp.indexOf("|") + 2);
            if (first == 'D') {
                des = "deadline " + taskDescription + " /by " + date;
            } else if (first == 'E') {
                des = "event " + taskDescription + " /at " + date;
            }
        }

        Task t = new Task(des);
        if (num == '0') {
            return t;
        } else {
            t.markAsDone();
            return t;
        }
    }

    public void fileContentToArrayList(ArrayList<String> tasksStrings) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String input = s.nextLine();
            tasksStrings.add(input);
        }
    }

    public ArrayList<Task> load() throws DukeException {
        // converts text to tasks, duke exception to check if is valid task
        ArrayList<Task> tasksArray = new ArrayList<>();
        ArrayList<String> tasksStrings = new ArrayList<>();

        try {
            fileContentToArrayList(tasksStrings);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        for (int i = 0; i < tasksStrings.size(); i++) {
            String currString = tasksStrings.get(i);
            Task t = textToTask(currString);
            t.validate();
            if (t.isTodo()) {
                tasksArray.add(t.convertToTodo());
            } else if (t.isDeadline()) {
                tasksArray.add(t.convertToDeadline());
            } else {
                tasksArray.add(t.convertToEvent());
            }
        }
        return tasksArray;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void save(String filePath, String textToAdd) {
        // writes fileString to .txt file
        try {
            writeToFile(Duke.FILENAME, textToAdd);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
        }
    }
}

