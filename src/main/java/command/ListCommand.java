package command;

import duke.Storage;
import duke.TaskList;
import task.Task;

/**
 * Represents a ListCommand for adding listing all existing tasks.
 */
public class ListCommand extends Command {
    private boolean listAll;

    /**
     * Creates an instance of a ListCommand.
     *
     * @param listAll Indicates whether the ListCommand has to list unarchived tasks too when executed.
     */
    public ListCommand(boolean listAll) {
        this.listAll = listAll;
    }

    /**
     * Lists the tasks in the TaskList.
     *
     * @param mainTasks The TaskList which stores unarchived tasks.
     * @param archivedTasks The TaskList which stores archived tasks.
     * @param storage The Storage which will record any changes into the file in its path.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList mainTasks, TaskList archivedTasks, Storage storage) {
        assert mainTasks.size() >= 0 : "unarchivedTasks should not have a negative size";
        assert archivedTasks.size() >= 0 : "archivedTasks should not have a negative size";

        if (!this.listAll) {
            return getListMainOutput(mainTasks);
        } else {
            return getListAllOutput(mainTasks, archivedTasks);
        }
    }

    private String getListAllOutput(TaskList mainTasks, TaskList archivedTasks) {
        boolean hasArchivedTasks = archivedTasks.size() != 0;
        String mainOutput = getListMainOutput(mainTasks);

        if (!hasArchivedTasks) {
            return mainOutput;
        }

        StringBuilder output = new StringBuilder(mainOutput);

        output.append("\n").append("\t Here are your archived tasks:\n");

        for (int i = 1; i <= archivedTasks.size(); i++) {
            Task theTask = archivedTasks.get(i - 1);
            assert theTask != null : "theTask should not be null";
            output.append("\t ").append(i).append(".").append(theTask).append("\n");
        }

        return output.toString();
    }

    private String getListMainOutput(TaskList mainTasks) {
        boolean hasMainTasks = mainTasks.size() != 0;

        if (!hasMainTasks) {
            return "\tYay! You have nothing to do at the moment! :-)\n";
        }

        StringBuilder output = new StringBuilder("\t Here are the tasks in your list:\n");

        for (int i = 1; i <= mainTasks.size(); i++) {
            Task theTask = mainTasks.get(i - 1);
            assert theTask != null : "theTask should not be null";
            output.append("\t ").append(i).append(".").append(theTask).append("\n");
        }

        return output.toString();
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on listing all the tasks.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
