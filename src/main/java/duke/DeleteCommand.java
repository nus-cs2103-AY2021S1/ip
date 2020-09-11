package duke;

/**
 * Handles deletion of Tasks.
 */

public class DeleteCommand extends Command {
    /** duke.Command details in the form [TYPE, INFORMATION] */
    private final String[] instructions;

    /**
     * Constructor for duke.DeleteCommand.
     * @param instructions Contains index of duke.Task to be deleted.
     */
    public DeleteCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the Delete duke.Command, deleting the duke.Task at the index provided by the instructions.
     * @param tasks duke.TaskList to be updated.
     * @param ui For user interaction.
     * @param storage To remove the deleted task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            return ui.incompleteInstructionError(); // User did not provide index of task for deletion.
        }

        try {
            int index = Integer.parseInt(instructions[1]) - 1; // instructions[1] contains the index of task.
            if (index >= tasks.getSize()) {
                return ui.deleteError();
            } else {
                return tasks.deleteTask(index) + "\n" + storage.save(tasks);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ui.invalidIndexError();
        }
    }
}

