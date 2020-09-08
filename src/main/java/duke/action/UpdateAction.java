package duke.action;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Optional;
import java.util.Queue;

import duke.task.*;

public class UpdateAction extends Action {

    private final int taskNumber;
    private final TaskList tasks;
    private Task taskToUpdate;
    private final Ui ui;
    private final Queue<Action> actionQueue;

    public UpdateAction(Ui ui,
                        TaskList tasks,
                        Queue<Action> actionQueue,
                        int taskNumber) {
        this.taskNumber = taskNumber;
        this.tasks = tasks;
        this.ui = ui;
        this.taskToUpdate = tasks.retrieve(taskNumber).duplicate();
        this.actionQueue = actionQueue;
    }


    @Override
    public void prompt(Ui ui) {
        ui.showUpdateActionMessage();
    }

    @Override
    public Optional<Action> receiveInputAndGetNextAction(String input) throws DukeException {
        boolean inputIsEmpty = input.length() == 0;
        if (input.split(" ").length == 0 || input.length() == 0) {
            throw new DukeException("Input cannot be empty.");
        }

        String taskType = taskToUpdate.getSaveSymbol();
        modifyTaskToUpdate(taskType, input);
        return Optional.of(new ConfirmAction(this));
    }

    public void execute() {
        tasks.replace(taskNumber, taskToUpdate);
        ui.showUpdateMessage(taskToUpdate, taskNumber);
    }

    private void modifyTaskToUpdate(String taskType, String input) throws DukeException {
        boolean isEmpty = input.length() == 0;
        boolean isJustWhiteSpace = input.split(" ").length == 0;
        if (isEmpty || isJustWhiteSpace) {
            throw new DukeException("Input cannot be empty.");
        }

        switch (taskType) {
            case Task.TODO_SAVE_SYMBOL:
                taskToUpdate.setDescription(input);
                break;
            case Task.DEADLINE_SAVE_SYMBOL:
                parseForDeadline(input);
                break;
            case Task.EVENT_SAVE_SYMBOL:
                parseForEvent(input);
                break;
            default:
                throw new DukeException("TaskType unknown in UpdateAction.");
        }
    }

    private void parseForDeadline(String input) throws DukeException {
        if (input.contains(Task.DEADLINE_FIELD_IDENTIFIER)) {
            String[] inputArray = input.split(Task.DEADLINE_FIELD_IDENTIFIER, 2);
            String description = inputArray[0].strip();

            if (description.length() == 0) {
                description = taskToUpdate.getDescription();
            }
            taskToUpdate = Deadline.createDeadline(description, inputArray[1].strip());
        } else {
            taskToUpdate.setDescription(input);
        }
    }

    private void parseForEvent(String input) throws DukeException {
        if (input.contains(Task.EVENT_FIELD_IDENTIFIER)) {
            String[] inputArray = input.split(Task.EVENT_FIELD_IDENTIFIER, 2);
            String description = inputArray[0].strip();

            if (description.length() == 0) {
                description = taskToUpdate.getDescription();
            }
            taskToUpdate = Event.createEvent(description, inputArray[1].strip());
        } else {
            taskToUpdate.setDescription(input);
        }
    }
}
