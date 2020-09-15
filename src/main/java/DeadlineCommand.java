/**
 * Class that makes a deadline command which genereates the deadline reminder.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Constructs a DeadlineCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Generates the Deadline task.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyDeadlineException     If there is empty message.
     * @throws DukeEmptyDeadlineTimeException If date is not in the right format.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyDeadlineException, DukeEmptyDeadlineTimeException {
        String message = "";
        try {
            if (input.split(" ").length == 1) {
                throw new DukeEmptyDeadlineException(input);
            }
            String deadlinerMessage = Parser.stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
            String[] deadlinerMessageParts = deadlinerMessage.split(" /by ");
            if (deadlinerMessageParts.length == 1) {
                throw new DukeEmptyDeadlineTimeException(input);
            }
            Deadline deadlineTask = new Deadline(deadlinerMessageParts[0], deadlinerMessageParts[1]);
            tasklist.addTask(deadlineTask);
            message = ui.printTaskAdd(deadlineTask, tasklist.numOfTasks());
        } catch (DukeEmptyDeadlineException e) {
            message = e.getMessage();
        } catch (DukeEmptyDeadlineTimeException e) {
            message = e.getMessage();
        }
        return message;
    }
}
