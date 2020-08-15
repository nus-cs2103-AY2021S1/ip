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

        //Iterate through the list of tasks
        for(int i = 0; i < this.list.size(); ++i){

            //Print the index and the message of the task
            System.out.println(String.format("%d.[%s] %s", i + 1, this.get(i).getStatusIcon(), this.get(i).getMessage()));
        }

    }

    public int size(){

        //Get the size of the list
        return this.list.size();
    }

    public boolean markDone(int index){

        //Check if the index is valid
        if (index < this.list.size() && index >= 0){

            //Mark the task at index as done
            this.get(index).markDone();

            //Return true if operation is successful
            return true;

        }else{

            //Otherwise operation will fail
            return false;
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
