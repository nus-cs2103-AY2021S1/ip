import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a event command from a user.
 */
public class EventCommand extends Command {

    EventCommand(String str) {
        super(str);
    }

    public static String getLocalDate(String time) {
        LocalDate d = LocalDate.parse(time);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Adds a event task to task list.
     *
     * @param list A list of task.
     * @param ui Ui that prints out the output.
     * @param storage Storage that reads from and writes to hard disk.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            String string = str.substring(6);
            String description = string.split(" /at ")[0]; // split the stirng by "/at ", take first half
            String time = string.split(" /at ")[1];
            String date = getLocalDate(time);
            Task newTask = new Event(description, date);
            list.getList().add(newTask);
            s = ui.printAddTask(newTask, list.getList().size());
        } catch (IndexOutOfBoundsException e) {
            s = ui.printNoDateEvent();
        } catch (DateTimeParseException e) {
            s = ui.printInvalidDate();
        }
        return s;
    }
}
