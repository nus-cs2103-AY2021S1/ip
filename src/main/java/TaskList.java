import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> ls= new ArrayList<>();

    public boolean isEmpty(){
        return ls.isEmpty();
    }

    public void setDone(int task){
        ls.set(task, ls.get(task).done());
    }

    public void add(String task){
        ls.add(new Task(task, false));
    }

    public void printTask(){
        for(int i = 0; i < ls.size(); i++){
            int j = i+1;
            if(ls.get(i).getIsDone()){
                System.out.println("     " + j + ".[" + "\u2714] " + ls.get(i).getTask());
            }else{
                System.out.println("     " + j + ".[" + "\u2713] " + ls.get(i).getTask());
            }
        }
    }

}
