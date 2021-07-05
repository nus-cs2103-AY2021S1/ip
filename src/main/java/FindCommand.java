import java.util.ArrayList;

public class FindCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Creates a FindCommand Object to handle user asking for a keyword.
     *
     * @param input Keyword to find.
     */
    public FindCommand(String input) {
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
        if (input.length() <= 5) {
            throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
        }

        String[] splitStr = input.split(" ");

        assert splitStr.length == 2 : "Input for Find does not follow the correct format";

        ArrayList<Task> tasksWithWord = tasks.find(splitStr[1]);

        if (tasksWithWord.size() == 0) {
            throw new DukeException("No tasks with the word found.");
        }

        System.out.println("Here are the matching task(s) in your list:");
        for (int i = 0; i < tasksWithWord.size(); i++) {
            Task task = tasksWithWord.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }
}
