package duke;

/**
 * Handles deletion of Tasks.
 */

public class DeleteCommand extends Command {
    /** duke.Command details */
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            ui.incompleteInstructionError();
        }
        int index = Integer.parseInt(instructions[1]) - 1;
        if (index >= tasks.getSize()) {
            ui.deleteError();
        } else {
            tasks.deleteTask(index);
            storage.save(tasks);
        }
    }
}

