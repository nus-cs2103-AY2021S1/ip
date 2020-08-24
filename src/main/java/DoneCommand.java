/**
 * Represents the command from the user to complete an
 * existing task
 */
public class DoneCommand extends Command{

    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Completes the task that the user specified
     * @param tasks List of tasks
     * @param ui Ui object that handles printing
     * @param storage handles the rewriting of save file
     * @throws InvalidInputException if command is poorly written
     * @throws InvalidSaveFileException if there is an issue writing the save file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidSaveFileException {
        if(super.input.length() <= 5) {
            throw new InvalidInputException("\t☹ OOPS!!! Please specify which task you want to complete!");
        }
        int completed = Integer.parseInt(super.input.substring(5));
        try {
            Task current = tasks.getTasks().get(completed - 1);
            current.completeTask();
            ui.printOutput("\tNice! I've marked this task as done:\n"+"\t\t" + current.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("\tIndex out of bounds! Please try again.");
        }
        storage.saveFile(tasks.getTasks());
    }
    public boolean isExit() {
        return false;
    }
}
