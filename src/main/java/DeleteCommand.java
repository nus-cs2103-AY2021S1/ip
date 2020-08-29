public class DeleteCommand extends Command {
    DeleteCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int taskNumber = Integer.parseInt(fullCommand.substring(7));

        Task task = tasks.delete(taskNumber);

        System.out.println(deletedTaskMessage(task, tasks));
        storage.save(tasks);
    }

    public String deletedTaskMessage(Task deletedTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("sure thing. i have removed this task: \n    ")
                .append(deletedTask).append("\n")
                .append(tasks.numberOfTasks());
        return sb.toString();
    }
}
