package duke.support;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.user.User;


/**
 * Parser deals with making sense of the user command.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD = "add";
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

    public static Command parse(String input) throws DukeException {
        try {
            String[] inputArr = input.split(" ", 2);
            String commandType = inputArr[0];
            String commandContent;
            if ((!Login.isLogined()) && (!commandType.equals(COMMAND_LOGIN))) {
                return new EmptyCommand();
            }
            if (commandType.equals(COMMAND_BYE)) {
                return new ByeCommand();
            } else if (commandType.equals(COMMAND_LIST)) {
                return new ListCommand();
            } else if (commandType.equals(COMMAND_LOVE)) {
                return new LoveCommand();
            }
            //assert inputArr.length == 2 : "No Available Condition for Operation!";

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
            throw new InvalidInputException("Sorry! The command is not valid");
        } catch (InvalidInputException e) {
            throw new InvalidInputException(e.getMessage());
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }
}
