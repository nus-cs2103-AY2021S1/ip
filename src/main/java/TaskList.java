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
    
    public String addStringTask (Task task) {
        String message = "Got it. I've added this task: \n";
        message = message + task.timeConverted() + "\n";
        int currentSize = list_of_Contents.size();
        message = message + "Now you have " + currentSize + " tasks in the list. \n";
        return message;
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
    public String showAllContent() {
            int no_of_tasks = list_of_Contents.size();

            Ui ui = new Ui();
//            System.out.println("Here are the tasks in your list: ");
            String allTasks = "Here are the tasks in your list: \n";
            allTasks = allTasks + ui.printLine() + "\n";
//            ui.printHorizontalLine();
            if (no_of_tasks == 0) {
                
                String noTasks = "There is no tasks in the list, please add some tasks first \n";
                allTasks =  allTasks + noTasks;
//                System.out.println("There is no tasks in the list, please add some tasks first ");
            } else {
                for (int i = 0; i < list_of_Contents.size(); i = i + 1) {
                    String counter = Integer.toString(i + 1) + ". ";
                    String oneTask = counter + list_of_Contents.get(i).timeConverted();
                    allTasks = allTasks + oneTask + "\n";
                   // System.out.println(counter + list_of_Contents.get(i).timeConverted());
                }
            }
        //            ui.printHorizontalLine(); 
            return allTasks;

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
    public String removeTask(int n) {
        
        String reply = "Noted. I've removed this task: \n";
//        System.out.println("Noted. I've removed this task:");
        Task task = list_of_Contents.get(n);
        System.out.println(task.toString());
        reply = reply + task.toString();
        list_of_Contents.remove(n);
        System.out.println("Now you have "+ list_of_Contents.size() + " tasks in the list.");
        int currentSize = list_of_Contents.size();
        reply = reply + "\n" + "Now you have "+ currentSize + " tasks in the list. \n";
        return reply;
       
    }

    /**
     * find whether the keyword from the user is in the list
     */
    public String findKeyword(String keyword) {

        Ui ui = new Ui();
        int no_of_tasks = list_of_Contents.size();
        int counter = 1;
        String tasks = "Here are the possible matches: \n" + ui.printLine() + "\n";
//        ui.printHorizontalLine();
        if (no_of_tasks == 0) {

            String noTasks = "There is no tasks in the list, please add some tasks first \n";
            tasks =  tasks + noTasks;
//            System.out.println("There is no tasks in the list ");
            
        } else {
            boolean isTaskFound = false;
            for (int i = 0; i < list_of_Contents.size(); i = i + 1) {
                if (list_of_Contents.get(i).containKeyWord(keyword)) {
                    isTaskFound = true;
                    String oneTask = counter + ". " + list_of_Contents.get(i).timeConverted();
                    tasks = tasks + oneTask + "\n";
//                    System.out.println(counter + list_of_Contents.get(i).timeConverted());
                    counter = counter + 1;
                }
            }
            
            if (!isTaskFound) {
                tasks = tasks + "Sorry, we cannot find a tasks that match the keyword in the list :( \n";
            }
        }
//        ui.printHorizontalLine();
        tasks = tasks + ui.printLine();
        return tasks;
    }
}
