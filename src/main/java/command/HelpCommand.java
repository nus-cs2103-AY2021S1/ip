package command;

public class HelpCommand implements Command {

    private static final String HELP_MESSAGE =
            "You can ask me to do these:\n"
            + "list: List the current tasks in your list.\n"
            + "bye: Saves any tasks in the list and quits the program.\n"
            + "todo: Add a To-Do task.\n"
            + "event: Add an event task.\n"
            + "deadline: Add a deadline task.\n"
            + "done: Mark task as done based on the task's number.\n"
            + "delete: Deletes task based on the task's number.\n"
            + "find: Find a task which matches your description."
            + "help: Repeat this list of possible commands.";;

    @Override
    public String executeWithResponse() {
        return HELP_MESSAGE;
    }

    @Override
    public boolean continueDuke() {
        return true;
    }
}
