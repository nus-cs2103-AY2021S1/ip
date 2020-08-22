/**
 * Handles completion of Tasks.
 */

public class DoneCommand extends Command {
    /** Command details */
    private final String[] instructions;

    /**
     * Constructor for DoneCommand.
     * @param instructions Contains index of Task to be completed.
     */
    public DoneCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the Done Command, completing the Task at the index provided by the instructions.
     * @param tasks TaskList to be updated.
     * @param ui For user interaction.
     * @param storage To update the completed task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            ui.incompleteInstructionError();
        }
        int index = Integer.parseInt(instructions[1]) - 1;
        tasks.completeTask(index);
        storage.save(tasks);
    }
}

