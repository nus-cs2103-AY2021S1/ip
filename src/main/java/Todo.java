
public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString(){
        if (done) {
            return String.format ("[T][DONE] %s", this.task);
        } else {
            return String.format ("[T][NOT DONE] %s", this.task);
        }
    }

}