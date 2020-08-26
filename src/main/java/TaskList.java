import java.util.List;

public class TaskList {
    protected List<Task> listOfTasks;
    
    protected static String TODO_INDICATOR = "[T]";
    protected static String DEADLINE_INDICATOR = "[D]";
    protected static String EVENT_INDICATOR = "[E]";
    

    public TaskList(List<Task> tasks) {
        this.listOfTasks = tasks;
    }

    public void addNewTask(Task newTask) {
        this.listOfTasks.add(newTask);
    }

    public void updateTaskList(Task newTask, int index) {
        this.listOfTasks.add(index, newTask);
    }


    public int totalNumberOfTasks() {
        return this.listOfTasks.size();
    }

    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }

    public void deleteTask(int index) {
        this.listOfTasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();

        for (Task task: this.listOfTasks) {
            int listIndex = this.listOfTasks.indexOf(task) + 1;
            allTasks.append(listIndex + "." + task + "\n");
        }

        return allTasks.toString();
    }
}
