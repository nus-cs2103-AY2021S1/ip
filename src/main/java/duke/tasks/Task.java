package duke.tasks;

import java.util.Optional;

/**
 * The class for an abstract idea of task.
 */
public class Task {

    private String task;

    private Boolean status;

    protected Task(String task) { //not meant to be instantiated by classes outside of this package
        this.task = task;
        this.status = false;
    }

    protected Task(String task, boolean status) {
        this.task = task;
        this.status = status;
    }

    public String getTaskType() {
        return "task";
    }

    public Optional<String> getTime() {
        return Optional.empty();
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override public String toString() {
        return "[" + statusToIcon() + "] " + this.task;
    }

    private String statusToIcon() {
        if(this.status){
            return "✅";
        }else{
            return "❎";
        }
    }
}
