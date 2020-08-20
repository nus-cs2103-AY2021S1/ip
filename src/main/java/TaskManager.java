import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> list;
    private final String linebreaker;
    TaskManager(String lb){
        this.list = new ArrayList<>();
        this.linebreaker = lb.repeat(50)+"\n";
    }
    /**
     * Parses the current list and prints the output
     */
    public void parseoutput() {
        StringBuilder sb = new StringBuilder(linebreaker);
        sb.append("\tHere are the tasks in your list:\n");
        for (int i = 0; i< this.list.size(); i++){
            sb.append("\t").append(i+1)
                    .append(". ")
                    .append(this.list.get(i).toString())
                    .append("\n");
        }
        sb.append(linebreaker);
        System.out.println(sb.toString());
    }

    public void doTask(String index) {
        int i = Integer.parseInt(index)-1;//0 indexing
        this.get(i).doTask();
        System.out.print(this.linebreaker);
        System.out.println("\tNice! I've marked this task as done: \n\t"+this.get(i));
        System.out.println(this.linebreaker);
    }

    /**
     * Get task from the internal list
     * @param i
     * @return
     */
    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Add task to the list
     * @param t
     */
    private void add(Task t){
        this.list.add(t);
        this.echo(t);
    }
    private void echo(Task t) {
        System.out.println(new StringBuilder().append(linebreaker).append("\tGot it. I've added this task:\n\t  ")
                .append(t).append("\n\tNow you have ")
                .append(this.list.size())
                .append(" tasks in the list.\n")
                .append(linebreaker).toString());
    }

    public void addToDo(String cmd){
        ToDo task = new ToDo(cmd);
        //TODO add a static factory method for making a ToDo task
        this.add(task);
    }
    public void addDeadline(String cmd){
        String[] timeSEP = extractTime(cmd);
        Deadline d = new Deadline(timeSEP[0], timeSEP[1]);
        add(d);
    }
    public void addEvent(String cmd){
        String[] timeSEP = extractTime(cmd);
        Event d = new Event(timeSEP[0], timeSEP[1]);
        add(d);
    }

    private String[] extractTime(String cmd){
        int i;
        if (cmd.contains("/at")){
            i = cmd.lastIndexOf("/at");
        }else if (cmd.contains("/by")){
            i = cmd.lastIndexOf("/by");
        }else return new String[2];
        //else throw an error here
        String[] c = new String[2];
        c[0] = cmd.substring(0,i);
        c[1] = cmd.substring(i+3);
        return c;
    }
}
