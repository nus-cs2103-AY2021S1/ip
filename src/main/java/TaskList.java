import java.util.ArrayList;

public class TaskList {


    private ArrayList<Task> tasks = new ArrayList<Task>();

    public String findTask(String keyword) {
        ArrayList<Task> listOfFoundItems = new ArrayList<Task>();
        String output = "";
        for (Task s : tasks) {
            String[] items = s.description.split(" ");
            for (int i = 0; i < items.length; i++) {
                if (keyword.equals(items[i])) {
                    listOfFoundItems.add(s);
                    break;
                }
            }

        }

        int iterator = 1;
        output += "Here are the matching tasks in your list:\n";

        for (Task s : listOfFoundItems) {
            output += iterator + "." + s.toString();
            iterator++;
        }
        return output;
    }


    public String addTask(Task myTask) {
        this.tasks.add(myTask);
        return "added: " + myTask;
    }



    public String deleteTask(int index) {
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        return "removed: " + myTask;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String showList() {
        int iterator = 1;
        String output = "Here are the tasks in your list:";
        for (Task s : tasks) {
            output += iterator + "." + s.toString();
            iterator++;
        }
        return output;
    }
}
