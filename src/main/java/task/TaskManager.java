package task;

import utils.Colour;
import utils.ResourceHandler;

import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    public String addTask(Task newTask) {
        tasks.add(newTask);
        String output = "Got it. I've added this task:\n " + Colour.Green(newTask.toString()) + "\n";
        String numberOfTasks = tasks.size() < 2 ? " task in the list." : " tasks in the list.";
        return output + "Now you have a total of " + String.valueOf(tasks.size()) + numberOfTasks;
    }

    public String deleteTask(int taskIndex) {
        Task deletedTask = tasks.remove(taskIndex - 1);
        String output = "Noted. I have removed the task: \n";
        return output + Colour.Red(deletedTask.toString());
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

    public int getCompletedTasks() {
        int completedTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getIsDone()) {
                completedTasks++;
            }
        }
        return completedTasks;
    }

    public int getUncompletedTasks() {
        int uncompletedTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).getIsDone()) {
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
        boolean isPluralCompletedTasks = getCompletedTasks() >= 2;
        boolean isPluralUncompletedTasks = getUncompletedTasks() >= 2;
        String completedTasks = Colour.Green(getCompletedTasks()
                + (isPluralCompletedTasks ? " tasks" : " task"));
        String uncompletedTasks = Colour.Red(getUncompletedTasks()
                + (isPluralUncompletedTasks ? " tasks." : " task"));
        stringBuilder.append("You have completed " + completedTasks + " and have yet to complete "
                + uncompletedTasks);
        return stringBuilder.toString();
    }

    public String markTaskAsDone(int taskIndex) {
        Task updatedTask = tasks.get(taskIndex - 1);
        updatedTask.markTaskAsDone();
        tasks.set(taskIndex - 1, updatedTask);
        String taskDoneMessage = ResourceHandler.getMessage("taskManager.taskDoneMessage");
        StringBuilder stringBuilder = new StringBuilder(taskDoneMessage);
        stringBuilder.append(Colour.Green(updatedTask.toString()));
        return stringBuilder.toString();
    }
}
