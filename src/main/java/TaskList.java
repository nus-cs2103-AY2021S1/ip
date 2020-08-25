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
    // find task

    public void findTask(String keyword) {
        ArrayList<Task> fTasks = new ArrayList<Task>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            if (currTask.relevant(keyword)) {
                fTasks.add(currTask);
            }
        }
        for (int i = 0; i < fTasks.size(); i++) {
            System.out.println("" + (i + 1) + "." + fTasks.get(i));
        }

    }


    // 4 type ways to add task

    /**
     * straightforward method to add task to list
     *
     * @param myTask task to be added
     */
    public void addTask(Task myTask) {
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }


    /**
     * convenient way to add tasks. Helps to create task first then add
     *
     * @param type type of task to add
     * @param task task details
     * @throws InSuffArgsException if not enough arguments
     */
    public void addTask(String type, String task) throws InSuffArgsException {

        if (task.equals("")) {
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type, task);
        addTask(myTask);
    }

    /**
     * convenient way to add tasks. Helps to create task first then add
     *
     * @param type type of task to add
     * @param task task details
     * @param d1   localDate object
     * @throws InSuffArgsException if not enough arguments
     */
    public void addTask(String type, String task, LocalDate d1) throws InSuffArgsException {
        if (task.equals("")) {
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type, task, d1);
        addTask(myTask);
    }

    /**
     * convenient way to add tasks. Helps to create task first then add
     *
     * @param type     type of task to add
     * @param task     task details
     * @param deadLine string representation of deadline
     * @throws InSuffArgsException if not enough arguments
     */
    public void addTask(String type, String task, String deadLine) throws InSuffArgsException {

        if (task.equals("")) {
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type, task, deadLine);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    // task deletion

    /**
     * remove a task from list
     *
     * @param index the position of task to be removed
     */
    public void deleteTask(int index) {
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        System.out.println("removed: " + myTask);
        numTask();
    }

    // prints number of tasks in two diff ways

    /**
     * prints out number of finished and unfinished task
     */
    public void numTask() {
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
        System.out.println(done + " finished tasks in the list.");
        System.out.println(undone + " unfinished tasks in the list.");
    }

    /**
     * lists out all the tasks in the list in a nice format
     */

    public void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("" + (i + 1) + "." + this.tasks.get(i));
        }
    }
}
