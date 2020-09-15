package duke.action;

import java.util.Optional;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * This represents and Action to be carried out that
 * updates a Task in the TaskList.
 */
public class UpdateAction extends Action {

    private final int taskNumber;
    private final TaskList tasks;
    private Task taskToUpdate;
    private final Ui ui;

    /**
     * Constructs a new UpdateAction.
     *
     * @param ui System Ui to be used.
     * @param tasks Current TaskList maintained by Duke.
     * @param taskNumber List number of Task to be updated.
     */
    public UpdateAction(Ui ui,
                        TaskList tasks,
                        int taskNumber) {
        this.taskNumber = taskNumber;
        this.tasks = tasks;
        this.ui = ui;
        this.taskToUpdate = tasks.retrieve(taskNumber).duplicate();
    }

    /**
     * Shows message that prompts User to enter changes in the text field.
     */
    @Override
    public void prompt(Ui ui) {
        ui.showUpdateActionMessage();
    }

    /**
     * Calls method:modifyTaskToUpdate and sends a ConfirmAction to be enqueued.
     * @param input Proposed changes that have been input by the User.
     * @return      An Optional containing a ConfirmAction.
     */
    @Override
    public Optional<Action> receiveInputAndGetNextAction(String input) throws DukeException {
        modifyTaskToUpdate(input);
        return Optional.of(new ConfirmAction(ui, this));
    }

    /**
     * Replaces Task in TaskList with a new modified Task.
     * And sends Ui to print out message to show that the Task
     * has been updated successfully.
     */
    public void execute() {
        tasks.replace(taskNumber, taskToUpdate);
        ui.showUpdateMessage(taskToUpdate, taskNumber);
    }

    /**
     * Parses input from User and updates the duplicate copy of the Task to
     * contain the changes. Modified Task is stored as class attribute
     * 'taskToUpdate' and is only applied to the TaskList once execute() is called.
     *
     * @param input Proposed changes that have been input by the User.
     */
    private void modifyTaskToUpdate(String input) throws DukeException {
        boolean isEmpty = input.length() == 0;
        boolean isJustWhiteSpace = input.split(" ").length == 0;
        if (isEmpty || isJustWhiteSpace) {
            throw new DukeException("Input cannot be empty.");
        }

        Optional<String> taskIdentifier = taskToUpdate.getFieldIdentifier();
        if (taskIdentifier.isPresent() && input.contains(taskIdentifier.get())) {
            String[] inputArray = input.split(taskIdentifier.get(), 2);
            String description = inputArray[0].strip();
            String fieldContent = inputArray[1].strip();

            if (description.length() == 0) {
                description = taskToUpdate.getDescription();
            }

            taskToUpdate.setDescription(description);
            taskToUpdate.setField(fieldContent);
        } else {
            // Task does not expect any field identifiers.
            taskToUpdate.setDescription(input);
        }
    }
}
