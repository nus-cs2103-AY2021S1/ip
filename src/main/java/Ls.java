/**
 * Returns list of tasks.
 */
public class Ls extends Command{

    Ls(){
        this.name = "list";
        this.usage = "list";
        this.description = "Lists all the tasks";
    }

    public String response(){
        return DataStorageInterface.listOfTasks();
    }
}
