

import java.util.ArrayList;
import java.util.List;

public class Task {
    public static List<Task> tasks = new ArrayList<>();
    private boolean done;
    private String name;
    private int ID;
    Task(String name){
        this.done = false;
        this.name = name;
        this.ID = tasks.size() + 1;
        tasks.add(this);
        //System.out.println("  " + "added: " + this.name + "\n" +
          //      "  ____________________________________________________________");
    }
    public void output(){
        System.out.println("  Got it. I've added this task:\n  " + this.toString() + "\n" +
                "  Now you have " + tasks.size() + " tasks in the list.\n" + "  ____________________________________________________________");
    }

    public static void setDone(int ID){
        if(ID >= tasks.size()){
            System.out.println(new DoneException(false).toString());
        }else {
            tasks.get(ID - 1).done = true;
            System.out.println("   Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(ID - 1).toString());
            System.out.println("  ____________________________________________________________");
        }
    }

    public static void listing(){
        System.out.println("   Here are the tasks in your list:");
        for(Task task : tasks){
             System.out.println("  " + task.ID + "." + task.toString());
        }
        System.out.println("  ____________________________________________________________");
    }
    public String toString(){
        if(this.done) {

            return "[" + "\u2713" + "] " + this.name;
        }
        return "[" + "\u2717" + "] " + this.name;
    }
}
