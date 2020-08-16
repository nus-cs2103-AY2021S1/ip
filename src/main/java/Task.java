public class Task {

    private final String name;
    private final boolean isDone;

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

    public Task setDone(){
        return new Task(this.name, true);
    }
}
