public class AddDeadlineCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Creates a AddDeadlineCommmand Object to handle adding Deadline to list.
     *
     * @param input The input that user types in.
     */
    public AddDeadlineCommand(String input) {
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
        if (input.length() <= 9 || !input.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] splitStr = input.split("/by ");
        String description = splitStr[0].substring(9).trim();
        Deadline deadline = new Deadline(description, splitStr[1]);

        tasks.addTask(deadline);

        storage.writeNewDataToFile("D", "0", deadline.getDescription(), deadline.getBy());
        System.out.println("    Got it. I've added this task:\n      " + deadline + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
