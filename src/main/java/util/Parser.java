package util;

import command.*;
import duke.DukeException;

/**
 * The Parser class handles the validation of user input and creation of commands.
 */
public class Parser {
    /**
     * List of tasks
     */
    private final TaskList lst;

    /**
     * Creates a new Parser instance.
     *
     * @param lst List of tasks.
     */
    public Parser(TaskList lst) {
        this.lst = lst;
    }

    /**
     * Validates the user input command description.
     *
     * @throws DukeException If user did not give a command description, or gave it in an invalid format.
     */
    private void validateCommandDesc(String desc, Action type) throws DukeException {
        String result = desc.trim();
        if (result.isEmpty()) {
            throw new DukeException("Command description cannot be empty");
        }
        if (type == Action.DEADLINE) {
            if (!result.contains("/by") || result.split("/by").length <= 1 || result.split("/by")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
        if (type == Action.EVENT) {
            if (!result.contains("/at") || result.split("/at").length <= 1 || result.split("/at")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
    }

    /**
     * Validates the user input task number.
     *
     * @throws DukeException If user gave an invalid task number.
     */
    private void validateTaskNum(int taskNum) throws DukeException {
        if (taskNum > lst.size() || taskNum <= 0) {
            throw new DukeException("You have no such task. Please check your task number.");
        }
    }

    /**
     * Returns the correct command after parsing the user input.
     *
     * @param command String representing the user input command.
     * @throws DukeException If user gave an invalid input command.
     */
    public Command parse(String command) throws DukeException {
        Command resultantCommand = null;
        String[] splitCommand = command.split(" ", 2);
        Action action = Action.valueOf(splitCommand[0].toUpperCase());
        String value = splitCommand.length > 1 ? splitCommand[1] : "";

        switch (action) {
        case LIST:
            resultantCommand = new ListCommand();
            break;
        case TODO:
            this.validateCommandDesc(value, Action.TODO);
            resultantCommand = new AddCommand("TODO", value);
            break;
        case DEADLINE:
        case EVENT:
            String[] splitValue;
            if (action == Action.DEADLINE) {
                this.validateCommandDesc(value, Action.DEADLINE);
                splitValue = value.split("/by ");
                resultantCommand = new AddCommand("DEADLINE", splitValue[0], splitValue[1]);
            } else {
                this.validateCommandDesc(value, Action.EVENT);
                splitValue = value.split("/at ");
                resultantCommand = new AddCommand("EVENT", splitValue[0], splitValue[1]);
            }
            break;
        case DONE:
        case DELETE:
            int taskNum = Integer.parseInt(value);
            this.validateTaskNum(taskNum);
            resultantCommand = action == Action.DONE ? new DoneCommand(taskNum) : new DeleteCommand(taskNum);
            break;
        case BYE:
            resultantCommand = new ExitCommand();
            break;
        case FIND:
            this.validateCommandDesc(value, Action.FIND);
            resultantCommand = new FindCommand(value);
            break;
        case HELP:
            resultantCommand = new HelpCommand();
        }

        return resultantCommand;
    }

    /**
     * Constants representing the different actions.
     */
    private enum Action {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        FIND,
        HELP
    }
}
