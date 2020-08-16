import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> list;

    Tasks() {
        this.list = new ArrayList<>();
    }

    void addTask(String task) throws DukeException {
        String type = task.split(" ")[0];
        String temp = task.strip();
        if (temp.equals("todo") || temp.equals("deadline") || temp.equals("event")) {
            throw new InvalidTaskTypeException("☹ OOPS!!! The description of a " + temp + " cannot be empty.");
        } else if (temp.equals("")) {
            throw new InvalidTaskTypeException("☹ OOPS!!! The type of a task cannot be empty.");
        }
        if (type == null || (!type.equals("todo") && !type.equals("deadline") && !type.equals("event"))) {
            throw new InvalidTaskTypeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        String details = task.substring(type.length());
        if (type.equals("todo")) {
            ToDo t = new ToDo(details.strip());
            this.list.add(t);
            echo(t);
        } else {
            String[] detailsArray = details.split("/");
            String[] keywords = detailsArray[1].split(" ");
            String datetime = detailsArray[1].substring(keywords[0].length() + 1);
            if (type.equals("deadline")) {
                Deadline d = new Deadline(detailsArray[0].strip(), keywords[0] + ": " + datetime);
                this.list.add(d);
                echo(d);
            } else if (type.equals("event")) {
                Event e = new Event(detailsArray[0].strip(), keywords[0] + ": " + datetime);
                this.list.add(e);
                echo(e);
            } else {
                echo(new Task(task));
            }
        }
    }

    void listTasks() {
        System.out.println("\t___________________________________________________________________________");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
        System.out.println("\t___________________________________________________________________________\n");
    }

    void echo(Task t) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + t);
        System.out.println("\t Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("\t___________________________________________________________________________\n");
    }

    void markDone(int index) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        this.list.get(index - 1).markAsDone();
        System.out.println("\t   " + this.list.get(index - 1));
        System.out.println("\t___________________________________________________________________________\n");
    }
}
