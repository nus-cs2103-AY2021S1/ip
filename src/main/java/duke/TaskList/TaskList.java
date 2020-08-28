package duke.TaskList;

import duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){

        this.taskList = new ArrayList<>();
    }

    // add tasks to taskList
    public void addTask(Task task){

        taskList.add(task);
    }

    // return length of taskList
    public int getSize(){

        return taskList.size();
    }

    //return task from taskList
    public Task getTask(int i){

        return taskList.get(i);
    }

    //remove task from taskList and return the deleted Task
    public Task removeTask(int i) {
        Task deletedTask = taskList.remove(i);
        return deletedTask;
    }
}
