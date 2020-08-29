package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskListManager {

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    TaskListManager(){
        listOfTasks = new ArrayList<>();
    }

    public int findListSize(){
     return listOfTasks.size();
    }

    public void printTaskList(){
        System.out.println("Here are the tasks in your list:");

        int number = listOfTasks.size();

        for(int i = 0; i < number; i++){

            listOfTasks.get(i).showTask(i+1);

        }
    }

    public void deleteTask(int index){

        Task toBeDeletedTask = listOfTasks.get(index-1);

        listOfTasks.remove(index-1);

        int size = listOfTasks.size();

        System.out.println("Noted. I've removed this task:");

        System.out.println(toBeDeletedTask.toString());

    }

    public void addTask(Task task){

        listOfTasks.add(task);

        int taskSize = listOfTasks.size();

        System.out.println("Got it. I've added this task:");

        System.out.println(task.toString());

        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    public void markAsDone(int index){

        listOfTasks.get(index - 1).markAsDone();

    }

}
