package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;

public class DukeCommandExecutor implements CommandExecutor {
    private boolean hasExited = false;

    public String execute(String in, TaskList taskList) throws DukeException {
        if (hasExited) {
            throw new InvalidCommandException("Program has already exited!");
        }

        CommandType cmdType = CommandParser.parseCmdWord(in);
        switch (cmdType) {
        case Exit:
            hasExited = true;
            return ExitCommand.execute();
        case List:
            return ListCommand.execute(taskList);
        case Done:
            return DoneCommand.execute(in, taskList);
        case Delete:
            return DeleteCommand.execute(in, taskList);
        case Due:
            return DueCommand.execute(in, taskList);
        case Task:
            return TaskCommand.execute(in, taskList);
        default: // Invalid
           throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean shouldExit() {
        return hasExited;
    }
}
