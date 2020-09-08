package main.java.farrell.duke;

public class ByeCommand extends Command {
    public ByeCommand() {
        type = CommandType.BYE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
