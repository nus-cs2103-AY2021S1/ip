public class AddTodoCommand extends Command {
    private final ToDoTask toDoTask;

    public AddTodoCommand(ToDoTask toDoTask) {
        this.toDoTask = toDoTask;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {

    }

    @Override
    public boolean isBye() {
        return false;
    }
}
