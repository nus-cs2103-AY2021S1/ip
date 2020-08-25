package Duke.Commands;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the case of adding different tasks which are todo, deadline and event
 */
abstract public class AddCommand extends Command {
    /**
     * constructor that assigns string value of string
     * @param string passes it to super class constructor
     */
    public AddCommand(String string) {
        super(string);
    }

    /**
     * adds the task to list of task in taskList and into the file in storage
     * @param storage where the file here is updated
     * @param task this task is added into storage and taskList
     * @param taskList where the tasks here is updated with task added
     * @throws IOException when the file in storage is not present
     */
    public static void update(Storage storage, Task task, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(storage.getFilePath(), true);
        taskList.getAllTasks().add(task);
        fw.write(task.inputListFormat() + "\n");
        fw.close();
        Task.tasks.add(task);
        System.out.println("  Got it. I've added this task:\n  " + task.toString() + "\n" +
                "  Now you have " + taskList.getAllTasks().size() + " tasks in the list.");
    }

    /**
     * converts string to date
     * @param string string to convert to date
     * @return local date which is converted from string, if cannot then DateTimeException thrown
     */
    public static LocalDate localDate(String string){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter);
            return parsedDate;
        }catch (DateTimeException d) {
            throw d;
        }
    }
    /**
     * converts string to dateTime
     * @param string string to convert to dateTime
     * @return local dateTime which is converted from string, if cannot then DateTimeException thrown
     */
    public static LocalDateTime localDateTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException g) {
            throw g;
        }
    }

    /**
     * converts string to time
     * @param string string to convert to time
     * @return local time which is converted from string, if cannot then DateTimeException thrown
     */
    public static LocalTime localTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedDate = LocalTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }

}
