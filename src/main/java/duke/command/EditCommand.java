package duke.command;

import java.util.Arrays;
import java.util.List;

import duke.edit.Edit;
import duke.edit.EditingException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This Command edits a Task or its subclasses. If the user tries an Edit which is not applicable to the task specified
 * (eg. edit date of a Task, edit start date of a Deadline), an error message is shown.
 *
 * @param <T> The type of Task to edit.
 */
public class EditCommand<T extends Task> implements Command {
    private int taskNumber;
    private List<Edit<? super T>> edits;

    /**
     * Creates an EditCommand.
     *
     * @param taskNumber Number of the Task to edit.
     * @param edits Edits to apply.
     */
    @SafeVarargs
    public EditCommand(int taskNumber, Edit<? super T>... edits) {
        this.taskNumber = taskNumber;
        this.edits = Arrays.asList(edits);
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        try {
            Helper.validateTaskNumber(taskNumber, list);
            // ClassCastException is expected if user tries to edit the wrong type of task, eg. edit date of a Task.
            // It is necessary to rely on the exception since instanceof is not allowed for generics.
            @SuppressWarnings("unchecked")
            T task = (T) list.get(taskNumber - 1);
            for (Edit<? super T> edit : edits) {
                edit.apply(task);
            }
            ui.say("Okay, edited this:\n  " + task.displayString());
        } catch (InvalidTaskNumberException | EditingException e) {
            ui.say(e.getMessage(), true);
        } catch (ClassCastException classCastException) {
            ui.say("Wrong type of task!", true); // TODO: better error message
        }
    }
}
