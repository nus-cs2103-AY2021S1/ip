package duke.command;

import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class AddCommand extends Command {

    private String input;
    private InputCommand inputCommand;

    public AddCommand(String input, InputCommand inputCommand) {
        this.exit = false;
        this.input = input;
        this.inputCommand = inputCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        switch (inputCommand) {
        case TODO:
            try {
                taskList.addList(new Todo(Parser.getTodoDescription(input)));
                ui.printAddedMessage(new Todo(Parser.getTodoDescription(input)), taskList.getListSize());
                storage.saveListToFile(taskList.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the todo description...");
            }
            break;
        case DEADLINE:
            try {
                taskList.addList(new Deadline(Parser.getDeadlineStrings(input)[0],
                        Parser.getDeadlineStrings(input)[1]));
                ui.printAddedMessage(new Deadline(Parser.getDeadlineStrings(input)[0],
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
                ui.printAddedMessage(new Event(Parser.getEventTimeStrings(input)[0],
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