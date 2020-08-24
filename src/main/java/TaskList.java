import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

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