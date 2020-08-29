public class DoneCommand extends Command {
    DoneCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int taskNumber = Integer.parseInt(fullCommand.substring(5));

        Task task = tasks.done(taskNumber);

        System.out.println(doneTaskMessage(task, tasks));
        storage.save(tasks);
    }

    public String doneTaskMessage(Task deletedTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have marked this task as done: \n    ")
                .append(deletedTask).append("\n")
                .append(tasks.numberOfTasks());
        return sb.toString();
    }
}
