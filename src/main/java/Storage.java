import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    protected String filePath;
    protected ArrayList<Task> tasks;

    /**
     * Creates Storage object by loading tasks stored locally in filePath.
     * @param filePath File path to .txt file to load tasks stored locally.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
        loadTasks(tasks);
    }

    /**
     * Saves tasks stored in tasklist before program is terminated in a 'duke.txt' file.
     *
     * @param tasks ArrayList of Tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            // Creates a BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

            // Write tasks as strings into file
            for (int i = 0; i < tasks.size(); i++) {
                bufferedWriter.write(tasks.get(i).toString() + "\n");
            }

            // Closes the writer
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks stored in 'duke.txt' when program is executed.
     *
     * @param tasks ArrayList of Tasks to be loaded.
     */
    public void loadTasks(ArrayList<Task> tasks) {
        BufferedReader bufferedReader = null;

        try {
            String currentLine = null;
            FileReader file = new FileReader(filePath);

            if (file.ready()) { // file exists to be read
                bufferedReader = new BufferedReader(file);

                while ((currentLine = bufferedReader.readLine()) != null) {
                    String taskType = currentLine.substring(1, 2);
                    boolean isDone = currentLine.contains("✗") ? false : true;
                    String taskDescription = currentLine.substring(currentLine.indexOf(" ") + 1);

                    switch (taskType) {
                    case "T":
                        tasks.add(new Todo(taskDescription, isDone));
                        break;
                    case "D":
                        String deadlineDescription = taskDescription.substring(0, taskDescription.indexOf("(") - 1);
                        String deadlineBy = taskDescription.substring(taskDescription.indexOf("by:") + 4,
                                taskDescription.indexOf(")"));
                        LocalDate deadline = LocalDate.parse(deadlineBy,
                                DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        tasks.add(new Deadline(deadlineDescription, deadline, isDone));
                        break;
                    case "E":
                        String eventDescription = taskDescription.substring(0, taskDescription.indexOf("(") - 1);
                        String eventAt = taskDescription.substring(taskDescription.indexOf("at:") + 4,
                                taskDescription.indexOf(")"));
                        tasks.add(new Event(eventDescription, eventAt, isDone));
                        break;
                    default:
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, an empty duke.txt file has been created for you.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

