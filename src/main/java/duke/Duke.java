package duke;

import duke.exception.DukeException;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private boolean willReturnAsValid;

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

    private String checkMessage(String message, boolean isValid) {
        willReturnAsValid = isValid;
        return message;
    }

    /**
     * Runs the initialised Duke.
     */
    public String run(String input) {
        try {
            Parser parser = new Parser();
            parser.setCommandLine(input);
            String command = parser.getCommandWord();

            switch (command) {
            case "bye":
                storage.save(taskList);
                return checkMessage(Ui.displayGoodbye(), true);
            case "list":
                return checkMessage(Ui.displayList(taskList), true);
            case "help":
                return checkMessage(Ui.displayHelp(), true);
            case "done":
                return checkMessage(taskList.done(parser), taskList.isToReturnAsValid());
            case "delete":
                return checkMessage(taskList.delete(parser), taskList.isToReturnAsValid());
            case "find":
                return checkMessage(taskList.find(parser), taskList.isToReturnAsValid());
            case "update":
                return checkMessage(taskList.update(parser), taskList.isToReturnAsValid());
            case "deadline":
            case "event":
            case "todo":
                return checkMessage(taskList.add(parser), taskList.isToReturnAsValid());
            default:
                assert false : command;
            }
        } catch (DukeException e) {
            return checkMessage(Ui.displayMessage(e.toString()), false);
        } catch (Exception e) {
            e.printStackTrace();
            return checkMessage(Ui.displayMessage(e.getMessage()), false);
        }
        return input;
    }

    public String getResponse(String input) {
        return run(input);
    }

    public boolean getWillReturnAsValid() {
        return willReturnAsValid;
    }
}

