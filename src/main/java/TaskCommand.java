public abstract class TaskCommand extends Command {
    protected String description;

    TaskCommand(String description) {
        this.description = description;
    }

    protected String commandResult(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have added this task to your list: \n    ")
                .append(task).append("\n")
                .append(Command.numberOfTasks());
        FileUpdater.updateFile();
        return sb.toString();
    }
}
