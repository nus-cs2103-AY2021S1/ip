public class Task {
    private boolean isDone;
    private final String name;

    public Task(String name){
        this.isDone = false;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void complete(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString(){
        return this.getStatusIcon() +" " + this.getName();
    }
}
