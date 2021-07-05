package util;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.TagCommand;
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
            if (isInvalidDeadlineTaskFormat(result)) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
        if (type == Action.EVENT) {
            if (isInvalidEventTaskFormat(result)) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
        if (type == Action.TAG) {
            if (isInvalidTagFormat(result)) {
                throw new DukeException("Be sure to add a tag in the correct format.");
            }
        }
    }

    /**
     * Returns true if result is in an invalid format to create an event task.
     *
     * @param result String to test validity.
     * @return true if result is in an invalid format to create an event task.
     */
    private boolean isInvalidEventTaskFormat(String result) {
        return (!result.contains("/at")
                || result.split("/at").length <= 1
                || result.split("/at")[0].isEmpty());
    }

    /**
     * Returns true if result is in an invalid format to create a deadline task.
     *
     * @param result String to test validity.
     * @return true if result is in an invalid format to create a deadline task.
     */
    private boolean isInvalidDeadlineTaskFormat(String result) {
        return (!result.contains("/by")
                || result.split("/by").length <= 1
                || result.split("/by")[0].isEmpty());
    }

    /**
     * Returns true if result is in an invalid format to create a deadline task.
     *
     * @param result String to test validity.
     * @return true if result is in an invalid format to create a deadline task.
     */
    private boolean isInvalidTagFormat(String result) {
        String[] splitResult = result.split(" ");
        return splitResult.length != 2;
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
     * @return Correct command after parsing user input.
     * @throws DukeException If user gave an invalid input command.
     */
    public Command parse(String command) throws DukeException {
        Command resultantCommand = null;
        // splits command string into action, command description (if applicable)
        String[] splitCommand = command.split(" ", 2);
        Action action = Action.valueOf(splitCommand[0].toUpperCase());
        String commandDesc = splitCommand.length > 1 ? splitCommand[1] : "";

        switch (action) {
        case LIST:
            resultantCommand = new ListCommand();
            break;
        case TODO:
            this.validateCommandDesc(commandDesc, Action.TODO);
            resultantCommand = new AddCommand("TODO", commandDesc);
            break;
        case DEADLINE:
        case EVENT:
            String[] splitValue;
            String commandDate;
            if (action == Action.DEADLINE) {
                this.validateCommandDesc(commandDesc, Action.DEADLINE);
                // splits command desc to get command date
                splitValue = commandDesc.split("/by ");
                commandDate = splitValue[1];
                commandDesc = splitValue[0];
                resultantCommand = new AddCommand("DEADLINE", commandDesc, commandDate);
            } else {
                this.validateCommandDesc(commandDesc, Action.EVENT);
                // splits command desc to get command date
                splitValue = commandDesc.split("/at ");
                commandDate = splitValue[1];
                commandDesc = splitValue[0];
                resultantCommand = new AddCommand("EVENT", commandDesc, commandDate);
            }
            break;
        case DONE:
        case DELETE:
            int taskNum = Integer.parseInt(commandDesc);
            this.validateTaskNum(taskNum);
            resultantCommand = action == Action.DONE ? new DoneCommand(taskNum) : new DeleteCommand(taskNum);
            break;
        case BYE:
            resultantCommand = new ExitCommand();
            break;
        case FIND:
            this.validateCommandDesc(commandDesc, Action.FIND);
            resultantCommand = new FindCommand(commandDesc);
            break;
        case HELP:
            resultantCommand = new HelpCommand();
            break;
        case TAG:
            validateCommandDesc(commandDesc, Action.TAG);
            String[] splitCommandDesc = commandDesc.split(" ", 2);
            String tagName = splitCommandDesc[1];
            int taskNumber = Integer.parseInt(splitCommandDesc[0]);
            this.validateTaskNum(taskNumber);
            boolean isDelete = tagName.equals("delete");
            resultantCommand = new TagCommand(taskNumber, tagName, isDelete);
            break;
        default:
            assert false : action;
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
        HELP,
        TAG
    }
}
