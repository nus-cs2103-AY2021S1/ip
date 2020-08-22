public class DeleteCommand extends Command {

    private String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    // Method to get index for the task to be deleted
    protected int getDeletedTaskIndex() throws DukeException {
        try {
            String[] deleteCommand = this.command.split(" ");
            return Integer.parseInt(deleteCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

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

    protected boolean isExit() {
        return false;
    }

}
