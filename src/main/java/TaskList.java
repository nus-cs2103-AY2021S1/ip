import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> list;
    private final Ui ui;


    public TaskList(List<Task> list) {
        this.list = list;
        ui = new Ui();
    }


    public void addTask(Task task) {
        list.add(task);
        ui.displayThis("Got it. I've added this task: \n         " + task +
                "\n    Now you have " + list.size() + " tasks in the list");
    }


    public Task done(int entryDone) {
        return list.get(entryDone).markAsDone();
    }


    public void clear(){
        list.clear();
    }

    public Task delete(int entryDelete){
        return list.remove(entryDelete);
    }


    public int size(){
        return list.size();
    }


    public boolean isNull() {
        return list.size() <= 0;
    }


    public List<Task> getList() {
        return list;
    }


}