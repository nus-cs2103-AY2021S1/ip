package duke.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.handle.LoadingException;
import duke.task.Deadline;
import duke.task.DoWithinPeriodTask;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * The Storage class reads the local task record and the task list, and updates
 * the local record when the list of tasks is changed.
 */
public class Storage {
    private String path;

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
        assert path != null : "the path should not be null";
        File record = new File(path);
        Scanner scanner = new Scanner(System.in);

        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String next;

            if (!record.exists() || record.isDirectory()) {
                File directory = new File("data");
                directory.mkdirs();
                record = new File(directory, "duke.txt");
                record.createNewFile();
                throw new FileNotFoundException("Creating new record");
            }

            scanner.close();
            scanner = new Scanner(record);
            System.out.println(scanner.hasNextLine());
            while (scanner.hasNextLine()) {
                next = scanner.nextLine();
                assert next != null : "the command should not be null";
                String[] strings = next.split(" \\| ");

                if (strings[1].equals("0") || strings[1].equals("1")) {
                    if (strings[0].equals("T") && strings.length == 3) {
                        readToDo(tasks, strings[2], strings[1]);

                    } else if (strings[0].equals("D") && strings.length == 4) {
                        readDeadline(tasks, strings[2], strings[3], strings[1]);

                    } else if (strings[0].equals("E") && strings.length == 4) {
                        readEvent(tasks, strings[2], strings[3], strings[1]);

                    } else if (strings[0].equals("P") && strings.length == 5) {
                        readPeriodTask(tasks, strings[2], strings[3], strings[4], strings[1]);

                    } else {
                        throw new LoadingException(
                                "The previous record cannot be read becuase the format of a task is incorrect\n"
                                        + "Cleaning the record to start again");
                    }
                } else {
                    throw new LoadingException(
                            "The previous record cannot be read because the complete state of a task is incorrect\n"
                                    + "Cleaning the record to start again");
                }
            }
            return tasks;

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Throw exception after the file has been created");
            throw fileNotFoundException;

        } catch (IOException ioException) {
            //System.out.println("Throw caused by loading the file");
            throw new LoadingException("Problems with creating the file");

        } catch (LoadingException loadingException) {
            //System.out.println("Throw caused by loading the file");
            throw new LoadingException("Problems with creating the file");

        } finally {
            scanner.close();
        }
    }

    /**
     * Reads the description and state of the task, and adds the task to the taks list.
     *
     * @param tasks The task list.
     * @param description The description of the task.
     * @param state The state of the task.
     */
    private void readToDo(ArrayList<Task> tasks, String description, String state) {
        ToDo todo = new ToDo(description);

        if (state.equals("1")) {
            todo.markAsCompleted();
        }

        tasks.add(todo);
    }

    /**
     * Reads the description, time, and state of the task, and adds the task to the taks list.
     *
     * @param tasks The task list.
     * @param description The description of the task.
     * @param time The time of the task.
     * @param state The state of the task.
     */
    private void readDeadline(ArrayList<Task> tasks, String description, String time, String state) {
        Deadline deadline = new Deadline(description, LocalDate.parse(time));

        if (state.equals("1")) {
            deadline.markAsCompleted();
        }

        tasks.add(deadline);
    }

    /**
     * Reads the description, time, and state of the task, and adds the task to the taks list.
     *
     * @param tasks The task list.
     * @param description The description of the task.
     * @param time The time of the task.
     * @param state The state of the task.
     */
    private void readEvent(ArrayList<Task> tasks, String description, String time, String state) {
        Event event = new Event(description, LocalDate.parse(time));

        if (state.equals("1")) {
            event.markAsCompleted();
        }

        tasks.add(event);
    }

    /**
     * Reads the description, time, and state of the task, and adds the task to the taks list.
     *
     * @param tasks The task list.
     * @param description The description of the task.
     * @param start The start time of the task.
     * @param end The end time of the task.
     * @param state The state of the task.
     */
    private void readPeriodTask(ArrayList<Task> tasks, String description, String start, String end, String state) {
        DoWithinPeriodTask doWithinPeriodTask = new DoWithinPeriodTask(
                description,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );

        if (state.equals("1")) {
            doWithinPeriodTask.markAsCompleted();
        }

        tasks.add(doWithinPeriodTask);
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

        for (int i = 0; i < tasks.size(); i = i + 1) {
            string = string.concat(tasks.get(i).record() + System.lineSeparator());
        }
        fileWriter.write(string);
        fileWriter.close();
    }
}
