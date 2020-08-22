package main.java.duke.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import main.java.duke.task.Task;
import main.java.duke.task.Event;
import main.java.duke.task.ToDo;
import main.java.duke.task.Deadline;
import main.java.duke.handle.LoadingException;

/**
 * The Storage class reads the local task record and the task list, and updates
 * the local record when the list of tasks is changed.
 */
public class Storage {
    public String path;

    /**
     * Takes in the path of the local record and returns a storage manager.
     *
     * @param path The path of the local record.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Reads the local record according to the path.
     *
     * @return The list of tasks.
     * @throws FileNotFoundException If the local record is not found.
     * @throws LoadingException If the local record cannot be read.
     */
    public ArrayList<Task> readRecord() throws FileNotFoundException, LoadingException {
        File record = new File(path);
        Scanner scanner = new Scanner(record);

        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String next;
            if (record.createNewFile()) {
                throw new FileNotFoundException("Creating new record");
            }
            while (scanner.hasNextLine()) {
                next = scanner.nextLine();
                String[] strings = next.split(" \\| ");
                if (strings[1].equals("0") || strings[1].equals("1")) {
                    if (strings[0].equals("T") && strings.length == 3) {
                        ToDo todo = new ToDo(strings[2]);
                        if (strings[1].equals("1")) {
                            todo.markAsCompleted();
                        }
                        tasks.add(todo);
                    } else if (strings[0].equals("D") && strings.length == 4) {
                        Deadline deadline = new Deadline(strings[2], LocalDate.parse(strings[3]));
                        if (strings[1].equals("1")) {
                            deadline.markAsCompleted();
                        }
                        tasks.add(deadline);
                    } else if (strings[0].equals("E") && strings.length == 4) {
                        Event event = new Event(strings[2], LocalDate.parse(strings[3]));
                        if (strings[1].equals("1")) {
                            event.markAsCompleted();
                        }
                        tasks.add(event);
                    } else {
                        throw new LoadingException("The previous record cannot be read becuase the format of a task is incorrect\nCleaning the record to start again");
                    }
                } else {
                    throw new LoadingException("The previous record cannot be read because the complete state of a task is incorrect\nCleaning the record to start again");
                }
            }
            return tasks;
        } catch (FileNotFoundException fileNotFoundException) {
            throw fileNotFoundException;
        } catch (Exception exception) {
            throw new LoadingException(exception.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Updates the local record when the list of tasks is changed.
     *
     * @param taskList The task list.
     * @throws IOException If the local record cannot be updated.
     */
    public void writeRecord(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        ArrayList<Task> tasks = taskList.getTasks();
        String string = "";
        
        for(int i = 0; i < tasks.size(); i = i + 1) {
            string = string.concat(tasks.get(i).record() + System.lineSeparator());
        }
        fileWriter.write(string);
        fileWriter.close();
    }
}
