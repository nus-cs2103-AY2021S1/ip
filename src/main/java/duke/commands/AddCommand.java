package duke.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.errors.DukeException;
import duke.errors.FileAbsentException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.tasks.Task;

/**
 * This class handles the case of adding different tasks which are ToDo, deadline and Event
 */
public abstract class AddCommand extends Command {
    /**
     * constructor that assigns string value of string
     *
     * @param input passes it to super class constructor
     */
    public AddCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Gives a String saying that the task list has been updated
     *
     * @param task to be added into taskList
     * @param taskList where task is added
     * @return String that informs task is added into taskList
     */
    protected static String stringToUpdateTaskList(Task task, TaskList taskList) {
        return "  Got it. I've added this task:\n  " + task.toString() + "\n" + //Task added message
                "  Now you have " + taskList.getAllTasks().size() + " tasks in the list.";
    }

    /**
     * adds the task to list of task in taskList and into the file in storage
     *
     * @param storage where the file here is updated
     * @param task this task is added into storage and taskList
     * @param taskList where the tasks here is updated with task added
     * @throws DukeException when the file in storage is not present
     */
    protected static String updateTaskList(Storage storage, Task task, TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(storage.getFilePath(), true);
            //updates the file in storage as new task is added
            taskList.getAllTasks().add(task);
            fw.write(task.inputListFormat() + "\n");
            fw.close();
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
        return stringToUpdateTaskList(task, taskList);
    }

    /**
     * converts string to date
     *
     * @param string string to convert to date
     * @return local date which is converted from string, if cannot then DateTimeException thrown
     */
    protected static LocalDate stringToLocalDate(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter); //converts string to date
            return parsedDate;
        } catch (DateTimeException d) {
            throw d;
        }
    }
    /**
     * converts string to dateTime
     *
     * @param string string to convert to dateTime
     * @return local dateTime which is converted from string, if cannot then DateTimeException thrown
     */
    protected static LocalDateTime stringToLocalDateTime(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(string, formatter); //converts string to date and time
            return parsedDate;
        } catch (DateTimeException g) {
            throw g;
        }
    }

    /**
     * converts string to time
     *
     * @param string string to convert to time
     * @return local time which is converted from string, if cannot then DateTimeException thrown
     */
    protected static LocalTime stringToLocalTime(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedDate = LocalTime.parse(string, formatter); //converts string to time
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }
}
