package Logic.Tasks;

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

    public String toData(){
        return (isDone ? "1|" : "0|") + name + "|";
    }

    @Override
    public String toString(){
        return (isDone ? "[\u2705]" : "[\u2718]") + " " + name;
    }
}
