public abstract class TaskCommand extends Command {
    TaskCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    public String addedTaskMessage(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("got it! i have added the following task to your list:\n    ")
                .append(task)
                .append("\n" + tasks.numberOfTasks());

        return sb.toString();
    }
}
