public abstract class AddCommand extends Command {
    protected final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    protected void showAddTask(TaskList tasks, Ui ui, Task task) {
        ui.showPrompt("Got it. I've added this task:\n  "
                + task + "\n" + "Now you have " + tasks.getTasks().size()
                + (tasks.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
    }
}
