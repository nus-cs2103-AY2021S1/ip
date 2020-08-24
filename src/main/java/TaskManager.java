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

    public void listTasks() {
        int i = 1;
        System.out.println("*Here are all your tasks");
        for (Task task: tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    public void markDone(int taskNum) throws DukeException {
        try {
            tasks.get(taskNum - 1).markDone();
            save();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you gave an invalid task number!");
        }
        
    }

    public void deleteTask(int taskNum) {
        Task task = tasks.remove(taskNum - 1);
        System.out.println(String.format("Successfully removed the following task:\n %s", task));
        System.out.println(String.format("You have a total of %d tasks left", tasks.size()));
        save();
    }
}