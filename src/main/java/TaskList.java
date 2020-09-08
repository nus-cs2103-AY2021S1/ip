import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Task list to store an manage all tasks
 */
public class TaskList {


    private ArrayList<Task> tasks = new ArrayList<Task>();


    /**
     * gives the list of all tasks
     *
     * @return arrayList of my tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public static TaskList TaskList(Task ... myTasks){

        TaskList myList = new TaskList();


        for(int i = 0 ;i<myTasks.length;i++){
            myList.getTasks().add(myTasks[i]);
        }
        return myList;
    }



    public String findTaskUI(String keyword) {

        String myStr = "";

        ArrayList<Task> fTasks = new ArrayList<Task>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            if (currTask.relevant(keyword)) {
                fTasks.add(currTask);
            }
        }
        for (int i = 0; i < fTasks.size(); i++) {
           myStr+="" + (i + 1) + "." + fTasks.get(i)+"\n";
        }
        return myStr;

    }

    // 4 type ways to add task


    // UI
    public String addTaskUI(Task myTask) {
        this.tasks.add(myTask);
        return "added: " + myTask+"\n";
    }


    /**
     * convenient way to add tasks. Helps to create task first then add
     *
     * @param type type of task to add
     * @param task task details
     * @throws InSuffArgsException if not enough arguments
     */
    public String addTaskUI(String type, String task) throws InSuffArgsException {

        if (task.equals("")) {
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type, task);
        return addTaskUI(myTask);
    }

    /**
     * convenient way to add tasks. Helps to create task first then add
     *
     * @param type type of task to add
     * @param task task details
     * @param d1   localDate object
     * @throws InSuffArgsException if not enough arguments
     */
    public String addTaskUI(String type, String task, LocalDate d1) throws InSuffArgsException {
        if (task.equals("")) {
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type, task, d1);
        return addTaskUI(myTask);
    }

    /**
     * convenient way to add tasks. Helps to create task first then add
     *
     * @param type     type of task to add
     * @param task     task details
     * @param deadLine string representation of deadline
     * @throws InSuffArgsException if not enough arguments
     */
    public String addTaskUI(String type, String task, String deadLine) throws InSuffArgsException {

        if (task.equals("")) {
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type, task, deadLine);
        this.tasks.add(myTask);
        return "added: " + myTask +"\n";
    }

    //


    public String deleteTaskUI(int index) {

        Task myTask = this.tasks.get(index);

        if(myTask.isRecurrent()){
            Task nextTask = myTask.getNextRecurrent();
            this.tasks.remove(index);
            this.tasks.add(nextTask);

            return "removed: " + myTask+"\nadded recurring: "+myTask+"\n";

        }
        else{
            this.tasks.remove(index);
            return "removed: " + myTask;
        }


    }

    public String forceDeleteUI(int index) {
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        return "removed: " + myTask;

    }

    // prints number of tasks in two diff ways


    /**
     * lists out all the tasks in the list in a nice format
     */

    /**
     * prints out number of finished and unfinished task
     */
    public String numTaskUI() {
        String myStr = "";
        int done = 0;
        int undone = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            boolean finished = tasks.get(i).finished();
            if (finished) {
                done++;
            } else {
                undone++;
            }
        }
        myStr +=done + " finished tasks in the list.\n";
        myStr +=undone + " unfinished tasks in the list.\n";
        return myStr;
    }


    public String listUI() {
        String myStr = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            myStr+="" + (i + 1) + "." + this.tasks.get(i)+"\n";
        }
        return myStr;
    }
}
