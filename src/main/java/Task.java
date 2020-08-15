public class Task {
    private String desc;
    private boolean done;

    public Task(String desc) {
        this(desc,false);
    }

    public Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    public void setDone() {
        this.done = true;
    }

    public String getDesc() {
        return this.desc;
    }


    @Override
    public String toString(){
        return String.format("[%c] %s", (this.done) ? '✓' : '✗', this.desc);
    }
}
