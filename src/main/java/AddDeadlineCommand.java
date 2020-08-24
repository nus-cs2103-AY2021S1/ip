public class AddDeadlineCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public AddDeadlineCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

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
