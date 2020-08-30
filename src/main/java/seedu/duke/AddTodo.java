package seedu.duke;

/**
 * Class that represents adding a todo task.
 */
public class AddTodo extends AddCommand {
    public AddTodo(String[] words) {
        super(words);
    }

    /**
     * Adds the task to the list of current tasks.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     */
    @Override
    public void execute(TaskList ls, Ui ui) {
        ToDo newTD = new ToDo(words[1], false);
        ls.add(newTD);
        String thing = "Alright then, add more things to your ever-growing list of tasks:\n" +
                newTD.getStatus().replaceAll("(?m)^", "\t") +
                "\nNow you have " + ls.size() + " tasks in the list.";
        ui.printResult(thing);
    }
}
