package duke.task;

import duke.exceptions.InvalidTaskIndexException;
import duke.utils.Colour;
import duke.utils.ResourceHandler;

import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public Task getTask(int taskIndex){
        return this.tasks.get(taskIndex);
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

    public int getTotalNumberOfTasks(){
        return this.tasks.size();
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

    public void markTaskAsDone(int taskIndex){
            Task updatedTask = tasks.get(taskIndex - 1);
            updatedTask.markTaskAsDone();
            tasks.set(taskIndex - 1, updatedTask);
    }
}
