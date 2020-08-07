import java.util.ArrayList;

public class TaskList {

    //Store the internal list
    private ArrayList<Task> list;

    public TaskList(){

        //Create a new arraylist to store the tasks
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> getList(){
        //Returns the list of tasks
        return this.list;
    }

    public void displayContent(){

        for(int i = 0; i < this.list.size(); ++i){
            System.out.println(String.format("%d. %s", i + 1, this.get(i).getMessage()));
        }

    }

    public Task get(int index){

        //Return the index of the arraylist
        return this.list.get(index);
    }

    public void add(Task task){

        //Add the task to the list
        this.list.add(task);
    }
}
