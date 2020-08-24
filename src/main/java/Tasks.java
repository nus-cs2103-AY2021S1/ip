import java.util.ArrayList;

public class Tasks implements java.io.Serializable {

    private final Storage storage = new Storage();
    private ArrayList<Task> tasks;

    public Tasks() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        UI.print("added: " + task.toString() + numTasks());
        Storage.store(this);
    }

    public String numTasks() {
        int size = tasks.size();
        return "You now have " + size + " task" + (size > 1 ? "s" : "") + " in the list.\n";
    }

    public void print_tasks() {
        System.out.print(UI.LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + "." + tasks.get(i));
        }
        System.out.print(UI.LINE);
    }

    public Task get(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        return tasks.get(i);
    }

    public void remove(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.remove(i);
        Storage.store(this);
    }

    public void setDone(int i, boolean value) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.get(i).done = value;
        Storage.store(this);
    }

    public void store() {
        Storage.store(this);
    }

}