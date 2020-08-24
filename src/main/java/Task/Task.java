package Task;

abstract public class Task {

    protected final String name;
    protected final boolean isDone;

    public Task (String name , boolean isDone){
        this.name = name;
        this.isDone = isDone;
    }

    public String getName(){
        return this.name;
    }

    public abstract String getType();

    public int isDone(){
        return isDone ? 1 : 0;
    }

    public abstract Task setToTrue();

    public abstract String getEnd();

    @Override
    public String toString(){
        if (isDone) {
            return "[✓] " + this.getName();
        } else {
            return "[✗] " + this.getName();
        }
    }
}
