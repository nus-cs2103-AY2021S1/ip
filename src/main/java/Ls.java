public class Ls extends Command{

    //TODO: Add on if you want for optional parameters to show only a certain type of task
    Ls(){
        this.name = "list";
        this.usage = "list";
        this.description = "Lists all the tasks";
    }

    public String response(){
        return DataStorageInterface.listOfTasks();
    }
}
