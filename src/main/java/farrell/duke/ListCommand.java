package main.java.farrell.duke;

public class ListCommand extends Command {

    public ListCommand() {
        type = CommandType.LIST;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.toString();
    }
}
