import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {


    private ArrayList<Task> tasks = new ArrayList<Task>();

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }


    // 3 type ways to add task

    public void addTask(String type, String task) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    public void addTask(String type, String task, LocalDate d1) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task,d1);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }
    public void addTask(String type,String task,String deadLine) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task,deadLine);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    // task deletion

    public void deleteTask(int index) {
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        System.out.println("removed: " + myTask);
        numTask();
    }

    // prints number of tasks in two diff ways

    public void numTask() {
        int done = 0;
        int undone = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            boolean finished = tasks.get(i).finished();
            if (finished) {
                done++;
            } else {
                undone++;
            }
        }
        System.out.println(done + " finished tasks in the list.");
        System.out.println(undone + " unfinished tasks in the list.");
    }

    public void list(){
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("" + (i + 1)+"." + this.tasks.get(i));
        }
    }
}
