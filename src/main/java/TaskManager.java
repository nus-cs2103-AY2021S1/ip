import java.util.ArrayList;
import java.util.List;

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;   
    }

    public void add(Task task) {
        tasks.add(task);
        save();
    }

    private void save() {
        StringBuffer sb = new StringBuffer();
        for (Task task : tasks) {
            sb.append(task.saveText() + "\n");
        }
        Storage.writeTasksFile(sb.toString());
    }

    public String listTasks() {
        int i = 1;
        StringBuffer sb = new StringBuffer("Here are your tasks\n");
        for (Task task: tasks) {
            sb.append("\n" + i + ". " + task);
            i++;
        }
        return sb.toString();
    }

    public Task markDone(int taskNum) throws DukeException {
        try {
            Task task = tasks.get(taskNum - 1);
            task.markDone();
            save();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you gave an invalid task number!");
        }
        
    }

    public void deleteTask(int taskNum) throws DukeException {
        try {
            Task task = tasks.remove(taskNum - 1);
            System.out.println(String.format("Successfully removed the following task:\n %s", task));
            System.out.println(String.format("You have a total of %d tasks left", tasks.size()));
            save();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you gave an invalid task number!");
        }
    }
}