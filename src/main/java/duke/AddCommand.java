package duke;

public class AddCommand extends Command {
    private final String command;
    private final String input;

    public AddCommand(String command, String input) {
        this.command = command;
        this.input = input;
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.addTask(command, input);
        String output = "Got it. I've added this task: \n";
        output += task;
        output += "\nNow you have " + list.size() + " tasks in the list.";
        storage.updateTextFile(list);
        ui.printLine(output);
    }
}
