public class ByeCommand extends Command {
    public static final boolean IS_EXIT = true;

    /**
     * Creates a ByeCommand Object to handle the case when user says bye.
     */
    public ByeCommand() {
        super(IS_EXIT);
    }

    /**
     * Executes the command.
     *
     * @param tasks The entire list of tasks.
     * @param ui Ui object to deal with user interaction.
     * @param storage Storage object to deal with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
