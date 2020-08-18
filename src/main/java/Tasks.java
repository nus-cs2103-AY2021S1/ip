import java.util.ArrayList;
import java.util.Iterator;

public class Tasks {

    public ArrayList<String> taskList;

    public Tasks() {
        taskList = new ArrayList<>();
    }

    public void addTask(String task) {
        System.out.println("added : " + task);
        taskList.add(task);
    }

    public void getList() {
        Iterator<String> iter = taskList.iterator();
        int index = 1;
        while (iter.hasNext()) {
            System.out.println(index + ". " + iter.next());
            index++;
        }
    }

}
