public class ToDoCommand extends TaskCommand{
    ToDoCommand(String description) {
        super(description);
    }

    @Override
    public String execute() throws EmptyDescriptionException {
        if (description.length() <= 5) {
            throw new EmptyDescriptionException("oops! the description of a todo cannot be empty");
        } else {
            ToDo todo = new ToDo(description);
            Command.tasks.add(todo);
            Command.uncompletedTasks++;
            return super.commandResult(todo);
        }
    }
}
