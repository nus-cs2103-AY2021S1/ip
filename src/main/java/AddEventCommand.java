public class AddEventCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Creates a AddEventCommand Object to handle adding Event to list.
     *
     * @param input The input that user types in.
     */
    public AddEventCommand(String input) {
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
        if (input.length() <= 6 || !input.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String[] splitStr = input.split("/at ");
        String description = splitStr[0].substring(6).trim();
        Event event = new Event(description, splitStr[1]);

        tasks.addTask(event);

        storage.writeNewDataToFile("E", "0", event.getDescription(), event.getTime());
        System.out.println("    Got it. I've added this task:\n      " + event + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
