import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents the command to add tasks to the existing task list. An object of this class has the fields type, name,
 * date, and time. Implements the execute method from the Command class.
 */
public class AddCommand extends Command {
    private final TaskType type;
    private final String name;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Public Constructor.
     *
     * @param type Type of task.
     * @param name Name of task.
     * @param date Date of the deadline or event.
     * @param time Time of the deadline or event.
     */
    public AddCommand(TaskType type, String name, LocalDate date, LocalTime time) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlankTaskException, IOException {
        tasks.add(type, name, date, time);
        ui.showAdd(tasks.size(), tasks.getLastTask());
        storage.updateMemory(tasks.getList());
    }

    /**
     * Returns true if the other object is an AddCommand instance with the same name, date, time.
     *
     * @param o Object in comparison.
     * @return true if the given object represents an AddCommand equivalent to this AddCommand, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddCommand that = (AddCommand) o;
        return type == that.type &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time);
    }

    /**
     * Returns a hash code for this AddCommand. The hashcode depends on the type, name, date, and time of the
     * AddCommand.
     *
     * @return Hash code for this AddCommand.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, name, date, time);
    }
}
