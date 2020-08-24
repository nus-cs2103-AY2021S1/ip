import java.util.ArrayList;

public class Tasks {

    private ArrayList<Task> tasks;

    public Tasks(){
        tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
        Duke.print("added: " + task.toString() + numTasks());
    }

    public String numTasks() {
        int size = tasks.size();
        return "You now have " + size + " task" + (size > 1 ? "s" : "") + " in the list.\n";
    }

    public void print_tasks() {
        System.out.print(Duke.LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + "." + tasks.get(i));
        }
        System.out.print(Duke.LINE);
    }

    public Task get(int i) throws DukeException {
        if(i < 0 || i >= tasks.size()){
            throw new DukeException("invalid task number");
        }
        return tasks.get(i);
    }

    public void remove(int i) throws DukeException {
        if(i < 0 || i >= tasks.size()){
            throw new DukeException("invalid task number");
        }
        tasks.remove(i);
    }

    public void setDone(int i, boolean value) throws DukeException {
        if(i < 0 || i >= tasks.size()){
            throw new DukeException("invalid task number");
        }
        tasks.get(i).done = value;
    }

}