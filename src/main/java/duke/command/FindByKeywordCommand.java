package duke.command;

import duke.exception.InvalidFunctionException;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

import duke.exception.DukeException;


public class FindByKeywordCommand extends Command {

    private final String[] parsedCommand;

    public FindByKeywordCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            try {
                String keyword = this.parsedCommand[1].trim().toLowerCase();
                int index = 1;
                ui.printReply("Search Results:");
                for (Task task : tasks.getTaskList()) {
                    if (task.getDescription().toLowerCase().contains(keyword)) {
                        String results = String.format("%d. %s", index, task);
                        ui.printReply(results);
                        index++;
                    }
                }
                if (index == 1) {
                    ui.printReply("No tasks found! Please search using a different keyword!");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                String err = "No keyword for the search was entered. Please enter a keyword!";
                throw new InvalidFunctionException(err);
            }

    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
