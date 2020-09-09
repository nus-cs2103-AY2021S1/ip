import java.util.List;
import java.util.ArrayList;

public class TaskList {
    
    private List<Task> listOfContents;

    /**
     * Constructor for TaskList object.
     */
    TaskList() {
        listOfContents = new ArrayList<>();
    }

    /**
     * Adds a task to taskList.
     * @param task Task to be added.
     */
    public void addTask (Task task) throws InvalidException {
        int totalTasks = listOfContents.size();
        boolean containDuplicate = false;
        for (int i = 0; i < totalTasks; i = i + 1) {
            if (task.new_task.equals(listOfContents.get(i).new_task)) {
                containDuplicate = true;
                break;
            }
        }
        if (!containDuplicate) {
            listOfContents.add(task);
        } else {
            throw new InvalidException("detect duplicates, the tasks is already in the list");
        }
    }
    
    public String addStringTask (Task task) {
        String message = "Got it. I've added this task: \n";
        message = message + task.timeConverted() + "\n";
        int currentSize = listOfContents.size();
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
        System.out.println("Now you have " + listOfContents.size() + " tasks in the list.");
    }

    /**
     * Prints the whole taskList.
     */
    public String showAllContent() {
            int no_of_tasks = listOfContents.size();

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
                for (int i = 0; i < listOfContents.size(); i = i + 1) {
                    String counter = Integer.toString(i + 1) + ". ";
                    String oneTask = counter + listOfContents.get(i).timeConverted();
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
        return listOfContents.size();
    }


    /**
     * Returns the taskList.
     * @return TaskList.
     */
    public List<Task> getTheList() {
        return listOfContents;
    }

    public void setTaskAsDone(int n) {

        assert n <= listOfContents.size();
        System.out.println("Nice! I've marked this task as done: ");
        Task task = listOfContents.get(n - 1);
        task.setTaskAsDone();
        System.out.println(task.toString());
    }

    /**
     * Deletes the nth task.
     * @param n Index of the task to be deleted.
     */
    public String removeTask(int n) {

        assert listOfContents.size() < n : "The number you choose is more than the total number of tasks it contains";
        String reply = "Noted. I've removed this task: \n";
//        System.out.println("Noted. I've removed this task:");
        Task task = listOfContents.get(n);
        System.out.println(task.toString());
        reply = reply + task.toString();
        listOfContents.remove(n);
        System.out.println("Now you have " + listOfContents.size() + " tasks in the list.");
        int currentSize = listOfContents.size();
        reply = reply + "\n" + "Now you have " + currentSize + " tasks in the list. \n";
        return reply;
       
    }

    /**
     * find whether the keyword from the user is in the list
     */
    public String findKeyword(String keyword) {

        Ui ui = new Ui();
        int no_of_tasks = listOfContents.size();
        int counter = 1;
        String tasks = "Here are the possible matches: \n" + ui.printLine() + "\n";
//        ui.printHorizontalLine();
        if (no_of_tasks == 0) {

            String noTasks = "There is no tasks in the list, please add some tasks first \n";
            tasks = tasks + noTasks;
//            System.out.println("There is no tasks in the list ");
            
        } else {
            boolean isTaskFound = false;
            for (int i = 0; i < listOfContents.size(); i = i + 1) {
                if (listOfContents.get(i).containKeyWord(keyword)) {
                    isTaskFound = true;
                    String oneTask = counter + ". " + listOfContents.get(i).timeConverted();
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
