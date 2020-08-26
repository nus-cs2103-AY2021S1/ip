import java.util.List;
import java.util.ArrayList;

public class TaskList {
    
    private List<Task> list_of_Contents;

    /**
     * Constructor for TaskList object.
     */
    TaskList() {
        list_of_Contents = new ArrayList<>();
    }

    /**
     * Adds a task to taskList.
     * @param task Task to be added.
     */
    public void addTask (Task task) {
        list_of_Contents.add(task);
    }

    /**
     * Prints the current task.
     * @param task Task to be printed.
     */
    public void showNewContent(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have "+ list_of_Contents.size() + " tasks in the list.");
    }

    /**
     * Prints the whole taskList.
     */
    public void showAllContent() {
            int no_of_tasks = list_of_Contents.size();

            Ui ui = new Ui();
            System.out.println("Here are the tasks in your list: ");
            ui.printHorizontalLine();
            if (no_of_tasks == 0) {
                System.out.println("There is no tasks in the list, please add some tasks first ");
            } else {
                for (int i = 0; i < list_of_Contents.size(); i = i + 1) {
                    String counter = Integer.toString(i + 1) + ". ";
                    System.out.println(counter + list_of_Contents.get(i).timeConverted());
                }
            }
            ui.printHorizontalLine();
    }

    /**
     * Returns the number of task in the list.
     * @return Integer indicating number of task in list.
     */
    public int getSizeOfList() {
        return list_of_Contents.size();
    }


    /**
     * Returns the taskList.
     * @return TaskList.
     */
    public List<Task> getTheList() {
        return list_of_Contents;
    }

    public void set_Task_As_Done(int n) {
        System.out.println("Nice! I've marked this task as done: ");
        Task task = list_of_Contents.get(n - 1);
        task.set_Task_As_Done();
        System.out.println(task.toString());
    }

    /**
     * Deletes the nth task.
     * @param n Index of the task to be deleted.
     */
    public void removeTask(int n) {
        System.out.println("Noted. I've removed this task:");
        Task task = list_of_Contents.get(n - 1);
        System.out.println(task.toString());
        list_of_Contents.remove(n - 1);
        System.out.println("Now you have "+ list_of_Contents.size() + " tasks in the list.");
    }

    /**
     * find whether the keyword from the user is in the list
     */
    public void findKeyword(String keyword) {

        Ui ui = new Ui();
        int no_of_tasks = list_of_Contents.size();
        int counter = 1;
        ui.printHorizontalLine();
        if (no_of_tasks == 0) {
            System.out.println("There is no tasks in the list ");
        } else {
            for (int i = 0; i < list_of_Contents.size(); i = i + 1) {
                if (list_of_Contents.get(i).containKeyWord(keyword)) {
                    System.out.println(counter + list_of_Contents.get(i).timeConverted());
                    counter = counter + 1;
                }

            }
        }
        ui.printHorizontalLine();
    }
}
