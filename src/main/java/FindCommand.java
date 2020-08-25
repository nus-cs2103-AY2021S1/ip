import java.util.List;

/**
 * Represents the command to find a certain task that
 * the user has input.
 */
public class FindCommand extends Command{

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException("\tDescription of item to find cannot be empty! Please try again!");
        }
        List<Task> foundTasks = tasks.findTasks(super.input.substring(5));
        if(foundTasks.size()==0) {
            ui.printOutput("\tThere is no item that matches your description!");
        } else {
            ui.printOutput("\tHere are the matching tasks in your list:");
            for ( int x = 0; x < foundTasks.size(); x++) {
                ui.printOutput("\t" + (x+1) + "." + foundTasks.get(x).toString());
            }
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
