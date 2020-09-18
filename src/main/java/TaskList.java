import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.function.Consumer;

public class TaskList {
    public ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        taskList = new ArrayList<>();
    }
    
    public int taskListLength() {
        return taskList.size();
    }
    
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }
    
    public Task updateTask(int index, Consumer<Task> update) throws DukeException {
        assert index > 0 : "index cannot be zero or negative";
        try {
            Task task = taskList.get(index);
            update.accept(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            final String ERROR_MESSAGE = "ERROR: task index out of range";
            throw new DukeException(ERROR_MESSAGE);
        }
    }
    
    public Task deleteTask(int index) throws DukeException {
        return updateTask(index, task -> taskList.remove(task));
    }
    
    public Task doneTask(int index) throws DukeException {
       return updateTask(index, Task::markAsDone); 
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String findTask(String display) {
        assert display.length() > 0 : "input cannot be empty";
        StringBuilder matches = new StringBuilder();
        for (Task value : taskList) {
            if (value.description.contains(display)) {
                matches.append(value.toString()).append("\n");
            }
        }
        return matches.toString();
    }
    
    public void sortTasksByDate() {
        TaskComparator taskComparator = new TaskComparator();
        PriorityQueue<Task> priorityQueue = new PriorityQueue<>(taskComparator);
        priorityQueue.addAll(taskList);
        ArrayList<Task> sortedTaskList = new ArrayList<>();
        while (! priorityQueue.isEmpty()) {
            sortedTaskList.add(priorityQueue.poll());
        }
        taskList = sortedTaskList;
    }
}
