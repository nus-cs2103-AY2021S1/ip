import java.util.ArrayList;
public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * TaskList constructor
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * get number of tasks in current list
     * @return size of list of tasks
     */
    public int getSize(){
        return tasks.size();
    }

    /**
     * add a task in list
     * @param task the task to be added
     */
    public void addTask(Task task){
        tasks.add(task);
        System.out.println("Got it. I've added this task:" + "\n" + " " + task.toString() + "\n"
                + "Now you have " + getSize() + " tasks in the list");
    }

    /**
     * delete a task in list
     * @param taskIndex index of task to be deleted
     */
    public void deleteTask(int taskIndex){
        Task deleted = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task: " + "\n" +
                deleted.toString() + "\n" + "Now you have " + getSize() + " tasks in the list.");
    }

    /**
     * done a task in list
     * @param taskIndex index of task to be done
     */
    public void doneTask(int taskIndex){
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * show all tasks in the list in string format
     */
    public void showTaskList(){
        System.out.println("Here are the tasks in your list:" + "\n");
        for (int i = 0; i < getSize(); i++) {
            int listNum = i + 1;
            System.out.println(listNum + "." + tasks.get(i).toString());
        }
    }

    /**
     * find tasks matching a particular keyword
     * @param keyword the keyword to be found
     */
    public void findKeyword(String keyword){
        ArrayList<Task> keyTasks = new ArrayList<>();
        for(int i = 0; i < getSize(); i++){
            if(this.tasks.get(i).description.contains(keyword)){
                keyTasks.add(this.tasks.get(i));
            }
        }

        if(keyTasks.size() == 0){
            System.out.println("Sorry there is no matching task :-(");
        }

        System.out.println("Here are the matching tasks in your list:\n");
        for(int i = 0; i < keyTasks.size(); i++){
            int listNum = i + 1;
            System.out.println(listNum + "." + keyTasks.get(i).toString());
        }
    }
}
