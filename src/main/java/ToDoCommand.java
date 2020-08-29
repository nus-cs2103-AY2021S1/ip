public class ToDoCommand extends TaskCommand {
    ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        ToDo todo = new ToDo(fullCommand);
        tasks.add(todo);
        storage.save(tasks);
        System.out.println(addedTaskMessage(todo, tasks));
    }
}
