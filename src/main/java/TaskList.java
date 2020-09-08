import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(tasks){
        this.tasks = tasks;
    }

    public void showTaskList(){
        System.out.println("Here are the tasks n your list:" + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            Integer listNum = i + 1;
            System.out.println(listNum.toString() + "." + tasks.get(i).toString());
        }
    }
}
