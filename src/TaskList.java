import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> arr;

    TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    TaskList() {
        this.arr = new ArrayList<Task>();
    }

    public ArrayList<Task> getArr() {
        return arr;
    }


}
