package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

import exception.MissingInfoException;

import task.Task;
import task.Event;
import task.Deadline;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String pathname;

    public Storage(String pathname) {
        this.pathname = pathname;
    }

    /**
     * Fill the TaskList up with the tasks from the text file.
     *
     * @param taskList A TaskList with no tasks.
     * @throws IOException If file is not found or error reading file.
     */
    public void load(TaskList taskList) throws IOException, MissingInfoException {
        try {
            File directory = new File("data");
            checkDirectory(directory);

            File file = new File(this.pathname);
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            processData(scanner, taskList);

        } catch (IOException e) {
            throw e;
        } catch (MissingInfoException e) {
            throw e;
        }
    }

    private void checkDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private void processData(Scanner scanner, TaskList taskList) throws MissingInfoException {
        while (scanner.hasNextLine()) {
            String[] data;
            data = scanner.nextLine().split(" \\| ");

            switch (data[0]) {
                case "T":
                    taskList.addTask(taskList.createTask(TaskType.TypeOfTask.TODO, data[2],
                            null, data[1].equals("1") ? true : false));
                    break;
                case "D":
                    taskList.addTask(taskList.createTask(TaskType.TypeOfTask.DEADLINE, data[2],
                            LocalDateTime.parse(data[3]), data[1].equals("1") ? true : false));
                    break;
                case "E":
                    taskList.addTask(taskList.createTask(TaskType.TypeOfTask.EVENT, data[2],
                            LocalDateTime.parse(data[3]), data[1].equals("1") ? true : false));
                    break;
                default:
                    throw new MissingInfoException("There is something wrong with your database file...");
            }
        }
    }

    /**
     * Write the tasks to the text file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If file is not found or error writing to file.
     */
    public void save(TaskList taskList) throws IOException, MissingInfoException {
        try {
            ArrayList<Task> tasks = taskList.getTasks();
            String taskString = convertTasksToString(tasks, "");
            writeToFile(taskString);
        } catch (IOException e) {
            throw e;
        } catch (MissingInfoException e) {
            throw e;
        }
    }

    private String convertTasksToString(ArrayList<Task> tasks, String initialString) throws MissingInfoException {
        String taskString = initialString;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String typeOfTask = task.getClass().getName();
            String timing = "";

            switch (typeOfTask) {
                case "task.Todo":
                    taskString = taskString + "T";
                    break;
                case "task.Deadline":
                    taskString = taskString + "D";
                    timing = timing + ((Deadline) task).getTiming();
                    break;
                case "task.Event":
                    taskString = taskString + "E";
                    timing = timing + ((Event) task).getTiming();
                    break;
                default:
                    throw new MissingInfoException("Something wrong with your function parameter?");
            }

            taskString = taskString + " | " + (task.getStatus() ? "1" : "0") + " | " + task.getTaskDescription()
                    + ((typeOfTask.equals("task.Deadline") || typeOfTask.equals("task.Event"))
                    ? " | " + timing
                    : "");

            taskString = taskString + "\n";
        }

        return taskString;
    }

    private void writeToFile(String taskString) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.pathname);
            fw.write(taskString);
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
