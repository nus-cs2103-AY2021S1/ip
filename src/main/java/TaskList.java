/**
 * Returns list of tasks.
 */
public class TaskList extends Command {

    TaskList() {
        this.name = "list";
        this.usage = "list";
        this.description = "Lists all the tasks";
    }

    public String response() {
        return DataStorageInterface.printListOfTasks();
    }
}
