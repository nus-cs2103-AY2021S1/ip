import java.util.ArrayList;

public class FindCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public FindCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
        }

        String[] splitStr = input.split(" ");

        ArrayList<Task> tasksWithWord = tasks.find(splitStr[1]);

        System.out.println("    Here are the matching tasks in your list:" );
        for (int i = 0; i < tasksWithWord.size(); i++) {
            Task task = tasksWithWord.get(i);
            System.out.println("    " + (i + 1) + "." + task);
        }
    }
}
