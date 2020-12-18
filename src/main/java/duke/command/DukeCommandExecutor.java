package duke.command;

import duke.exception.DukeException;
import duke.response.ErrorResponse;
import duke.response.Response;
import duke.task.TaskList;

public class DukeCommandExecutor implements CommandExecutor {
    private static final String ERROR_INVALID_COMMAND = "I'm sorry, but I don't know what that means :-(";

    private boolean hasExited = false;

    /**
     * Processes the command issued by user and passes it on to the relevant Command class for its execution.
     *
     *
     * @param in String command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user.
     */
    public Response execute(String in, TaskList taskList) {
        assert !hasExited : "Program has already exited";

        try {
            CommandType cmdType = CommandParser.parseCmdWord(in);
            switch (cmdType) {
            case Delete:
                return DeleteCommand.execute(in, taskList);
            case Done:
                return DoneCommand.execute(in, taskList);
            case Due:
                return DueCommand.execute(in, taskList);
            case Exit:
                hasExited = true;
                return ExitCommand.execute();
            case Find:
                return FindCommand.execute(in, taskList);
            case List:
                return ListCommand.execute(taskList);
            case Remind:
                return RemindCommand.execute(in, taskList);
            case Tag:
                return TagCommand.execute(in, taskList);
            case Task:
                return TaskCommand.execute(in, taskList);
            default:
                assert cmdType.equals(CommandType.Invalid) : "CommandType should be Invalid";
                return new ErrorResponse(ERROR_INVALID_COMMAND);
            }
        } catch (DukeException e) {
            return new ErrorResponse(e.getMessage());
        }
    }

    /**
     * Returns true if the "bye" command has been issued else returns false.
     *
     * @return boolean should the program exit.
     */
    public boolean shouldExit() {
        return hasExited;
    }

    public void loadSaveString(String in, TaskList taskList) throws DukeException {
        TaskCommand.loadSavedTasks(in, taskList);
    }
}
