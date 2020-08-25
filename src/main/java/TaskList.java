import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void markAsDone(int value) throws IndexOutOfBoundsException{
        try{
            this.tasks.get(value).markAsDone();
        } catch (IndexOutOfBoundsException e){
            UI.printFormattedMessage("ERROR: Invalid Done Number!");
        }
    }

    public void deleteTask(int value) throws IndexOutOfBoundsException{
        try{
            this.tasks.remove(value);
        } catch (IndexOutOfBoundsException e){
            UI.printFormattedMessage("ERROR: Invalid Done Number!");
        }
    }

    public int numOfTasks(){
        return this.tasks.size();
    }

    public Task getTask(int value){
        return this.tasks.get(value);
    }


}
