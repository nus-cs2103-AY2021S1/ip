import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<String> allTasks;
    TaskList(List<String> tasks){
        this.allTasks = new ArrayList<>(tasks);
    }
    TaskList(){
        allTasks = new ArrayList<>();
    }
}
