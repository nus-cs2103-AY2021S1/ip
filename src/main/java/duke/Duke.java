package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

import duke.parser.InputParser;

/**
 * This class handles the logic behind the Duke chatbot.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private InputParser inputParser;
    private TaskList userTasks;
    /**
     * Instantiates a new Duke chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        inputParser = new InputParser();
        userTasks = new TaskList(storage.readFromFile());
    }

    /**
     * Gets user input and returns an appropriate string response.
     *
     * @param input This is the string input from user of Duke chatbot.
     * @return String response to user input.
     * @throws DukeException Any exception caught will throw a DukeException.
     */
    public String getResponse(String input) throws DukeException {

        String response = "";

        CommandType command = inputParser.parseInput(input);

        switch (command) {
        case HELP:
            response += new HelpCommand().getResponse();
            break;
        case TODO:
            String description = inputParser.parseToDoInput(input);
            response += new AddCommand(new ToDo(description))
                    .execute(userTasks, storage);
            break;
        case DEADLINE:
            String[] deadlineParams = inputParser.parseDeadlineInput(input);
            response += new AddCommand(new Deadline(deadlineParams[0], deadlineParams[1]))
                    .execute(userTasks, storage);
            break;
        case EVENT:
            String[] eventParams = inputParser.parseEventInput(input);
            response += new AddCommand(new Event(eventParams[0], eventParams[1], eventParams[2]))
                    .execute(userTasks, storage);
            break;
        case LIST:
            response += new ListCommand().execute(userTasks, input);
            break;
        case DONE:
            int index = Integer.parseInt(input.substring(5)) - 1;
            response += new DoneCommand().execute(index, userTasks, storage);
            break;
        case DELETE:
            index = Integer.parseInt(input.substring(7)) - 1;
            response += new DeleteCommand().execute(index, userTasks, storage);
            break;
        case BYE:
            response += new ByeCommand().getResponse();
            break;
        case INVALID_IS_EMPTY:
            try {
                throw new DukeException("", ExceptionType.EMPTY_INPUT);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_COMMAND:
            try {
                throw new DukeException("", ExceptionType.INVALID_COMMAND);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_EMPTY_DESCRIPTION:
            try {
                throw new DukeException("", ExceptionType.EMPTY_DESCRIPTION);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_DEADLINE_NO_BY:
            try {
                throw new DukeException("", ExceptionType.DEADLINE_NO_BY);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        case INVALID_EVENT_NO_START_END:
            try {
                throw new DukeException("", ExceptionType.EVENT_NO_START_END);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
                break;
            }
        default:
            try {
                throw new DukeException("", ExceptionType.DEFAULT);
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
            }
            break;
        }
        return response;
    }

    /**
     * Returns the Ui object of Duke to caller.
     *
     * @return Ui object.
     */
    public Ui getUi() {
        return ui;
    }
}