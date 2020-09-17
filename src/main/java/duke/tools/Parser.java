package duke.tools;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.CommandString;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.Exceptions;
import duke.main.Directory;
import duke.storage.DukeFileReader;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with the strings from the client
 * and enable the string to make sense to Duke.
 */
public class Parser {
    private static TaskList<Task> taskList;
    private static String[] extract = new String[3];
    private static final int command = 0;
    private static final int taskDetail = 1;
    private static final int taskTime = 2;

    /**
     * Constructs the Parser.
     */
    public Parser() {
        taskList = new TaskList<>();
    }

    /**
     * Returns the static attribute of taskList.
     *
     * @return the static attribute of taskList.
     */
    public static TaskList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Refreshes the taskList.
     */
    public static void reloadTaskList() throws DukeException {
        taskList = new TaskList<>();
        taskList = new DukeFileReader(Directory.FILEDIRECTORY).loadFile();
    }

    /**
     * Returns true if the input from the
     * user is "bye".
     *
     * @param inputFromClient input from the
     *                        user.
     * @return true if input is "bye".
     */
    public boolean isEnd(String inputFromClient) {
        return inputFromClient.equals(CommandString.BYE);
    }

    /**
     * Runs the Parser.
     *
     * @param order the order is the string after
     *              being shortened by Format.shorten().
     */
    public static Command run(String order) throws DukeException {

        extract = new String[3];
        extract = extract(order);

        assert extract[command] != null : "Shorten() not working";

        reloadTaskList();

        if (order.equals(CommandString.BYE)) {
            return new ByeCommand();
        } else if (order.equals(CommandString.LIST)) {
            return new ListCommand();
        } else {

            if (order.length() > 0) {
                switch (extract[command]) {
                case CommandString.BYE:
                    return new ByeCommand();
                case CommandString.LIST:
                    return new ListCommand();
                case CommandString.HELP:
                    return new HelpCommand();
                case CommandString.DONE:
                    return done();
                case CommandString.DELETE:
                    return delete();
                case CommandString.FIND:
                    return find(extract[taskDetail]);
                case CommandString.CLEAR:
                    return clear();
                case CommandString.UPDATE:
                    return update();
                default:
                    return identifier();
                }
            } else {
                //May cause error.
                return null;
            }
        }
    }

    /**
     * Sets done the corresponding
     * task on both the taskList and Duke.txt.
     */
    public static Command done() {
        try {
            int num = Integer.parseInt(extract[taskDetail]);
            if (num > taskList.getTaskList().size()) {
                return new DukeException(Exceptions.NUMBEREXCESSEXCEPTION);
            } else {
                return new DoneCommand(num);
            }
        } catch (NumberFormatException e) {
            return new DukeException(Exceptions.NUMBERFORMATEXCEPTION);
        }
    }

    /**
     * Deletes the corresponding
     * task on both the taskList and Duke.txt.
     */
    public static Command delete() {
        try {
            int num = Integer.parseInt(extract[taskDetail]);

            if (num > taskList.getTaskList().size()) {
                return new DukeException(Exceptions.NUMBEREXCESSEXCEPTION);
            } else {
                return new DeleteCommand(num);
            }
        } catch (NumberFormatException e) {
            return new DukeException(Exceptions.NUMBERFORMATEXCEPTION);
        }
    }

    /**
     * Finds the related content from the task details
     * of the tasks in the taskList.
     *
     * @param content the user input content.
     */
    public static Command find(String content) {
        try {
            String[] contentArray = content.split(" ");
            return new FindCommand(contentArray);
        } catch (Exception e) {
            return new DukeException(Exceptions.FINDDETAILMISSINGEXCEPTION);
        }

    }

    /**
     * Clears all records in the file from the
     * directory in Directory class.
     */
    public static Command clear() {
        if (extract[taskDetail] == null || extract[taskDetail].equals(CommandString.CLEAR_ALL)) {
            return new ClearCommand();
        } else if (extract[taskDetail].equals(CommandString.CLEAR_DONE)) {
            return new ClearCommand(extract[taskDetail]);
        }
        return new DukeException(Exceptions.CLEARFORMATIONEXCEPTION);
    }

