import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Main class where Duke program is run.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructs the duke object.
     */
    public Duke() {
        try {
            this.storage = new Storage();
            this.tasks = storage.load();
            this.ui = new UI();

        } catch (IOException e) {
            ui.printFormattedMessage("OOPS!!! File Loading error!");
        }
    }

    /**
     * Runs the duke program and will terminate upon input "bye"
     *
     * @throws DukeUnknownInputException      If users inputs invalid input.
     * @throws DukeEmptyToDoException         If todo is empty.
     * @throws DukeEmptyEventException        If event is empty.
     * @throws DukeEmptyDeadlineException     If deadline is empty.
     * @throws DukeInvalidDoneNumException    If done number entered is invalid.
     * @throws DukeEmptyDeadlineTimeException If the deadline time is empty.
     * @throws DukeEmptyEventTimeException    If the event time is empty.
     * @throws DukeDeleteException            If there are any other exceptions.
     * @throws DateTimeParseException         If there is time passed in the wrong format.
     */
    public String run(String input) {
        String message = "";
        Command currentCommand = null;
        try {
            if (Parser.isList(input)) {
                currentCommand = new ListCommand(input);
            } else if (Parser.isDone(input)) {
                currentCommand = new DoneCommand(input);
            } else if (Parser.isToDo(input)) {
                currentCommand = new ToDoCommand(input);
            } else if (Parser.isDeadline(input)) {
                currentCommand = new DeadlineCommand(input);
            } else if (Parser.isEvent(input)) {
                currentCommand = new EventCommand(input);
            } else if (Parser.isDelete(input)) {
                currentCommand = new DeleteCommand(input);
            } else if (Parser.isFind(input)) {
                currentCommand = new FindCommand(input);
            } else if (Parser.isTag(input)) {
                currentCommand = new TagCommand(input);
            } else if (Parser.isFindTag(input)) {
                currentCommand = new FindTagCommand(input);
            } else if (Parser.isHelp(input)) {
                currentCommand = new HelpCommand(input);
            } else {
                throw new DukeUnknownInputException(input);
            }
            message = currentCommand.execute(this.tasks, this.ui);
        } catch (DukeException e) {
            message = e.getMessage();
        }

        if (Parser.isBye(input)) {
            message = ui.printByeMessage();
        }
        storage.save(storage.convertArrayToSaveFormat(this.tasks));
        return message;
    }
}
