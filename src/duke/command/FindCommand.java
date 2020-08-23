package duke.command;

import duke.exception.DukeException;

import duke.task.Task;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

/**
 * Represents a FindCommand for finding tasks with specific keywords.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates an instance of a FindCommand.
     *
     * @param keyword The keyword to search for in the description of the Tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks which have the specified keyword in their description and then proceeds to list them.
     *
     * @param tasks The TaskList which contains all the tasks.
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will record any changes into the file in its path.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        StringBuilder output = new StringBuilder("\t Here are the tasks containing the keyword ")
                .append(String.format("\"%s\"", keyword)).append(":\n");

        int numbering = 1;
        boolean isUnavailable = true;
        Task task;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (task.hasKeyword(keyword)) {
                if (isUnavailable) {
                    isUnavailable = false;
                }
                output.append("\t ").append(numbering).append(".").append(task).append("\n");
                numbering++;
            }
        }

        if (isUnavailable) {
            ui.showMessage("\tThere are no tasks containing the keyword "
                    + String.format("\"%s\"", keyword) + "!\n");
        } else {
            ui.showMessage(output.toString());
        }
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after listing all the tasks
     *         containing the specified keyword.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
