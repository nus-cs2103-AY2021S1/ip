package task;

import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    public String addTask(Task newTask) {
        tasks.add(newTask);
        String output = "Got it. I've added this task:\n " + newTask.toString() + "\n";
        String numberOfTasks = tasks.size() < 2 ? " task in the list." : " tasks in the list.";
        return output + "Now you have a total of " + String.valueOf(tasks.size()) + numberOfTasks;
    }

    public int getCompletedTasks(){
        int completedTasks = 0;
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getIsDone()){
                completedTasks++;
            }
        }
        return completedTasks;
    }

    public int getUncompletedTasks(){
        int uncompletedTasks = 0;
        for(int i = 0; i < tasks.size(); i++){
            if(!tasks.get(i).getIsDone()){
                uncompletedTasks++;
            }
        }
        return uncompletedTasks;
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are all your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(String.valueOf(i + 1) + ") " + tasks.get(i).toString() + "\n");
        }
        stringBuilder.append("You have completed " + getCompletedTasks() + " tasks and have yet to complete "
        + getUncompletedTasks() + " tasks.");
        return stringBuilder.toString();
    }

    public String markTaskAsDone(int taskIndex) {
        Task updatedTask = tasks.get(taskIndex - 1).markTaskAsDone();
        tasks.set(taskIndex - 1, updatedTask);
        String taskDoneMessage = "Good job on completing this task! I have marked this task as done: ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(taskDoneMessage + "\n");
        stringBuilder.append(updatedTask.toString());
        return stringBuilder.toString();
    }
}
