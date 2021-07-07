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
        boolean taskIsFound = false;
        ui.appendMessage("Here are all the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription();
            boolean matchingWordIsFound = taskDescription.contains(inputDescription);
            if (matchingWordIsFound) {
                taskIsFound = true;
                ui.appendMessage((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
        if (!taskIsFound) {
            ui.appendMessage("Nothing matches your search!\n");
        } else {
            ui.appendMessage("\n"); // print empty line for easier visualisation
        }
    }
}
