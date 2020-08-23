import java.io.IOException;

public class DoneCommand extends Command {

    private String[] splitCommand;

    public DoneCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoneWrongFormatException,
            TaskIndexOutOfBoundsException {
        try {
            if (splitCommand.length != 2) { // If command is in a wrong format
                throw new DoneWrongFormatException();
            }
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1; // Index of task in the task list
            Task completedTask = tasks.getTask(taskIndex);
            completedTask.markAsDone();
            ui.showReplyForDoneTask(completedTask);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) { // Second argument of command was not a number, e.g. "done X"
            throw new DoneWrongFormatException();
        } catch (IndexOutOfBoundsException e) { // User requests for a task with an index not within the current task
            // list
            throw new TaskIndexOutOfBoundsException(splitCommand[1]);
        }
    }
}
