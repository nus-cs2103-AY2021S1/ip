package Duke;

public class Task {
    public final static String TICK = "\u2713";
    public final static String CROSS = "\u2718";

    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone(){
        this.done = true;
    }

    public boolean getDone(){
        return this.done;
    }

    public String getName(){
        return this.name;
    }

    public String getParsedData(){
        return String.valueOf(this.done) + "/" + this.name;
    }

    public String toString(){
        String symbol = done ? Task.TICK : Task.CROSS;
        return "[" + symbol + "] " + name;
    }
}

