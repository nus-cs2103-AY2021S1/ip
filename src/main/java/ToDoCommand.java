public class ToDoCommand extends Command {
    ToDoCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        System.out.println(tasks.add(new ToDo(fullCommand)));
        storage.save(tasks);
    }
}
