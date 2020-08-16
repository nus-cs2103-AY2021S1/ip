public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString(){
        if (done) {
            return String.format ("[T][\u2713] %s", this.task);
        } else {
            return String.format ("[T][\u2718] %s", this.task);
        }
    }

}
