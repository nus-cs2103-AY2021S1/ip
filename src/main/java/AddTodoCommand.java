public class AddTodoCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Creates a AddTodoCommand Object to handle adding Todo to list.
     *
     * @param input The input that user types in.
     */
    public AddTodoCommand(String input) {
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
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(input.substring(5));
        tasks.addTask(todo);
//        total++;
        storage.writeNewDataToFile("T", "0", todo.getDescription(), "");
        System.out.println("    Got it. I've added this task:\n      " + todo + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
