public class DeleteCommand extends Command {

    public DeleteCommand(String description) throws IllegalArgumentException {
        super(description); //the task number to delete
    }

    public void execute(TaskList taskList) {
        int taskNumber;
        System.out.println("-------------------------------------------\n" +
                " Noted. I've removed this task:\n");
        taskNumber = Integer.parseInt(this.getTaskName());
        Task deletedTask = taskList.getTask(taskNumber);
        taskList.removeTask(deletedTask);
        System.out.println(deletedTask + "\n-------------------------------------------");
    }
}
