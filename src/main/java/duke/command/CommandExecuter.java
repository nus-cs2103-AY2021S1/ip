package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskManager;
import duke.ui.UserInterface;

@FunctionalInterface
interface CommandExecuter {
    void run(TaskManager taskManager, UserInterface ui, String[] arguments) throws DukeException;
}
