public class CommandAdd implements Command {
    private Task newTask;

    public CommandAdd(Task newTask) { this.newTask = newTask; }

    @Override
    public void execute(TaskList tasks, Ui ui) {
       ui.printLine("Got it. I've added this task:");
       ui.printLine(newTask.toString());
       tasks.add(newTask);
       ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
    }

    public boolean isExit() { return false; }
}
