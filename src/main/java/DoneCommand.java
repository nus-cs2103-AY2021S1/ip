/**
 * Represents the done command which allows users to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Creates a DoneCommand object.
     * 
     * @param taskNum the task number that is done
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes marking the task as done. The user will be notified through the 
     * printed messages by the ui and the tasks are saved.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used 
     * @param storage the current Storage object being used
     * @throws PandaBotException If any errors occurs when executing the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
            // mark as done
            Task t = tasks.getTaskAt(taskNum);
            t.markTaskDone();

            // print
            ui.printOnDone(tasks.getTaskAt(taskNum));

            // save the changes
            storage.write(tasks.getTaskList());
    }

}