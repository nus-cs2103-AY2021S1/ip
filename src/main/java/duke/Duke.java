package duke;

import duke.exception.DukeException;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialises Duke with a given file save path.
     * Runs Duke with empty TaskList if save file is not found.
     *
     * @param filePath String representation of the file path.
     */
    public Duke(String filePath) {
        new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            e.printStackTrace();
            Ui.displayLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the initialised Duke.
     */
    public String run(String input) {
        Parser parser = new Parser();

        Ui.displayWelcome();

        String toReturn = "";

        try {
            parser.setCommandLine(input);
            String command = parser.getCommandWord();

            switch (command) {
            case "bye":
                storage.save(taskList);
                toReturn += Ui.displayGoodbye();
                break;
            case "list":
                toReturn += Ui.displayList(taskList);
                break;
            case "done":
                toReturn += taskList.done(parser);
                break;
            case "delete":
                toReturn += taskList.delete(parser);
                break;
            case "find":
                toReturn += taskList.find(parser);
                break;
            case "update":
                toReturn += taskList.update(parser);
                break;
            case "deadline":
            case "event":
            case "todo":
                toReturn += taskList.add(parser);
                break;
            default:
                assert false : command;
            }
        } catch (DukeException e) {
            toReturn += Ui.displayMessage(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            toReturn += Ui.displayMessage(e.getMessage());
        }
        return toReturn;
    }

    public String getResponse(String input) {
        return run(input);
    }
}

