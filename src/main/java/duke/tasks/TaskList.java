package duke.tasks;

import java.util.ArrayList;

/**
 * List of tasks.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * Construct a new TaskList.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks){
        this.listOfTasks=listOfTasks;
    }

    /**
     * Find the size of the list of tasks.
     *
     * @return the size.
     */
    public int findListSize() {
     return listOfTasks.size();
    }

    /**
     * Print out the tasks in the list one by one.
     */
    public String printTaskList() {
        String result = "Here are the tasks in your list: "+"\n";

        int number = listOfTasks.size();

        for(int i = 0; i < number; i++){

            result = result+ listOfTasks.get(i).showTask(i+1);

        }
        return result;
    }

    /**
     * Remove the task from the list.
     *
     * @param index The position index of the task in the list.
     */
    public String deleteTask(int index) {

        Task toBeDeletedTask = listOfTasks.get(index-1);

        listOfTasks.remove(index-1);

        int size = listOfTasks.size();

        String result = "Noted. I've removed this task:" + "\n";

        result = result + toBeDeletedTask.toString()+"\n";

        if(size == 0){
            result = result+"Now your task list is empty."+"\n";
        }
        else if(size == 1){
            result = result+"Now you have 1 task in the list."+"\n";
        }
        else{
            result = result+"Now you have " + size + " tasks in the list"+"\n";
        }

        return result;

    }

    /**
     * Find the task that fit the keyword.
     * @param keyWord The keyword to be used for searching
     * @return An ArrayList of tasks that meet the searching requirements
     */
    public String findTask(String keyWord) {
        String result = "";
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
        if(tasksFound.size()==0){
            result=result+"Sorry, no task related to "+keyWord+ " is found."+"\n";
        }else{
            result=result+"Here are the tasks found: "+"\n";
            for(Task task: tasksFound){
                result = result+task.toString()+"\n";
            }
        }
        return result;
    }

    /**
     * Add the task to the list.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {

        listOfTasks.add(task);

        int taskSize = listOfTasks.size();

        String result = "Got it. I've added this task:"+"\n"+task.toString()+"\n"
                +"Now you have " + taskSize + " tasks in the list."+"\n";

        return result;
    }

    /**
     * Mark the task as done.
     * @param index The position index of the task.
     */
    public String markAsDone(int index) {

        return listOfTasks.get(index - 1).markAsDone();

    }

    public ArrayList<Task> getListOfTasks(){
        return this.listOfTasks;
    }

    public String toString(){
        String result="";
        for(Task task: listOfTasks){
            result = result+task.toString()+"\n";
        }
        return result;
    }

}
