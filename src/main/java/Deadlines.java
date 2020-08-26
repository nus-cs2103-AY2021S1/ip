public class Deadlines extends Task{

    Deadlines(String name, String time) {
        super(name,time);
    }


    public String getTime() {
        return time;
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
