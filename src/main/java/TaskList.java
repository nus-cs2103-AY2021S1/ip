import java.util.ArrayList;
public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public int getSize(){
        return tasks.size();
    }

    public void addTask(Task task){
        tasks.add(task);
        System.out.println("Got it. I've added this task:" + "\n" + " " + task.toString() + "\n"
                + "Now you have " + getSize() + " tasks in the list");
    }

    public void deleteTask(int taskIndex){
        Task deleted = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task: " + "\n" +
                deleted.toString() + "\n" + "Now you have " + getSize() + " tasks in the list.");
    }

    public void doneTask(int taskIndex){
        tasks.get(taskIndex).markAsDone();
    }

    public void showTaskList(){
        System.out.println("Here are the tasks in your list:" + "\n");
        for (int i = 0; i < getSize(); i++) {
            Integer listNum = i + 1;
            System.out.println(listNum.toString() + "." + tasks.get(i).toString());
        }
    }

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
            Integer listNum = i + 1;
            System.out.println(listNum.toString() + "." + keyTasks.get(i).toString());
        }
    }
}
