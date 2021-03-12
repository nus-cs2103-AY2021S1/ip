package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;

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
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will record any changes into the file in its path.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) throws DukeException {
        StringBuilder output = new StringBuilder(" Here are the tasks containing the keyword ")
                .append(String.format("\"%s\"", keyword)).append(":\n");

        int numbering = 1;
        boolean isUnavailable = true;
        Task task;

        for (int i = 0; i < mainTasks.size(); i++) {
            task = mainTasks.get(i);
            assert task != null : "task should not be null";

            if (task.hasKeyword(keyword)) {
                if (isUnavailable) {
                    isUnavailable = false;
                }

                output.append(" ").append(numbering).append(".").append(task).append("\n");
                numbering++;
            }
        }

        if (isUnavailable) {
            return "There are no tasks containing the keyword "
                    + String.format("\"%s\"", keyword) + "!\n";
        } else {
            return output.toString();
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
