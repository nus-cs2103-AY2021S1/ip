public class Task {

    private String name;
    protected boolean isDone;

    public Task(String name){
        this.name = name;
        isDone = false;
    }

    public void completed(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return (isDone ? "[\u2713]" : "[\u2718]") + " " + name;
    }
}
