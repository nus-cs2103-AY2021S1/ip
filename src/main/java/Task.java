import java.util.ArrayList;
import java.util.List;

public class Task {
    static List<Task> tasks = new ArrayList<>();
    private boolean done;
    private String name;
    private int ID;
    Task(String name){
        this.done = false;
        this.name = name;
        this.ID = tasks.size() + 1;
        tasks.add(this);
        System.out.println("  " + "added: " + this.name + "\n" +
                "  ____________________________________________________________");
    }
    public void setDone(){
        this.done = true;
        tasks.get(this.ID - 1).done = true;
    }
    public void donePrint(){
        System.out.println("   Nice! I've marked this task as done:");
        System.out.println("   " + this.toString());
        System.out.println("  ____________________________________________________________");
    }
    public static void listing(){
        System.out.println("   Here are the tasks in your list:");
        for(Task task : tasks){
            System.out.println("  " + task.ID + "." + task);
        }
        System.out.println("\n" + "  ____________________________________________________________");
    }
    public String toString(){
        if(this.done) {
            return "[✓] " + this.name;
        }
        return "[✗] " + this.name;
    }
}