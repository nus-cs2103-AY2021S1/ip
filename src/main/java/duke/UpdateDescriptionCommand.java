package duke;

/**
 * Update task description.
 */
public class UpdateDescriptionCommand extends Command {
    private int idx;
    private String description;

    /**
     * UpdateDescriptionCommand constructor.
     *
     * @param idx         of the updated task
     * @param description the updated description.
     */
    public UpdateDescriptionCommand(int idx, String description) {
        super();
        this.idx = idx;
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.updateDescription(idx, description, ui);
        storage.writeToFile(taskList);
    }
}
