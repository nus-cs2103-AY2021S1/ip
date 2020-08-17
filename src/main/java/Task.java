abstract class Task {

    protected final String name;
    protected final boolean isDone;

    public Task (String name , boolean isDone){
        this.name = name;
        this.isDone = isDone;
    }

    public String getName(){
        return this.name;
    }

    public boolean check () {
        return this.isDone;
    }

    public abstract Task setToTrue();

    @Override
    public String toString(){
        if (isDone) {
            return "[✓] " + this.getName();
        } else {
            return "[✗] " + this.getName();
        }
    }
}
