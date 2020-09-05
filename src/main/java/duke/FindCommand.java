package duke;

import java.util.Arrays;

/**
 * Handles searching of tasks given a search phrase.
 */

public class FindCommand extends Command {
    /** duke.Command details in the form [TYPE, INFORMATION] */
    private final String[] instructions;

    /**
     * Constructor for duke.FindCommand.
     * @param instructions Contains search phrase.
     */
    public FindCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the Find duke.Command, displaying a list of tasks that have the search phrase.
     * @param tasks duke.TaskList to be searched.
     * @param ui For user interaction.
     * @param storage Unused.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        response.append(">> Your matching tasks:");

        boolean hasResult = false;
        int i = 1;
        for (Task task : tasks.getTasks()) {
            assert task.name != null : "Task name could not be found!";

            if (task.name.contains(instructions[1]) || (task.tags != null &&
                    Arrays.asList(task.tags).contains(instructions[1]))) { // instructions[1] contains the search query
                response.append("\n>> " + i++ + ". " + task);
                hasResult = true;
            }
        }

        if (!hasResult) {
            return ">> No results!";
        }

        return response.toString();
    }
}

