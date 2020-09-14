/**
 * Abstract command class that extends the different types of reminders.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, UI ui) throws DukeException;
}
