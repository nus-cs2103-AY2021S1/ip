package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /**
     * Used to load tasks from a storage file.
     * Parses the file specified at the filepath and adds Tasks to the tasklist
     * according to the contents of the file.
     * @param filepath The relative path where the file is stored.
     * @param taskList The task list to which the new Tasks should be added to.
     * @return true if the file is loaded successfully, false otherwise.
     */
    public static boolean loadTasksFrom(String filepath, TaskList taskList) {
        assert taskList != null;
        assert taskList.numOfTasks() == 0;
        File dataFile = new File(filepath);

        try {
            Scanner fileScanner = new Scanner(dataFile);

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                if (!nextLine.isEmpty()) {
                    String[] tokens = nextLine.split(" [|] ");

                    boolean isDone = !tokens[0].equals("0");

                    if (tokens[1].equals("Todo")) {
                        taskList.addTask(new Todo(tokens[2], isDone));
                    } else if (tokens[1].equals("Event")) {
                        taskList.addTask(new Event(tokens[2], isDone, tokens[3]));
                    } else if (tokens[1].equals("Deadline")) {
                        taskList.addTask(new Deadline(tokens[2], isDone, tokens[3]));
                    }
                }
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            // No need to do anything if file not found as file will be created later anyway
            return false;
        }

        return true;
    }

    /**
     * Stores all tasks in the specified tasklist to the file specified in the filepath.
     * @param filepath The relative path where the file is stored.
     * @param taskList The task list which contains the Tasks to be stored.
     * @return true if the Tasks have been successfully stored to the file, false otherwise.
     */
    public static boolean saveTasksTo(String filepath, TaskList taskList) {
        assert taskList != null;
        ArrayList<Task> tasksArray = taskList.getAllTasks();
        String fileOutput = "";

        for (Task task: tasksArray) {
            for (String string: task.serialize()) {
                fileOutput = fileOutput + string + " | ";
            }
            fileOutput = fileOutput + "\n";
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(fileOutput);
            writer.close();

        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
