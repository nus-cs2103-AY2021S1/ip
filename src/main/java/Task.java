public class Task {
    private String item;
    private boolean done;
    public Task(String item){
        this.item = item;
        this.done = false;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public String toString() {
        String doneString = "";
        if(done){
            doneString = "[✓]";
        }
        else{
            doneString = "[✗]";
        }
        return doneString + " " + item;
    }
}
