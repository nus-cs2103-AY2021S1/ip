public class DoneCommand extends Command {

    private String command;

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    protected void execute(TaskList tasks, UI dukeUI) throws InvalidTaskNumberException {
        int taskIndex;
        try {
            String[] doneCommand = this.command.split(" ");
            taskIndex = Integer.parseInt(doneCommand[1]);
            if (taskIndex > 0 && taskIndex <= tasks.getTaskList().size()) {
                Task completedTask = tasks.getTaskList().get(taskIndex-1);
                if (!completedTask.getStatus()) {
                    completedTask.markAsDone();
                }
                dukeUI.doneTask(completedTask);
            } else {
                throw new InvalidTaskNumberException();
            }
        } catch (InvalidTaskNumberException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    protected boolean isExit() {
        return false;
    }

}
