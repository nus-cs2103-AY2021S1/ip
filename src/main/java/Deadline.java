public class Deadline extends Task{

    String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    Deadline(String name, String time, String completed) {
        super(name, completed);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), time);
    }

    @Override
    public String[] toArray() {
        String[] strings = new String[4];
        strings[0] = "[D]";
        strings[1] = completed ? "1" : "0";
        strings[2] = name;
        strings[3] = time;
        return strings;
    }
}
