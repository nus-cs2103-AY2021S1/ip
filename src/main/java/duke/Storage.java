package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String path;

    /**
     * Constructor of Storage object
     *
     * @param filePath Takes in the path of the file that stores the data.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Creates a file and necessary directories if non-existent.
     */
    public void createFile() {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the data from the hard drive.
     *
     * @return a list of previously recorded Tasks.
     */
    public ArrayList<Task> loadData() {
        String savedTask;
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            createFile();
            File existingData = new File(path);
            Scanner readExistingData = new Scanner(existingData);

            while (readExistingData.hasNextLine()) {
                savedTask = readExistingData.nextLine();
                String[] taskParts = savedTask.split("\\s\\|\\s");

                if (taskParts[0].contains("T")) {

                    Task newToDoTask = new ToDo(taskParts[2].trim());
                    if (taskParts[1].contains("1")) {
                        newToDoTask.markAsDone();
                    }
                    tasks.add(newToDoTask);


                } else if (taskParts[0].contains("D")) {

                    Task newDeadlineTask = new Deadline(taskParts[2], taskParts[3]);
                    if (taskParts[1].contains("1")) {
                        newDeadlineTask.markAsDone();
                    }
                    tasks.add(newDeadlineTask);

                } else if (taskParts[0].contains("E")) {

                    Task newEventTask = new Event(taskParts[2], taskParts[3]);
                    if (taskParts[1].contains("1")) {
                        newEventTask.markAsDone();
                    }
                    tasks.add(newEventTask);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;

    }

    /**
     * Writes to File at the end of the program
     *
     * @param tasks Takes in the most updated list of Tasks.
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (int i = 0; i < tasks.size(); i++) {
                String task = tasks.get(i).toStringInFile() + "\n";
                fileWriter.write(task);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
