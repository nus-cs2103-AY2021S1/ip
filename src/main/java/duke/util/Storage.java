package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;

public class Storage {

    private static final String SPLITTER = "|";
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads task information stored in the file.
     *
     * @return List of tasks after loading.
     * @throws DukeException When file formatting is wrong.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    processLine(sc.nextLine(), taskList);
                }
                sc.close();
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Processes the line by splitting relevant information into a String array.
     *
     * @param lineToProcess Line to be processed.
     * @param taskList Task list to store tasks after the line is processed.
     * @throws DukeException When file formatting is wrong.
     */
    private void processLine(String lineToProcess, List<Task> taskList) throws DukeException {
        String[] taskInformation = lineToProcess.split("\\" + SPLITTER);
        if (taskInformation[0].equals(TaskType.TODO.getInitial())) {
            processToDoTask(taskInformation, taskList);
        } else if (taskInformation[0].equals(TaskType.DEADLINE.getInitial())) {
            processDeadlineTask(taskInformation, taskList);
        } else if (taskInformation[0].equals(TaskType.EVENT.getInitial())) {
            processEventTask(taskInformation, taskList);
        } else {
            throw new DukeException("File formatting is wrong...");
        }
    }

    /**
     * Processes the task information by accessing the array elements, then adding the task into the task list.
     *
     * @param taskInformation Contains information of the task to be added.
     * @param taskList Task list to store tasks after processing the task information.
     * @throws DukeException When To Do task information formatting is wrong.
     */
    private void processToDoTask(String[] taskInformation, List<Task> taskList) throws DukeException {
        try {
            taskList.add(new ToDo(taskInformation[2], isDone(taskInformation[1])));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("File formatting is wrong...");
        }
    }

    /**
     * Processes the task information by accessing the array elements, then adding the task into the task list.
     *
     * @param taskInformation Contains information of the task to be added.
     * @param taskList Task list to store tasks after processing the task information.
     * @throws DukeException When Deadline task information formatting is wrong.
     */
    private void processDeadlineTask(String[] taskInformation, List<Task> taskList) throws DukeException {
        try {
            taskList.add(new Deadline(taskInformation[2], isDone(taskInformation[1]), taskInformation[3]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("File formatting is wrong...");
        }
    }

    /**
     * Processes the task information by accessing the array elements, then adding the task into the task list.
     *
     * @param taskInformation Contains information of the task to be added.
     * @param taskList Task list to store tasks after processing the task information.
     * @throws DukeException When Event task information formatting is wrong.
     */
    private void processEventTask(String[] taskInformation, List<Task> taskList) throws DukeException {
        try {
            taskList.add(new Event(taskInformation[2], isDone(taskInformation[1]), taskInformation[3]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("File formatting is wrong...");
        }
    }

    public String getDoneString(Task task) {
        return task.isDone() ? "1" : "0";
    }

    public boolean isDone(String storedTaskStatus) {
        return storedTaskStatus.equals("1");
    }

    /**
     * Updates the tasks stored in file from the task list.
     *
     * @param taskList Task list to be updated in file.
     * @throws DukeException When there is an input or output error.
     */
    public void updateTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskList.getStoredTasks()) {
                if (task instanceof ToDo) {
                    fileWriter.write(String.join(SPLITTER, TaskType.TODO.getInitial(), getDoneString(task),
                            task.getDescription()));
                } else if (task instanceof Deadline) {
                    fileWriter.write(String.join(SPLITTER, TaskType.DEADLINE.getInitial(), getDoneString(task),
                            task.getDescription(), ((Deadline) task).getBy()));
                } else if (task instanceof Event) {
                    fileWriter.write(String.join(SPLITTER, TaskType.EVENT.getInitial(), getDoneString(task),
                            task.getDescription(), ((Event) task).getAt()));
                } else {
                    assert (false) : "Tasks in taskList should have been in the correct format!";
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("I can't seem to handle the input/output properly...");
        }

    }
}
