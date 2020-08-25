package Tasks;

import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> list;
    public TaskManager(String lb){
        this.list = new ArrayList<>();
    }
    /**
     * Parses the current list and prints the output
     */
    public String parseoutput() {
        StringBuilder sb = new StringBuilder("");
        if (this.list.size()>0) {
            sb.append("\tHere are the tasks in your list:\n");
            for (int i = 0; i < this.list.size(); i++) {
                sb.append("\t").append(i + 1)
                        .append(". ")
                        .append(this.list.get(i).toString())
                        .append("\n");
            }
        }
        else{
            sb.append("\tThere are no tasks in your list!\n");
        }
        return sb.toString();
    }

    public String doTask(String index) throws DukeCommandException, DukeIndexException {
        try{
            int i = Integer.parseInt(index)-1;//0 indexing
            this.get(i).doTask();
            return "\tNice! I've marked this task as done: \n\t"+this.get(i)+"\n";
        } catch (IllegalArgumentException e){
            throw new DukeCommandException(index);
        } catch (IndexOutOfBoundsException e){
            throw new DukeIndexException(index, list.size());
        }
       
    }

    public String deleteTask(String index) throws DukeCommandException, DukeIndexException {
        try{
            int i = Integer.parseInt(index)-1;//0 indexing
            Task t = this.get(i);
            this.list.remove(i);
            return new StringBuilder().append("\tNoted! I've removed this task from your list: \n\t")
                    .append(t)
                    .append("\n\tNow you have ")
                    .append(this.list.size())
                    .append(" tasks in the list.\n").toString();
        } catch (IllegalArgumentException e){
            throw new DukeCommandException(index);
        } catch (IndexOutOfBoundsException e){
            throw new DukeIndexException(index, list.size());
        }

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
     * generic polymorphic data flow for adding a task to the runtime database
     * @param t
     * @return String to be wrapped and printed
     */
    private String add(Task t){
        this.list.add(t);
        return this.echo(t);
    }

    /**
     * Returns string builder of the task
     * @param t
     * @return
     */
    private String echo(Task t) {
        return new StringBuilder().append("\tGot it. I've added this task:\n\t  ")
                .append(t).append("\n\tNow you have ")
                .append(this.list.size())
                .append(" tasks in the list.\n")
                .toString();
    }

    public String addToDo(String cmd) throws DukeNoInputException {
        if (cmd.isBlank()){
            throw new DukeNoInputException(cmd);
        }
        ToDo task = new ToDo(cmd);
        //TODO add a static factory method for making a Tasks.ToDo task
        return this.add(task);
    }
    public String addDeadline(String cmd) throws DukeDateTimeException, DukeNoInputException {
        if (cmd.isBlank()){
            throw new DukeNoInputException(cmd);
        }
        String[] timeSEP = extractTime(cmd);
        Deadline d = new Deadline(timeSEP[0], timeSEP[1]);
        return add(d);
    }
    public String addEvent(String cmd) throws DukeDateTimeException, DukeNoInputException {
        if (cmd.isBlank()){
            throw new DukeNoInputException(cmd);
        }
        String[] timeSEP = extractTime(cmd);
        Event e = new Event(timeSEP[0], timeSEP[1]);
        return add(e);
    }

    private String[] extractTime(String cmd) throws DukeDateTimeException {
        cmd = cmd.strip();
        int i;
        if (cmd.contains("/at")){
            i = cmd.lastIndexOf("/at");
        }else if (cmd.contains("/by")){
            i = cmd.lastIndexOf("/by");
        }else {
            //else throw an error here
            throw new DukeDateTimeException(cmd);
        }
        String[] c = new String[2];
        c[0] = cmd.substring(0,i).strip();
        c[1] = cmd.substring(i+3).strip();
        return c;
    }
}
