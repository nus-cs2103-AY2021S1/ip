package main.java;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new NoTaskException();
        } else {
            String message = ui.showList(tasks);
            ui.sendMessage(message);
        }
    }
}
