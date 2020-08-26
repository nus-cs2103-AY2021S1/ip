public class Events extends Task{

    Events(String name, String time) {
        super(name,time);
    }

    public void setTime(String time){
        this.time = time;
    }

    @Override
    public String toString(){
        if (time != null) {
            if (super.completed) {
                return "[E]" + "[" + "✓" + "] " + name + " (at:" + time +")";
            } else {
                return "[E]" + "[" + "✗" + "] " + name + " (at:" + time +")";
            }
        } else {
            if (super.completed) {
                return "[E]" + "[" + "✓" + "] " + name;
            } else {
                return "[E]" + "[" + "✗" + "] " + name;
            }
        }
    }
}
