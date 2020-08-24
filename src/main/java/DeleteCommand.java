public class DeleteCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Creates a DeleteCommand Object to handle deleting a task from the list.
     *
     * @param input The input that user types in.
     */
    public DeleteCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    /**
     * Executes the command.
     *
     * @param tasks The entire list of tasks.
     * @param ui Ui object to deal with user interaction.
     * @param storage Storage object to deal with loading tasks from the file and saving tasks in the file.
     * @throws DukeException when the input is not valid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = input.split(" ");

        if (splitStr.length == 1) {
            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
        }

        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
        Task task = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);

        storage.deleteCurrentDataInFile(taskIndex + 1, tasks.getSize());
        System.out.println("    Noted. I've removed this task:\n      " + task + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
