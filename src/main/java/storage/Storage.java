package storage;

import duke.DukeException;
import tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from a file and saving tasks in the same file
 *
 * @author (Sruthi)
 */
public class Storage {
    private final File file;
    private final String outputFormat = "  %s\n";
    private final TaskList taskList;

    /**
     * Takes in the filePath of the file the tasks are saved in and the taskList
     * which will contain the list of tasks of the Java Duke Program
     *
     * @param filePath path of file from the root
     * @param taskList list of tasks
     */
    public Storage(String filePath, TaskList taskList) {
        this.file = new File(filePath);
        this.taskList = taskList;
    }

    private void createFile() throws DukeException {
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Oops, an error occurred while creating the file >.<");
        }
    }

    /**
     * It gets the task list from the file
     *
     * @throws DukeException
     */
    public void getTodoList() throws DukeException {
        try {
            createFile();
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String task = s.nextLine();
                formatStringToTask(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Oops, file not found >.<");
        }
    }

    /**
     * It takes in the String of a task from the file and calls the taskList's add
     * function to add the task to the list of tasks. If any error occurred while
     * adding the task, it is printed to the user.
     *
     * @param task the string of task from the file
     */
    public void formatStringToTask(String task) {
        assert(task.contains(" "));
        String[] split = task.split(" ");
        assert(task.length() >= 6);
        String body = task.substring(6);
        try {
            switch (split[0]) {
            case "[T][O]":
                taskList.addTodoItem(body, true);
                break;
            case "[T][X]":
                taskList.addTodoItem(body, false);
                break;
            case "[D][O]":
                taskList.addDeadline(body, true);
                break;
            case "[D][X]":
                taskList.addDeadline(body, false);
                break;
            case "[E][O]":
                taskList.addEvent(body, true);
                break;
            default:
                taskList.addEvent(body, false);
            }
        } catch (DukeException e) {
            System.out.printf(outputFormat, e.getMessage());
        }
    }

    /**
     * It overwrites the file to include all the tasks in the current task list
     * and if any error occurred while overwriting, the error message is printed
     * to the user.
     */
    public void overwriteTodoList() {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(taskList.formatTodoListToString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
