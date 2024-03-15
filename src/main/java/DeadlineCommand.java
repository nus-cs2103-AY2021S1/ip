import java.time.format.DateTimeParseException;

/**
 * Deadline Command that executes deadline actions.
 * Extends from Command class.
 */
public class DeadlineCommand extends Command {

    /**
     * Creates a Deadline command object that handles deadline actions
     * @param ui ui that handles user interface
     * @param taskList list of tasks
     * @param args String of deadline commands
     */
    public DeadlineCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for deadline command
     * @return String containing a successful add message
     */
    @Override
    public String action() {
        String result;
        String[] deadlineArguments = Parser.splitDeadlineArguments(args);
        try {
            if (deadlineArguments.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty!");
            }
            String description = deadlineArguments[0];
            if (!deadlineArguments[1].equals("")) {
                throw new DukeException("The deadline of the task cannot be empty!");
            } else {
                String due = deadlineArguments[1];
                Task deadline = new Deadline(description, due);
                taskList.addTask(deadline);
                result = ui.printAdd(taskList, deadline);
            }

        } catch (DukeException e) {
            result = ui.printDukeError(e);
        } catch (DateTimeParseException e) {
            result = ui.printError("Please use this format: \n"
                    + "dd-MM-yyyy HHmm");
        }
        return result;
    }
}
