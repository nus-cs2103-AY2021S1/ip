package duke.support;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.AddUserCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EmptyCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.LoginCommand;
import duke.commands.LoveCommand;
import duke.commands.TagCommand;
import duke.exceptions.*;
import duke.user.User;


/**
 * Parser deals with making sense of the user command.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_TAG = "tag";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_ADDUSER = "adduser";
    private static final String COMMAND_LOVE = "love";

    /**
     * Parses user input into certain commands.
     *
     * @param input A String of user's input.
     * @return {@code Command} parsed.
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        try {
            String[] inputArr = input.split(" ", 2);
            String commandType = inputArr[0];
            String commandContent;
            if ((!Login.isLogined()) && (!commandType.equals(COMMAND_LOGIN))) {
                throw new NoAccessException();
            }

            if (isInvalidCommand(commandType)) {
                throw new InvalidCommandException();
            }

            if (commandType.equals(COMMAND_BYE)) {
                return new ByeCommand();
            } else if (commandType.equals(COMMAND_LIST)) {
                return new ListCommand();
            } else if (commandType.equals(COMMAND_LOVE)) {
                return new LoveCommand();
            }

            if (inputArr.length < 2) {
                throw new EmptyConditionException();
            }

            commandContent = inputArr[1];
            if (commandType.equals(COMMAND_DONE)) {
                return new DoneCommand(commandContent);
            } else if (commandType.equals(COMMAND_FIND)) {
                return new FindCommand(commandContent);
            } else if (commandType.equals(COMMAND_DELETE)) {
                return new DeleteCommand(commandContent);
            } else if (commandType.equals(COMMAND_TODO)) {
                return new AddToDoCommand(commandContent);
            } else if (commandType.equals(COMMAND_EVENT)) {
                return new AddEventCommand(commandContent);
            } else if (commandType.equals(COMMAND_DEADLINE)) {
                return new AddDeadlineCommand(commandContent);
            } else if (commandType.equals(COMMAND_TAG)) {
                String[] tagInfo = commandContent.split(" ", 2);
                int taskIndex = Integer.parseInt(tagInfo[0]);
                String taskTag = tagInfo[1];
                return new TagCommand(taskIndex, taskTag);
            } else if (commandType.equals(COMMAND_LOGIN)) {
                String[] userInfo = commandContent.split(" ", 2);
                String username = userInfo[0];
                String userPassword = userInfo[1];
                Login.login(username, userPassword);
                return new LoginCommand();
            } else if (commandType.equals(COMMAND_ADDUSER)) {
                String[] newUserInfo = commandContent.split(" ", 3);
                String username = newUserInfo[0];
                String userPassword = newUserInfo[1];
                String userNickname = newUserInfo[2];
                return new AddUserCommand(new User(username, userPassword, userNickname));
            }
            throw new InvalidInputException();
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        } catch (EmptyConditionException e) {
            throw new EmptyConditionException();
        } catch (NoAccessException e) {
            throw new NoAccessException();
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Checks whether input value is a valid command.
     */
    public static boolean isInvalidCommand(String commandType) {
        if (commandType.equals(COMMAND_ADDUSER) ||
            commandType.equals(COMMAND_BYE) ||
            commandType.equals(COMMAND_DEADLINE) ||
            commandType.equals(COMMAND_DELETE) ||
            commandType.equals(COMMAND_DONE) ||
            commandType.equals(COMMAND_EVENT) ||
            commandType.equals(COMMAND_FIND) ||
            commandType.equals(COMMAND_LIST) ||
            commandType.equals(COMMAND_LOGIN) ||
            commandType.equals(COMMAND_LOVE) ||
            commandType.equals(COMMAND_TAG) ||
            commandType.equals(COMMAND_TODO)) {
            return false;
        }
        return true;
    }
}
