import java.io.IOException;

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
     * Runs the duke program and will terminate upon input "bye".
     *
     * @param input input of the user.
     * @throws DukeException If the user keys in wromg inputs and returns various messages.
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
