public class TodoCommand extends Command {

    public TodoCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    public void execute(TaskList taskList) {
        Todo newTask = new Todo(this.getTaskName());
        taskList.addTask(newTask);
        System.out.println(newTask +
                String.format("\nNow you have %d tasks in the list.\n", taskList.getTaskLength())
                + "-------------------------------------------");
    }
}
