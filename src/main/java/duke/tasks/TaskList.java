package duke.tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * Construct a new TaskList.
     */
    public TaskList(){
        listOfTasks = new ArrayList<>();
    }

    /**
     * Find the size of the list of tasks.
     * @return the size.
     */
    public int findListSize(){
     return listOfTasks.size();
    }

    /**
     * Print out the tasks in the list one by one.
     */
    public void printTaskList(){
        System.out.println("Here are the tasks in your list:");

        int number = listOfTasks.size();

        for(int i = 0; i < number; i++){

            listOfTasks.get(i).showTask(i+1);

        }
    }

    /**
     * Remove the task from the list.
     * @param index The position index of the task in the list.
     */
    public void deleteTask(int index){

        Task toBeDeletedTask = listOfTasks.get(index-1);

        listOfTasks.remove(index-1);

        int size = listOfTasks.size();

        System.out.println("Noted. I've removed this task:");

        System.out.println(toBeDeletedTask.toString());

    }

    public ArrayList<Task> findTask(String keyWord){
        ArrayList<Task> tasksFound = new ArrayList<>();
        for(Task task: listOfTasks){
            String[] words = task.name.split(" ");
            for(String word: words){
                if(word.equals(keyWord)){
                    tasksFound.add(task);
                    break;
                }
            }
        }
        return tasksFound;
    }

    /**
     * Add the task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task){

        listOfTasks.add(task);

        int taskSize = listOfTasks.size();

        System.out.println("Got it. I've added this task:");

        System.out.println(task.toString());

        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    /**
     * Mark the task as done.
     * @param index The position index of the task.
     */
    public void markAsDone(int index){

        listOfTasks.get(index - 1).markAsDone();

    }

}
