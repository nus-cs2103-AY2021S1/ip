package duke;
import java.time.LocalDate;


/**
 * Command for adding tasks into list
 */
public class AddCommand extends Command {
    private final String input;
    private final ActionType actionType;

    /**
     * AddCommand constructor
     *
     * @param input User input (without type of task written in front)
     * @param actionType Dictates type of task to be added
     */
    public AddCommand(String input, ActionType actionType) {
        super(false);
        this.input = input;
        this.actionType = actionType;
    }

    /**
     * Creates new task and adds it into list, then returns string containing task info
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @return String containing task info
     * @throws DukeException if exception encountered
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        switch(actionType) {
        case ADD_TODO:
            if (input.length() < 6) {
                throw new DukeException("Task can't be empty :(");
            } else {
                newTask = new ToDo(input.substring(5), false);
            }
            break;
        case ADD_EVENT:
            if (input.length() < 7) {
                throw new DukeException("Event can't be empty :(");
            } else if (!input.contains(" /at ")) {
                throw new DukeException(("The format of event command is: event project meeting /at 2019-10-15"));
            } else {
                String[] split = input.substring(6).split(" /at ");
                String eventDesc = split[0];
                LocalDate eventTime = LocalDate.parse(split[1]);
                newTask = new Event(eventDesc, eventTime, false);
            }
            break;
        case ADD_DEADLINE:
            if (input.length() < 10) {
                throw new DukeException("Deadline can't be empty :(");
            } else if (!input.contains(" /by ")) {
                throw new DukeException("The format of deadline command is: deadline return book /by 2019-10-15");
            } else {
                String[] split = input.substring(9).split(" /by ");
                String deadlineDesc = split[0];
                LocalDate deadline = LocalDate.parse(split[1]);
                newTask = new Deadline(deadlineDesc, deadline, false);
            }
            break;
        default:
            assert false : actionType; // only one of the three commands belongs to addcommand
        }

        tasks.addTask(newTask);
        storage.updateFile(tasks);
        return ui.printTask(newTask, actionType) + "\n" + ui.printTotalTasks(tasks);
    }
}
