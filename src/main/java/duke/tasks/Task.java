package duke.tasks;

import duke.errors.DukeException;
import duke.errors.FileAbsentException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This Task is made abstract because it is never initialized in the actual code, however,
 * it is used so that polymorphism
 * is able to work properly.
 */
public abstract class Task {
    /**
     * Tasks is made static because it contains the different tasks that are added, and therefore it is not limited to
     * a single instance of Task
     */
    private boolean done; //true if Task is completed, false otherwise
    private String name; //gives name of the Task
    /**
     * constructor assigns name variable a value
     *
     * @param name this assigns the name of the Task to the name being given in the constructor
     */
    Task(String name) {
        this.done = false;
        this.name = name;
    }

    /**
     * constructor assigns name and done a value.
     *
     * @param name assigns name to this.name
     * @param done assigns name to this.done
     */
    Task(String name, boolean done) {
        this.done = done;
        this.name = name;
    }
    /**
     * gives name of task
     *
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * setter that sets Done to the done value stated
     *
     * @param done value given to set it to done var
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Overrides the toString methods
     *
     * @return String which contains info on task name as well as whether it is completed(tick sign) or not(cross sign).
     */
    public String toString() {
        if (this.done) {
            return "[" + "\u2713" + "] " + this.name; //\u2713 is a tick, denoting done
        } else if (!this.done) {
            return "[" + "\u2717" + "] " + this.name; //\u2717 is a cross, deonoting not done
        } else {
            return "default";
        }
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file
     *
     * @return the string representation
     */
    public String inputListFormat() { //format of Tasks to appear in file in Storage
        if (this.done) {
            return " | 1 | " + this.name; //1 denotes done
        } else if (!this.done) {
            return " | 0 | " + this.name; //0 denotes not done
        } else {
            return "default";
        }
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
    protected static LocalDate stringToLocalDateExistingTask(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy");
            LocalDate parsedDate = LocalDate.parse(string, formatter); //converts string to time
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }
    protected static LocalDateTime stringToLocalDateTimeExistingTask(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(string, formatter); //converts string to time
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }
    protected static LocalTime stringToLocalTimeExistingTask(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedDate = LocalTime.parse(string, formatter); //converts string to time
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
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
}
