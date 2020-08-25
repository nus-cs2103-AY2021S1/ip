public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
        this.tag = "T";
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return String.format("[%s][%s] %s",tag,symbol,taskName);
    }

    @Override
    public String safeFileFormat(){
        int done = isDone ? 1 : 0;
        return String.format("%s | %d | %s \n",tag,done,taskName);
    }
}