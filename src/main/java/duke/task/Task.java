package duke.task;

public class Task {
    protected String title;
    protected boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task markAsDone() {
        Task newTask = new Task(title);
        newTask.isDone = true;
        return newTask;
    }

    public String toString() {
        String status = isDone ? "[✓]" : "[✗]";
        return status + " " + title;
    }

    public String toData() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public static Task fromData(String st) {
        String[] segment = st.split(" \\| ");
        String type = segment[0];
        boolean isDone = segment[1].equals("1");
        String title = segment[2];
        if (type.equals("T")) {
            return new Todo(title, isDone);
        } else {
            String time = segment[3];
            return type.equals("E")
                    ? new Event(title, time, isDone)
                    : new Deadline(title, time, isDone);
        }
    }
}