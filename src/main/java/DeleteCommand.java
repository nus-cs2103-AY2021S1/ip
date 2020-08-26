public class DeleteCommand extends EditCommand {
    DeleteCommand(int taskNumber) {
        super(taskNumber);
    }

    @Override
    public String execute () {
        Task task = (Task) Command.tasks.remove(super.taskNumber - 1);

        StringBuilder sb = new StringBuilder();
        sb.append("sure thing. i have removed this task: \n    ")
                .append(task).append("\n");

        if (task.isDone()) {
            Command.completedTasks--;
        } else {
            Command.uncompletedTasks--;
        }

        sb.append(Command.numberOfTasks());

        FileUpdater.updateFile();

        return sb.toString();
    }
}
