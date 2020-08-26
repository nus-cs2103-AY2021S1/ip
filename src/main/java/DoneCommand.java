public class DoneCommand extends EditCommand {
    DoneCommand(int taskNumber) {
        super(taskNumber);
    }

    @Override
    public String execute() {
        Task task = (Task) Command.tasks.get(super.taskNumber - 1);
        task.markAsDone();
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have marked this task as done: \n    ").append(task);

        Command.uncompletedTasks--;
        Command.completedTasks++;

        if (Command.uncompletedTasks == 0) {
            sb.append("\n").append("woohoo! you have no more uncompleted tasks left");
        } else {
            sb.append("\n").append(Command.numberOfTasks());
        }

        FileUpdater.updateFile();

        return sb.toString();
    }
}
