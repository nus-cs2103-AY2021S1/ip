public class AddTodoCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public AddTodoCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

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
