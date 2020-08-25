import java.time.format.DateTimeFormatter;

public abstract class Task {
    String desc;
    boolean isDone;
    char symbol;
    String time = "";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMM HH:mm");



    public Task(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        String check = isDone ? "\u2713" : "\u2718";
        return check + " " + symbol + " " + desc + " " + time;
    }

    public void done() {
        isDone = true;
    }

    public static Task parseToTask(String line) {
        char c = line.charAt(2);
        String desc = line.substring(4);
        Task t;

        if(c == ToDo.sym) {
            t = new ToDo(desc);
        } else if (c == Event.sym) {
            t = new Event(desc);
        } else {
            System.out.println("Error didn't recognize task symbol");
            return null;
        }

        char d = line.charAt(0);
        //check if its done
        if(d == '\u2713') {
            t.done();
        }

        return t;
    }

}
