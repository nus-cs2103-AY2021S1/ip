/**
 * Class to handle delete commands entered by the user.
 * @author vanGoghhh
 */

public class DoneCommand extends Command {

    private String command;

    /**
     * Constructor for done command object.
     * @param command Complete line of done command entered by user.
     */
    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute entirely when a done command is entered by the user.
     * @param tasks Tasklist containing all the tasks.
     * @param dukeUI UI to print string responses by the bot.
     * @return String response by the bot.
     * @throws InvalidTaskNumberException when an inaccurate task number is entered.
     */
    @Override
    protected String execute(TaskList tasks, UI dukeUI) throws InvalidTaskNumberException {
        int taskIndex;
        try {
            String[] doneCommand = this.command.split(" ");
            taskIndex = Integer.parseInt(doneCommand[1]);
            if (taskIndex > 0 && taskIndex <= tasks.getTaskList().size()) {
                Task completedTask = tasks.getTaskList().get(taskIndex-1);
                String dukeResponse = dukeUI.doneTask(completedTask);
                tasks.markTaskDone(completedTask);

                return dukeResponse;
            } else {
                throw new InvalidTaskNumberException();
            }
        } catch (InvalidTaskNumberException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
