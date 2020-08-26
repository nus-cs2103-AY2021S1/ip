import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedData) {
        this.taskList = loadedData;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void setDone(int index) {
        Task newTask = this.taskList.get(index - 1).markAsDone();
        this.taskList.set(index - 1, newTask);
        Ui.printMarkAsDone(newTask);
    }

    public void setDelete(int index) {
        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        Ui.printRemoveTask(removed, this.taskList);
    }

    public void setTodo(String input) {
        String description = input.substring(4);
        Task newTask = new Todo(description.trim());
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, taskList);
    }

    public void setDeadline(String description, LocalDate d1, String timeString) {
        Task newTask = new Deadline(description.trim(), d1, timeString);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    public void setDeadline(String description, LocalDate d1) {
        Task newTask = new Deadline(description.trim(), d1);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    public void setEvent(String description, LocalDate d2, String timeString) {
        Task newTask = new Event(description.trim(), d2, timeString);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    public void setEvent(String description, LocalDate d2) {
        Task newTask = new Event(description.trim(), d2);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    public TaskList find(String input) {
        TaskList result = new TaskList();
        for(int i = 0; i < this.taskList.size(); i++) {
            String taskDescription = this.taskList.get(i).getDescription();
            if (taskDescription.contains(input)) {
                result.add(this.taskList.get(i));
            }
        }
        return result;
    }
}
