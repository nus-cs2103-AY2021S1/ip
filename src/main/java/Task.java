public class Task {
    public boolean done;
    public String task;
    public Task (String in) {
        task = in;
        done = false;
    }
    public void complete() {
        done = true;
    }

}
