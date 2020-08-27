package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents command that is specific to the add command.
 */
public class AddCommand extends Command {

    private String input;
    private InputCommand inputCommand;

    public AddCommand(String input, InputCommand inputCommand) {
        this.exit = false;
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

        switch (inputCommand) {
        case TODO:
            try {
                taskList.addList(new Todo(Parser.getTodoDescription(input)));
                ui.addedMessage(new Todo(Parser.getTodoDescription(input)), taskList.getListSize());
                storage.saveListToFile(taskList.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the todo description...");
            }
            break;
        case DEADLINE:
            try {
                taskList.addList(new Deadline(Parser.getDeadlineStrings(input)[0],
                        Parser.getDeadlineStrings(input)[1]));
                ui.addedMessage(new Deadline(Parser.getDeadlineStrings(input)[0],
                        Parser.getDeadlineStrings(input)[1]), taskList.getListSize());
                storage.saveListToFile(taskList.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the deadline description...");
            }
            break;
        case EVENT:
            try {
                taskList.addList(new Event(Parser.getEventTimeStrings(input)[0],
                        Parser.getEventTimeStrings(input)[1]));
                ui.addedMessage(new Event(Parser.getEventTimeStrings(input)[0],
                        Parser.getEventTimeStrings(input)[1]), taskList.getListSize());
                storage.saveListToFile(taskList.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the event description...");
            }
            break;
        default:
            throw new DukeException("Give me a valid banana (input)!");
        }
    }

}