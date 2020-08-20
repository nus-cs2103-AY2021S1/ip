package tasks;

public class Task {
    boolean done;
    String task;

    public Task(String task) {
        this.task = task;
        done = false;
    }

    public String getTask() {
        return task;
    }

    void setTaskDone(boolean boo){
        done = boo;
    }

    @Override
    public String toString(){
        return task;
    }
}
