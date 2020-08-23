/**
 * Class to handle delete commands entered by the user
 * @author vanGoghhh
 */

public class DeleteCommand extends Command {

    private String command;

    /**
     * Constructor for delete command object
     * @param command Complete line of delete command entered by user
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute entirely when a delete command is entered by the user
     * @param tasks Tasklist containing all the tasks
     * @param dukeUI UI to print string responses by the bot
     * @throws InvalidTaskNumberException when an inaccurate task number is entered
     */
    @Override
    protected void execute(TaskList tasks, UI dukeUI) throws InvalidTaskNumberException {
        try {
            String[] deleteCommand = this.command.split(" ");
            int taskIndex = Integer.parseInt(deleteCommand[1]);
            if (taskIndex > 0 && taskIndex <= tasks.getTaskList().size()) {
                Task deletedTask = tasks.getTaskList().get(taskIndex-1);
                tasks.deleteTask(deletedTask);
                dukeUI.deleteTask(tasks, deletedTask);
            } else {
                throw new InvalidTaskNumberException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Method to tell bot whether to end the current session
     * @return false to not exit the system
     */
    protected boolean isExit() {
        return false;
    }

}
