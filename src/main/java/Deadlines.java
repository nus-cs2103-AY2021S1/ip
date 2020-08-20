public class Deadlines extends Task{
    String time;

    Deadlines(String name, String time) {
        super(name);
        this.time = time;
    }

    public void setTime(String time){
        this.time = time;
    }

    @Override
    public String toString(){
        if (time != null) {
            if (super.completed) {
                return "[D]" + "[" + "✓" + "] " + name + " (by:" + time +")";
            } else {
                return "[D]" + "[" + "✗" + "] " + name + " (by:" + time +")";
            }
        } else {
            if (super.completed) {
                return "[D]" + "[" + "✓" + "] " + name;
            } else {
                return "[D]" + "[" + "✗" + "] " + name;
            }
        }
    }
}
