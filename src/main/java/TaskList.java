import java.util.List;
import java.util.ArrayList;

public class TaskList {
    
    private List<Task> list_of_Content;
    
    TaskList() {
        list_of_Content = new ArrayList<>();
    }
    
    public void addTask (Task task) {
        list_of_Content.add(task);
    }
    
    public void showNewContent(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have "+ list_of_Content.size() + " tasks in the list.");
    }
    
    public void showAllContent() {
            int no_of_tasks = list_of_Content.size();

            Ui ui = new Ui();
            System.out.println("Here are the tasks in your list: ");
            ui.printHorizontalLine();
            if (no_of_tasks == 0) {
                System.out.println("There is no tasks in the list, please add some tasks first ");
            } else {
                for (int i = 0; i < list_of_Content.size(); i = i + 1) {
                    String counter = Integer.toString(i + 1) + ". ";
                    System.out.println(counter + list_of_Content.get(i).timeConverted());
                }
            }
            ui.printHorizontalLine();
    }
    
    public int getSizeOfList() {
        return list_of_Content.size();
    }
    

    public List<Task> getTheList() {
        return list_of_Content;
    }

    public void set_Task_As_Done(int n) {
        System.out.println("Nice! I've marked this task as done: ");
        Task task = list_of_Content.get(n - 1);
        task.set_Task_As_Done();
        System.out.println(task.toString());
    }

    public void removeTask(int n) {
        System.out.println("Noted. I've removed this task:");
        Task task = list_of_Content.get(n - 1);
        System.out.println(task.toString());
        list_of_Content.remove(n - 1);
        System.out.println("Now you have "+ list_of_Content.size() + " tasks in the list.");
    }

    public void findKeyword(String keyword) {

        Ui ui = new Ui();
        int no_of_tasks = list_of_Content.size();
        int counter = 1;
        ui.printHorizontalLine();
        if (no_of_tasks == 0) {
            System.out.println("There is no tasks in the list ");
        } else {
            for (int i = 0; i < list_of_Content.size(); i = i + 1) {
                if (list_of_Content.get(i).containKeyWord(keyword)) {
                    System.out.println(counter + list_of_Content.get(i).timeConverted());
                    counter = counter + 1;
                }

            }
        }
        ui.printHorizontalLine();
    }
}
