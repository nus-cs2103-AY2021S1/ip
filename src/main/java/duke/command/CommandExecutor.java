package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;

public class CommandExecutor {
    private boolean hasExited = false;

    public String execute(String in, TaskList taskList, Storage storage) throws DukeException {
        if (hasExited) {
            throw new InvalidCommandException("Program has already exited!");
        }

        CommandType cmdType = Parser.parseCmdWord(in);
        switch (cmdType) {
            case Exit:
                hasExited = true;
                return ExitCommand.execute();
            case List:
                return ListCommand.execute(taskList);
            case Done:
                return DoneCommand.execute(in, taskList);
            case Delete:
                return DeleteCommand.execute(in, taskList, storage);
            case Due:
                return DueCommand.execute(in, taskList);
            case Task:
                return TaskCommand.execute(in, taskList, storage);
            default: // Invalid
               throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean shouldExit() {
        return hasExited;
    }
}
