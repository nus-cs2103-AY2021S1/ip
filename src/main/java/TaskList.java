import java.util.List;
import java.util.ArrayList;
public class TaskList {
    private final List<Task> items;
    public TaskList() {
        this.items = new ArrayList<>();
    }
    public List<Task> getItemsList() {
        return this.items;
    }
    public int size(){
        return this.items.size();
    }
    public void add(Task task) {
        this.items.add(task);
        Util.systemMessage("morning sir i have added this to the list sir:\n"
                + task
                + "\ni counted all your number of tasks sir it is "
                + this.items.size()
                + " sir");
    }
    public String toString() {
        String numberedItems = "";
        for (int i=0;i<this.items.size();i++) {
            numberedItems += (i+1) + ". " + this.items.get(i) + "\n";
        }
        return numberedItems;
    }
    public Task markItem(int idx) {
        Task selected = this.items.get(idx);
        selected.markAsDone();
        return  selected;
    }
    public Task deleteItem(int idx) {
        return this.items.remove(idx);
    }
    public static void deleteCommand(TaskList taskList, String[] args) throws  DukeException {
        int idx = Integer.parseInt(args[0]);
        if(idx > taskList.size())
            throw new DukeException(DukeException.Errors.DELETE_OUT_OF_RANGE);
        Task selected = taskList.deleteItem(idx-1);
        Util.systemMessage("sir this task has been remove sir:\n  " + selected);
    }
    public static void doneCommand(TaskList taskList, String[] args) throws  DukeException {
        int idx = Integer.parseInt(args[0]);
        if(idx > taskList.size())
            throw new DukeException(DukeException.Errors.DONE_OUT_OF_RANGE);
        Task selected = taskList.markItem(idx-1);
        Util.systemMessage("afternoon sir i have mark this task done sir:\n  " + selected);
    }
    public static void listCommand(TaskList taskList, String[] args) throws  DukeException {
        Util.systemMessage(taskList.toString());
    }

}
