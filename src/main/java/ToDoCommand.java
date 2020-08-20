public class ToDoCommand extends TaskCommand{
    ToDoCommand(String description) {
        super(description);
    }

    @Override
    public String execute() {
        ToDo todo = new ToDo(description);
        Command.tasks.add(todo);
        Command.uncompletedTasks ++;
        return super.commandResult(todo);
    }
}
