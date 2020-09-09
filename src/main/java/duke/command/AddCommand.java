package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.*;

/**
 * Represents command that is specific to the add command.
 */
public class AddCommand extends Command {

    private String input;
    private InputCommand inputCommand;

    /**
     * Creates AddCommand object.
     * @param input input of user.
     * @param inputCommand the InputCommand enum value of object.
     */
    public AddCommand(String input, InputCommand inputCommand) {
        this.isExit = false;
        this.input = input;
        this.inputCommand = inputCommand;
    }

    /**
     * Executes add command based on user input.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     * @throws DukeException If invalid input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        assert taskList != null : "TaskList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";

        switch (inputCommand) {
        case TODO:
            try {
                Todo todo = new Todo(Parser.getTodoDescription(input));
                if (!taskList.getList().contains(todo)) {
                    taskList.addList(todo);
                    ui.setAddedMessage(new Todo(Parser.getTodoDescription(input)), taskList.getListSize());
                    storage.saveListToFile(taskList.getList());
                } else {
                    ui.setDuplicateMessage();
                }
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the todo description...");
            }
            break;
        case DEADLINE:
            try {
                Deadline deadline = new Deadline(Parser.getDeadlineStrings(input)[0],
                        Parser.getDeadlineStrings(input)[1]);
                if (!taskList.getList().contains(deadline)) {
                    taskList.addList(deadline);
                    ui.setAddedMessage(deadline, taskList.getListSize());
                    storage.saveListToFile(taskList.getList());
                } else {
                    ui.setDuplicateMessage();
                }
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the deadline description...");
            }
            break;
        case EVENT:
            try {
                Event event = new Event(Parser.getEventTimeStrings(input)[0],
                        Parser.getEventTimeStrings(input)[1]);
                if (taskList.getList().stream().map(Task::toString).noneMatch(s -> s == event.toString())) {
                    taskList.addList(event);
                    ui.setAddedMessage(event, taskList.getListSize());
                    storage.saveListToFile(taskList.getList());
                } else {
                    ui.setDuplicateMessage();
                }
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the event description...");
            }
            break;
        default:
            throw new DukeException("Give me a valid banana (input)!");
        }
    }

}
