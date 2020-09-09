package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

// TODO: fix unsafe generic stuff
/**
 * This Command edits a Task or its subclasses.
 *
 * @param <T> The type of Task to edit.
 */
public class EditCommand<T extends Task> implements Command {
    private int taskNumber;
    private Edit<T>[] edits;

    /**
     * Creates an EditCommand.
     *
     * @param taskNumber The number of the Task to edit.
     * @param edits The edits to apply.
     */
    public EditCommand(int taskNumber, Edit<T>... edits) {
        this.taskNumber = taskNumber;
        this.edits = edits;
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        try {
            Helper.validateTaskNumber(taskNumber, list);
        } catch (InvalidTaskNumberException e) {
            ui.say(e.getMessage());
        }

        try {
            T task = (T) list.get(taskNumber - 1);
            for (Edit<T> edit : edits) {
                try {
                    edit.apply(task);
                } catch (EditingException editingException) {
                    ui.say(editingException.getMessage());
                }
            }
            ui.say("Okay, edited this:\n  " + task.displayString());
        } catch (ClassCastException e) {
            ui.say("Wrong type of task!"); // TODO: better error message
        }
    }
}
