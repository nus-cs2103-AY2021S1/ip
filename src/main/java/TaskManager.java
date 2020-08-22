import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks; // a list of tasks

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void markTaskAsDone(int index) {
        tasks.get(index - 1).markAsDone();
        Messenger.markAsDoneMessage(tasks.get(index - 1));
    }

    public void addTask(String content, String status, String time) {
        Task newTask = new Task(content, status, time);
        tasks.add(newTask);
        int size = tasks.size();
        Messenger.addTaskMessage(newTask, size);
    }

    public void addTask(String content, String status) {
        Task newTask = new Task(content, status);
        tasks.add(newTask);
        int size = tasks.size();
        Messenger.addTaskMessage(newTask, size);
    }

    public void deleteTask(int index) throws DukeException {
        int size = tasks.size();
        if (index > size) {
            throw new DukeException(Messenger.INDEX_OUT_OF_BOUND_ERROR);
        }
        Messenger.deleteTaskMessage(tasks.get(index - 1), size - 1);
        tasks.remove(index - 1);
    }
}
