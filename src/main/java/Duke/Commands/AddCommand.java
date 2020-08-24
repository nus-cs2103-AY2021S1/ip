package Duke.Commands;
import Duke.Errors.DeadlineException;
import Duke.Errors.DukeException;
import Duke.Errors.EventException;
import Duke.Errors.TodoException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Deadline;
import Duke.Tasks.Task;
import Duke.Tasks.event;
import Duke.Tasks.todo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    public AddCommand(String string) {
        super(string);
    }
    public static void update(Storage storage, Task task, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(storage.getFilePath(), true);
        taskList.getAllTasks().add(task);
        fw.write(task.inputListFormat() + "\n");
        fw.close();
        Task.tasks.add(task);
        System.out.println("  Got it. I've added this task:\n  " + task.toString() + "\n" +
                "  Now you have " + taskList.getAllTasks().size() + " tasks in the list.");
    }
    public static LocalDate localDate(String string){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter);
            return parsedDate;
        }catch (DateTimeException d) {
            throw d;
        }
    }
    public static LocalDateTime localDateTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException g) {
            throw g;
        }
    }
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
