public class Task {

    private boolean isDone = false;
    private String taskName;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public void complete(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return "[" + symbol + "]" +  " " + taskName;
    }
}
