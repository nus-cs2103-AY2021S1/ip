import java.util.regex.Pattern;

public class TodoCommand extends Command{
    public TodoCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile("todo ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");

        if(!pattern.matcher(inputCommand).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'todo' command format!");
        } else {
            Todo todo = new Todo(inputCommand.substring(5));
            list.getList().add(todo);

            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(todo.toString());
            ui.printMessage("Now you have " + list.getList().size() + " task(s) in the list.");

            storage.save(list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
