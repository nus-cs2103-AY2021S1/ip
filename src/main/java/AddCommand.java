import java.util.ArrayList;

public class AddCommand extends Command {

    private CommandName commandName;
    private String description;
    private String date;

    public AddCommand(CommandName commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }

    public AddCommand(CommandName commandName, String description, String date) {
        this.commandName = commandName;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = tasks.addTask(commandName, description, date);
        ArrayList<Task> taskList = tasks.getTasks();
        ui.printOutput(output, true);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
