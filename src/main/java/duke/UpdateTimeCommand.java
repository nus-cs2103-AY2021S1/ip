package duke;

import java.time.LocalDate;

/**
 * Update task time.
 */
public class UpdateTimeCommand extends Command {
    private int idx;
    private LocalDate time;

    /**
     * UpdateDescriptionCommand constructor.
     *
     * @param idx  of the updated task
     * @param time the updated time.
     */
    public UpdateTimeCommand(int idx, LocalDate time) {
        super();
        this.idx = idx;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.updateTime(idx, time, ui);
        storage.writeToFile(taskList);
    }
}
