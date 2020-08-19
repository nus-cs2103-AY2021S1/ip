import java.util.ArrayList;
import java.util.List;

public class TaskList {
    //This class manages the list of Tasks that the user has added
    private List<Task> ls= new ArrayList<>();

    public boolean isEmpty(){
        return ls.isEmpty();
    }

    public int length(){
        return ls.size();
    }

    public void setDone(int task) throws Exception{
        if(task>ls.size()){
            throw new Exception("You dont have that many tasks");
        }else {
            ls.set(task, ls.get(task).done());
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.println("       [" + "\u2713] " + ls.get(task).getTask());
        }
    }

    public void add(String task){
        ls.add(new Task(task, false));
    }

    public void printTask(){
        for(int i = 0; i < ls.size(); i++){
            int j = i+1;
            if(ls.get(i).getIsDone()){
                System.out.println("     " + j + ".[" + "\u2713] " + ls.get(i).getTask());
            }else{
                System.out.println("     " + j + ".[" + "\u2718] " + ls.get(i).getTask());
            }
        }
    }

}
