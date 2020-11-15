package duke.command;

import java.util.StringJoiner;

import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

public class FindCommand extends Command {
    private String[] stringsToFind;

    /**
     * Constructs a FindCommand.
     *
     * @param stringsToFind The strings used to search for tasks.
     */
    public FindCommand(String ... stringsToFind) {
        super(true);
        this.stringsToFind = stringsToFind;
    }

    /**
     * Executes a find command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when the command is completed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        StringJoiner userStrings = new StringJoiner("\n");

        for (String string : stringsToFind) {
            userStrings.add(String.format("\"%s\"", string));
        }

        response.add(String.format("Here are the tasks in your list that contain \n%s\n",
                userStrings.toString()));
        int numberOfTasksFound = 0;
        for (String toFind : stringsToFind) {
            if (!tasks.find(toFind).equals("")) {
                response.add(tasks.find(toFind));
                numberOfTasksFound++;
            }
        }

        if (numberOfTasksFound == 0) {
            return "No matching tasks found :(";
        } else {
            return response.toString();
        }
    }

    @Override
    public String toString() {
        return "find <keywords separated by ~>";
    }
}
