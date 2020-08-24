import tasks.Task;

import java.util.ArrayList;

/**
 * An object which contains the list of tasks a user needs to do
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialized the arraylist with a list of tasks
     * @param tasks The list of tasks provided
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void update(Task task){
        tasks.add(task);
    }

    public Task get(int i){
        return tasks.get(i - 1);
    }

    public void delete(int i){
        tasks.remove(i - 1);
    }

    public void updateStatus(int i){
        tasks.get(i - 1).updateStatus();
    }

    public int getSize(){
        return tasks.size();
    }

    /**
     * The tasklist has to be converted into a String format that is easily parsable when the file is reopened
     * @return String in a format that easily parsable upon reopening the file
     */
    public String save(){
        StringBuilder line = new StringBuilder();
        for (Task task : tasks){
            if (!task.istodo()){
                String append = task.description() + task.getWork() + "|" + task.getDate() + "\n";
                line.append(append);
                continue;
            }
            String append = task.toString() + "\n";
            line.append(append);
        }
        return line.toString();
    }

    public String toString(){
        StringBuilder line = new StringBuilder();
        for (Task task : tasks) {
            line.append(task.toString());
            line.append('\n');
        }
        return line.toString();
    }
}