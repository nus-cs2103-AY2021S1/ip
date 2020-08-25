/**
 * List all tasks that Duke currently has.
 */
public class ListCommand extends Command{
    
    public ListCommand(String input) {
        super(input);
    }
    /**
     * List out all tasks that Duke is currently handling.
     * @param tasks List of all tasks Duke has.
     * @param ui Handles the output that the user sees.
     * @param storage Writing of the save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().size() == 0) {
            ui.printOutput("\tList is empty! Start adding some tasks");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 1; i <= tasks.getTasks().size(); i++) {
                Task current = tasks.getTasks().get(i - 1);
                ui.printOutput("\t" + i + "." + current.toString());
            }
        }
    }

    /**
     * Lets main logic know that it can not stop running.
     * @return False to prevent loop from exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
