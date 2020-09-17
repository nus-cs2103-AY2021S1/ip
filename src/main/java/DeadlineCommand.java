import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline command from a user
 * which adds a deadline task to task list.
 */
public class DeadlineCommand extends Command {

    DeadlineCommand(String str) {
        super(str);
    }

    public static String getLocalDate(String time) {
        LocalDate d = LocalDate.parse(time);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Adds a deadline task to task list.
     *
     * @param list A list of task.
     * @param ui Ui that prints out the output.
     * @param storage Storage that reads from and writes to hard disk.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            String string = str.substring(9);
            String description = string.split(" /by ")[0]; // split the stirng by "/by ", take first half
            String time = string.split(" /by ")[1];
            String date = getLocalDate(time);
            String des = description + " (by: " + date + ")";
            Task newTask = new Deadline(des);
            list.getList().add(newTask);
            s = ui.printAddTask(newTask, list.getList().size());
        } catch (IndexOutOfBoundsException e) {
            s = ui.printNoDate();
        } catch (DateTimeParseException e) {
            s = ui.printInvalidDate();
        }
        return s;
    }
}
