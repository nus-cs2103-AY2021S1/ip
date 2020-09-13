package command;

public class BadCommand extends Command {

    /**
     * Warns user of bad input and provides command list.
     * @return String to show command list.
     */
    @Override
    public String execute() {
        return "I'm sorry, but I don't know what that means :(\n"
            + "Here's the command list:\n"
            + "1. list - show your current list of tasks\n"
            + "2. todo - creates a to-do task\n"
            + "3. event - creates an event task\n"
            + "4. deadline - creates a deadline task\n"
            + "5. done - marks task as done\n"
            + "6. delete - deletes task from the list\n"
            + "7. find - finds tasks containing keyword\n"
            + "8. tag - tags tasks with given tag\n";
    }

}
