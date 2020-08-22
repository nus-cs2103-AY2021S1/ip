import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    protected void markTaskDone(Task completedTask) {
        if (!completedTask.getStatus()) {
            System.out.println("\nNice! I have completed this task!");
            completedTask.markAsDone();
            System.out.println(" " + completedTask + "\n");
            int indexOfTask = this.taskList.indexOf(completedTask);
            this.taskList.get(indexOfTask).markAsDone();
        } else {
            System.out.println("\nThis task has already been completed!\n");
        }
    }

    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    protected void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    protected int checkTasksLeft() {
        int index = 0;
        for (Task task: this.taskList) {
            if (!task.getStatus()) {
                index++;
            }
        }
        return index;
    }


}
