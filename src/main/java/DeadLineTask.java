public class DeadLineTask extends Task {

    private static String display = "[D]";
    private final String date;

    DeadLineTask(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return display + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String saveString() {return "D/break/" + this.done + "/break/" + name + "/break/" + date;}
}
