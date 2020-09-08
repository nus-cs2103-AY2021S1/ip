package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DoneCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

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
            String description = input.substring(4);
            response += new AddCommand(new ToDo(description))
                    .execute(userTasks, storage);
            break;
        case DEADLINE:
            String[] inputSplit = input.split(" /by ");
            String by = inputSplit[1];
            description = inputSplit[0].substring(8);
            response += new AddCommand(new Deadline(description, by))
                    .execute(userTasks, storage);
            break;
        case EVENT:
            inputSplit = input.split(" /at ");
            String at = inputSplit[1].split(" ")[0];
            String timeRange = inputSplit[1].split(" ")[1];
            description = inputSplit[0].substring(5);
            response += new AddCommand(new Event(description, at, timeRange))
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

            try {
                if (index >= userTasks.getTaskListSize()) {
                    throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
                } else {
                    Task task = userTasks.getTask(index);
                    userTasks.deleteTask(index);
                    response += ui.taskDeletedMessage(task);
                }
                storage.saveToFile(userTasks.getTaskList());
            } catch (DukeException ex) {
                response += ui.errorMessage(ex);
            }

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