    /**
     * Updates the selected number of task
     * with the new input.
     * This is done by first deleting the required task
     * and then add in a new task.
     *
     * @return string that contains all tasks after update.
     */
    public static Command update() {
        //The number of the task to update is in taskDetail.
        String[] indexAndTypeOfUpdate = extract[taskDetail].split(" ");

        //The detail of the task to update is in taskTime.
        String taskToUpdate = extract[taskTime];

        //Checks if there are more things other than
        //one index and one partToUpdate.
        if (indexAndTypeOfUpdate.length > 2) {
            return new DukeException(Exceptions.UPDATEFORMATEXCEPTION);
        }

        //Extract the index.
        String indexInString = Format.shorten(indexAndTypeOfUpdate[0]);

        //Extract the command.
        String command = Format.shorten(indexAndTypeOfUpdate[1]);

        try {
            int index = Integer.parseInt(indexInString);

            if (index > taskList.getTaskList().size()) {
                return new DukeException(Exceptions.NUMBEREXCESSEXCEPTION);
            }

            if (!command.equals(CommandString.UPDATE_DETAIL) && !command.equals(CommandString.UPDATE_TIME)) {
                return new DukeException(Exceptions.UPDATEFORMATEXCEPTION);
            }

            return new UpdateCommand(index, command, taskToUpdate);
        } catch (NumberFormatException e) {
            return new DukeException(Exceptions.NUMBERFORMATEXCEPTION);
        }
    }

    /**
     * Extracts out the type of command,
     * and possibly the task detail and
     * task time if the command is to
     * add a new task.
     *
     * @param description the user input.
     * @return a string array whose
     *         length is 3 and first element
     *         is the command string, second
     *         element is the task detail,
     *         and the third element is the
     *         task time.
     */
    private static String[] extract(String description) {
        int len = description.length();
        int pointer = 0;

        while (pointer < len && description.charAt(pointer) != ' ') {
            pointer++;
        }

        //the command of the description has been found.
        extract[command] = description.substring(0, pointer);

        int separator = pointer;
        while (separator < len && description.charAt(separator) != '/') {
            separator++;
        }

        //situation that there is no detail of the task, return
        if (pointer == separator) {
            return extract;
        }

        //details of the description is found
        extract[taskDetail] = Format.shorten(description.substring(pointer + 1, separator));

        while (separator < len && description.charAt(separator) != ' ') {
            separator++;
        }

        //time of the description is found
        if (separator < len - 1) {
            extract[taskTime] = Format.shorten(description.substring(separator));
        }

        assert (separator < len - 1 && extract[taskTime] != null)
                || (separator >= len - 1 && extract[taskTime] == null)
                : "correspondence of separator and time allocation mismatches";

        return extract;
    }

    /**
     * Identifies the type of Tasks
     * from the description passed down from the upper
     * level, which is essentially the input from the
     * user.
     */
    public static Command identifier() throws DukeException {
        String identity = extract[command];
        String detail = extract[taskDetail];
        String time = extract[taskTime];

        //to check if the input is not a todo or event or deadline
        if (!identity.equals(CommandString.TODO)
                & !identity.equals(CommandString.EVENT)
                & !identity.equals(CommandString.DEADLINE)) {
            return new DukeException(Exceptions.INPUTFORMATEXCEPTION);
        }

        //situation that there is no detail of the task, throw error
        if (detail == null) {
            return new DukeException(Exceptions.EMPTYTASKEXCEPTION);
        }

        if (identity.equals(CommandString.TODO)) {
            return new TodoCommand(detail);
        } else {
            try {
                Time date = new Time(time);

                if (identity.equals(CommandString.DEADLINE)) {
                    return new DeadlineCommand(detail, date);
                } else {
                    return new EventCommand(detail, date);
                }
            } catch (Exception e) {
                return new DukeException(Exceptions.TIMEFORMATEXCEPTION);
            }
        }
    }



}
