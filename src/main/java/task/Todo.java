package task;

/**
 * Todo is a task that consists of the details of the item the user
 * wants to complete and a boolean which indicates whether the task
 * is completed or not
 *
 * @author (Sruthi)
 */
public class Todo extends Task {
    /**
     * Todo takes in a String which contains the details of the task
     * and a boolean which indicates whether the todo is completed.
     * @param item description of the todo
     * @param completed whether the todo is completed
     */
    public Todo(String item, boolean completed) {
        super(item, completed);
    }

    /**
     * It returns the String to be printed to the user containing
     * the details of the todo.
     * @return String to be printed to the user
     */
    @Override
    public String getItem() {
        return "[T]" + super.getItem();
    }

    /**
     * It returns the String inputted by the user for this todo.
     * @return String to be printed to the user
     */
    @Override
    public String getInput() {
        return "[T]" + super.getItem();
    }
}
