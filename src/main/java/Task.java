public class Task {
    private boolean done;
    private String work;

    public Task(String work){
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
        if (!this.done){
            return "[✗]" + this.work;
        } else {
            return "[✓]" + this.work;
        }
    }
}
