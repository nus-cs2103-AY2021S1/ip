public abstract class Task {

    protected String tag;
    protected boolean isDone = false;
    protected String taskName;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public void complete(){
        this.isDone = true;
    }

    public void printAddTask(){
        System.out.println(
                String.format("Got it. I've added this task:\n  %s",this.toString())
        );
    }
}
