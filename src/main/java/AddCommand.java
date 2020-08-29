public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(task);
        storage.save(tasks);
        int numOfTasks = tasks.size();
        ui.printStatus(String.format("    ok! I've added this task:\n      %s\n    you now have %d task"
                + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
    }
}