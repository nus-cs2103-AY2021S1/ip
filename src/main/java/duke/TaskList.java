package duke;

import duke.task.Task;

import java.util.List;

public class TaskList {
    private List<Task> taskList;
    
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    public void listAllTasks() {
        int size = taskList.size();
        if (size == 0) {
            Ui.addMessage("There are no tasks in your list.");
        } else {
            Ui.addMessage("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                int taskNumber = i + 1;
                String task = String.format("%d. %s", taskNumber, taskList.get(i));
                Ui.addMessage(task);
            }
        }
        Ui.sendMessages();
    }
    
    public void addTask(Task task) {
        taskList.add(task);
        int size = taskList.size();
        String taskWord = (size > 1 ? "tasks" : "task");
        Ui.addMessage("Got it. I've added this task:");
        Ui.addMessage("  " + task);
        Ui.addMessage("Now you have " + size + " " + taskWord + " in the list.");
        Ui.sendMessages();
    }
    
    public void deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        
        Ui.addMessage("Got it. I've removed this task:");
        Ui.addMessage("  " + task);
        Ui.sendMessages();
    }
    
    public void doneTask(int index) {
        Task task = taskList.get(index);
        task.markAsDone();

        Ui.addMessage("Nice! I've marked this task as done:");
        Ui.addMessage("  " + task);
        Ui.sendMessages();
    }
    
    public List<Task> getTask() {
        return taskList;
    }
}
