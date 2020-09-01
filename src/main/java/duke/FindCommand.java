package duke;

public class FindCommand extends Command {
    public FindCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        // if length is not 2, nothing was passed in after 'find'
        if (getParsedCommand().length != 2) {
            throw new DukeException("NANI??! Tell us something to find!\n");
        }

        String inputDescription = getParsedCommand()[1].trim();
        boolean taskFound = false;
        ui.appendMessage("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i)
                    .getDescription()
                    .contains(inputDescription)) {
                taskFound = true;
                ui.appendMessage((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
        if (!taskFound) {
            ui.appendMessage("Nothing matches your search!\n");
        } else {
            ui.appendMessage("\n"); // print empty line for easier visualisation
        }
    }
}
