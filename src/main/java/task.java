public class task {
    private boolean done;
    private String work;

    public task(String work){
        this.done = false;
        this.work = work;
    }

    public void updateStatus(){
        done = true;
    }

    public boolean isDone(){
        return done;
    }

    public String toString(){
        return this.work;
    }
}
