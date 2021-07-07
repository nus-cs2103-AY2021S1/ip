import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a event command.
 */
public class EventCommand extends Command {

    /**
     * Constructs a event command object.
     *
     * @param str event command
     */
    EventCommand(String str) {
        super(str);
    }

    /**
     * Converts the date from user input to local date.
     *
     * @param time user input time
     * @return local date
     */
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
            String des = description + " (at: " + date + ")";
            Task newTask = new Event(des);
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
