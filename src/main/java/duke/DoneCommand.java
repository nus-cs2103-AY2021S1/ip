package duke;

/**
 * Handles completion of Tasks.
 */

public class DoneCommand extends Command {
    /** duke.Command details in the form [TYPE, INFORMATION] */
    private final String[] instructions;

    /**
     * Constructor for duke.DoneCommand.
     * @param instructions Contains index of duke.Task to be completed.
     */
    public DoneCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the Done duke.Command, completing the duke.Task at the index provided by the instructions.
     * @param tasks duke.TaskList to be updated.
     * @param ui For user interaction.
     * @param storage To update the completed task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            return ui.incompleteInstructionError(); // User did not provide index of task to mark done.
        }

        try {
            int index = Integer.parseInt(instructions[1]) - 1; // instructions[1] contains the index of task.
            return tasks.completeTask(index) + "\n" + storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ui.invalidIndexError();
        }
    }
}

