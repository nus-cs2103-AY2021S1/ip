package botbot.commands;

import java.time.LocalDateTime;
import java.util.Optional;

import botbot.tasks.Task;
import botbot.tasks.TaskList;
import botbot.ui.Ui;
import botbot.utils.Storage;

public class EditCommand extends Command {
    public static final String COMMAND_FORMAT = "edit INDEX [/d DESCRIPTION] [/at DATE_OF_EVENT "
            + "[TIME_OF_EVENT]] [/by DATE_OF_DEADLINE [TIME_OF_DEADLINE]] (at least one of /d, /at or /by "
            + "must be provided)";
    public static final String COMMAND_KEYWORD = "edit";
    private final int id;
    private final Optional<String> description;
    private final Optional<LocalDateTime> at;
    private final Optional<LocalDateTime> by;

    /**
     * Creates an edit command to edit the specified task.
     *
     * @param id ID of task to be edited.
     * @param description Description to be edited to.
     * @param at Time to be edited to.
     * @param by Deadline to be edited to.
     */
    public EditCommand(int id, Optional<String> description, Optional<LocalDateTime> at,
            Optional<LocalDateTime> by) {
        this.id = id;
        this.description = description;
        this.at = at;
        this.by = by;
    }

    /**
     * Executes the edit command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to edit task.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task task;
        try {
            task = tasks.get(id);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorResponse(CommandValidator.ERROR_MESSAGE_INVALID_TASK_ID);
        }
        
        try {
            tasks.edit(id, description, at, by);
        } catch (IllegalArgumentException e) {
            return ui.showErrorResponse(e.getMessage());
        }
        
        storage.save(tasks, 0);
        return ui.showEditResponse(task);
    }
}
