public class DoneCommand extends Command {

    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NoSuchTaskException {
        Task taskDone = taskList.markTaskAsDone(taskIndex);
        ui.show(String.format("\t Nice! I've marked this task as done:\n\t\t%s", taskDone.toString()));
    }
}
