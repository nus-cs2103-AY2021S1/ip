public class TodoCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException {
        String description = Parser.parseTodo(input);
        Task task = new Todo(description);
        taskList.add(task);
        ui.add(task);
    }
}
