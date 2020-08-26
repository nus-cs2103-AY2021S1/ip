package TaskList;

import Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();

    // add tasks to taskList
    public static void addTask(Task task){
        taskList.add(task);
    }

    // return length of taskList
    public static Integer getSize(){
        return taskList.size();
    }

    //return task from taskList
    public static Task getTask(int i){
        return taskList.get(i);
    }

    //remove task from taskList and return the deleted Task
    public static Task removeTask(int i){
        Task deletedTask = taskList.remove(i);
        return deletedTask;
    }
}
