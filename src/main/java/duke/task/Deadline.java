package duke.task;

public class Deadline extends TimedTask {

    public Deadline(String description, String by) {
        super(description, by);
        this.connecting = " (by: ";
        this.firstLetter = "[D]";
    }

    public static Deadline load(String str) {
        String[] arr = str.split("\\|", 4);
        Deadline dl = new Deadline(arr[2], arr[3]);
        if (arr[1].equals("true")) {
            dl.isDone = true;
        }
        return dl;
    }

    @Override
    public String store() {
        return "D|" + super.store() + "|" + this.dateTime;
    }

}
