public class Task {
    private final String task;
    private final boolean isDone;

    Task(String task, boolean isDone){
        this.task = task;
        this.isDone = isDone;
    }

    public Task done(){
        return new Task(this.task, true);
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public String getTask(){
        return this.task;
    }
}
