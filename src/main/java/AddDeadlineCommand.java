import java.time.format.DateTimeParseException;

/**
 * Represents command that adds deadline-typed tasks.
 */
public class AddDeadlineCommand extends AddCommand {
    protected String by;

    /**
     * Constructs command object that adds deadline-typed tasks
     * @param taskDescription literal description of the task
     * @param by date in YYYY-MM-DD format as a String
     */

    public AddDeadlineCommand(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Executes the command.
     * @param tasks TaskList instance used by Duke in this session
     * @param ui Ui instance used by Duke in this session
     * @param storage Storage instance used by Duke in this session
     * @throws DateTimeParseException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws 
            DateTimeParseException {
        Task newTask = new Deadline(taskDescription, by);
        tasks.add(newTask);
        ui.showAdded(newTask, tasks.size());
        
        storage.save(tasks);
    }
}
