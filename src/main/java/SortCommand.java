import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implements methods for SortCommand.
 */
public class SortCommand extends Command {
    protected String typeOfCommand;
    protected boolean isExit;

    /**
     * Instantiates TodoCommand object.
     * @param typeOfCommand Description of sorting command.
     */
    public SortCommand(String typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }

    /**
     * Runs command to handle todo command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     */
    @Override
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        if (typeOfCommand.equals("description")) {
            sortByDescription(arrayOfTasks);
            return ui.listTaskByDesc(ui, arrayOfTasks);
        } else if (typeOfCommand.equals("type")) {
            sortByType(arrayOfTasks);
            return ui.listTaskByType(ui, arrayOfTasks);
        } else {
            return ui.sortError();
        }
    }

    /**
     * Sorts list alphabetically according to task description.
     */
    private void sortByDescription(TaskList arrayOfTasks) {
        ArrayList<Task> copiedArray = arrayOfTasks.getTasks();
        copiedArray.sort(Comparator.comparing(Task::getDescription));
    }

    /**
     * Sorts list alphabetically according to task type.
     */
    private void sortByType(TaskList arrayOfTasks) {
        ArrayList<Task> copiedArray = arrayOfTasks.getTasks();
        copiedArray.sort(Comparator.comparingInt(this::getTaskType)
        );
    }

    /**
     * Gets the type of task required.
     * @return First alphabet of the type of task.
     */
    private char getTaskType(Task task) {
        if ((task.getClass().equals(Todo.class))) {
            return 'T';
        } else if ((task.getClass().equals(Deadline.class))) {
            return 'D';
        } else if ((task.getClass().equals(Event.class))) {
            return 'E';
        }
        return 0;
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return isExit as False
     */
    @Override
    public boolean exitChecker() {
        isExit = false;
        return isExit;
    }
}
