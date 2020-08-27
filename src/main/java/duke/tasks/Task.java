package duke.tasks;

import java.util.Optional;

public class Task {
    private String task;
    private Boolean status;
    Task(String task){
        this.task = task;
        this.status = false;
    }

    Task(String task, boolean status) {
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

    @Override public String toString(){
        return "[" + statusToIcon() + "] " + this.task;
    }

    private String statusToIcon(){
        if(this.status){
            return "✅";
        }else{
            return "❎";
        }
    }
}
