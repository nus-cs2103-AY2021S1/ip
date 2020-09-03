package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public interface Command {
    String parseInput(TaskList tasks, String text) throws DobbyException;
}
