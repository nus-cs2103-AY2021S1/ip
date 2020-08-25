import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> lst) {
        ArrayList<Task> temp = new ArrayList<>();
        for (String s : lst) {
            String[] arr = s.split(" \\| ");
            if (arr[0].equals("T")) {
                ToDo todo = new ToDo(arr[2]);
                if (arr[1].equals("1")) {
                    todo.markAsDone();
                }
                temp.add(todo);
            } else if (arr[0].equals("D")) {
                Deadline deadline = new Deadline(arr[2], arr[3]);
                if (arr[1].equals("1")) {
                    deadline.markAsDone();
                }
                temp.add(deadline);
            } else {
                Event event = new Event(arr[2], arr[3]);
                if (arr[1].equals("1")) {
                    event.markAsDone();
                }
                temp.add(event);
            }
        }
        this.tasks = temp;
    }

    public int getLength() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void showList(Ui ui) {
        ArrayList<String> lst = new ArrayList<>();
        for (Task task : tasks) {
            lst.add(task.toString());
        }
        ui.showList(lst);
    }

    public void markDone(int pos, Ui ui) {
        tasks.get(pos).markAsDone();
        ui.showDone(tasks.get(pos));
    }

    public void deleteTask(int pos, Ui ui) {
        Task t = tasks.remove(pos);
        ui.showDelete(t, tasks.size());
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
