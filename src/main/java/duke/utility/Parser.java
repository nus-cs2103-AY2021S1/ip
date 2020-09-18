package duke.utility;

import duke.command.*;
import duke.DukeException;
import duke.task.*;

public class Parser {

    /** duke.task.TaskList class that stores and deals with the tasks **/
    private TaskList taskList;

    /**
     *Class constructor
     *
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse the inputs and carry out the respective commands
     *
     * @param inputs the array of duke.command and taskDescription obtain from user
     */
    public String parseCommand(String[] inputs) {

        assert inputs != null : "inputs cannot be empty";

        try {
            Commands command = getCommand(inputs[0]);
            String taskDescription = getTaskDescription(inputs,command);

            switch (command) {
            case HELP: {
                return new Help(taskList,taskDescription).execute();
            }
            case STATISTIC: {
                return new IStatistic(taskList, taskDescription).execute();
            }
            case DONE: {
                return new Done(taskList, taskDescription).execute();
            }
            case LIST: {
                return new List(taskList, taskDescription).execute();
            }
            case BYE: {
                return new Bye(taskList, taskDescription).execute();
            }
            case FIND: {
                return new Find(taskList, taskDescription).execute();
            }
            case TODO: {
                return new ITodo(taskList, taskDescription).execute();
            }
            case DEADLINE: {
                return new IDeadline(taskList, taskDescription).execute();
            }
            case EVENT: {
                return new IEvent(taskList, taskDescription).execute();
            }
            case DELETE: {
                return new Delete(taskList, taskDescription).execute();
            }
            default: {
                throw new DukeException("smlj??????");
            }
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


    /**
     * convert string duke.command into enum Command
     *
     * @param commandInput duke.command from the user
     */
    public Commands getCommand(String commandInput) {

        assert commandInput != null : "commandInput cannot be null";

        Commands command;
        try {
            command = Commands.valueOf(commandInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Commands.UNKNOWN;
        }
        return command;
    }

    public String getTaskDescription(String[] inputs, Commands command) throws DukeException {
        String taskDescription = "";
        if (inputs.length > 1) {
            taskDescription = inputs[1];
        } else if (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event")) {
            throw new DukeException(String.format("The description of %s cannot be empty", command.toString()));
        }
        return taskDescription;
    }
}
