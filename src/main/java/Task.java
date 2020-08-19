public class Task {
    String text;
    boolean done;
    Task(String text, boolean done){
        this.text = text;
        this.done = done;
    }
    public String toString(){
        return "[" + (done ? "✓" : "✗") + "] " + text + "\n";
    }
}
