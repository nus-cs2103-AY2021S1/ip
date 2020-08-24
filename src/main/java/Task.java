public class Task implements java.io.Serializable{
    String text;
    boolean done;
    public Task(String text){
        this.text = text;
        this.done = done;
    }
    public String toString(){
        return "[" + (done ? "✓" : "✗") + "] " + text;
    }
}
