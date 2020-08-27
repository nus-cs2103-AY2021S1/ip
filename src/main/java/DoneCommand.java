
public class DoneCommand extends Command {
    private int taskNum;
    
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

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