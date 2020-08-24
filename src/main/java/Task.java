import java.util.ArrayList;

public class Task {
    private String description;
    private Boolean done;

     public Task(String name) {
        this.description = name;
        this.done = false;
    }
    public Task(String name, boolean done) {
         this.description = name;
         this.done = done;
    }

    public static void addTask(String task, ArrayList<Task> store, boolean print) {
        String type = task.split(" ", 2)[0];
        String title = task.split(" ", 2)[1];

        //System.out.println(title);
        if (type.equals("todo")) {
            store.add(new Todo(title));
        } else {
            String description = title.split("/", 2)[0];
            Task newTask;

            if (type.equals("deadline")) {
                String by = title.split("/by", 2)[1];
                newTask = new Deadline(description, by);
                store.add(newTask);
            }
            if (type.equals("event")) {
                String at = title.split("/at", 2)[1];
                newTask = new Event(description, at);
                store.add(newTask);
            }
        }
        if (print) {
            System.out.println("Got it. I've added this task:\n" + store.get(store.size() - 1));
        }
    }

    public static void addTaskFromFile(String task, ArrayList<Task> store, boolean print) {
        String type = task.split(" ", 2)[0];
        String remain = task.split(" ", 2)[1];
        String done = remain.split(" ", 2)[0];
        String title = remain.split(" ", 2)[1];
        //System.out.println(title);
        if (type.equals("todo")) {
            store.add(new Todo(title, Boolean.valueOf(done)));
        } else {
            String description = title.split("/", 2)[0];
            Task newTask;

            if (type.equals("deadline")) {
                String by = title.split("/by", 2)[1];
                newTask = new Deadline(description, by, Boolean.valueOf(done));
                store.add(newTask);
            }
            if (type.equals("event")) {
                String at = title.split("/at", 2)[1];
                newTask = new Event(description, at, Boolean.valueOf(done));
                store.add(newTask);
            }
        }
        if (print) {
            System.out.println("Got it. I've added this task:\n" + store.get(store.size() - 1));
        }
    }

    public String inputStyle() {
         return this.done + " " +description;
    }

    public String getStatusIcon() {
         return done ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
         this.done = true;
    }
    @Override
    public String toString() {
         return "[" + this.getStatusIcon() + "] " + description;
    }
}
