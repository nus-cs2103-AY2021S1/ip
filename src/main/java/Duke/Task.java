package Duke;

public class Task {
    private String task;
    private Boolean status;
    public Task(String task){
        this.task = task;
        this.status = false;
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
