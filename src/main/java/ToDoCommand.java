public class ToDoCommand extends Command {
    public ToDoCommand(String input) {
        super(input);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws ToDoException {
        Task task = new ToDo(input);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    boolean isExit() {
        return false;
    }
}
