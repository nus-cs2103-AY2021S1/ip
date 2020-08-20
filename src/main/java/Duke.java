import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> storage;

    public Duke() {
        storage = new ArrayList<Task>();
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //stores and prints out command
    public void store(String s) {
        Task newTask = new Task(s);
        this.store(newTask);
    }

    public void store(Task t) {
        this.storage.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t.toString());
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
    }

    public void addToDo(String s) {
        ToDo newToDo = new ToDo(s);
        this.store(newToDo);
    }

    public void addDeadline(String s, String deadline) {
        Deadline newDeadline = new Deadline(s, deadline);
        this.store(newDeadline);
    }

    public void addEvent(String s, String when) {
        Event newEvent = new Event(s, when);
        this.store(newEvent);
    }

    public Task getTask(int i) {
        return this.storage.get(i-1);
    }

    public void doTask(int i) {
        Task temp = this.getTask(i);
        temp.doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + temp.toString());
    }

    public void printList() {
        for (int i = 0; i < this.storage.size(); i++) {
            String printText = Integer.toString(i+1) + ". " + this.storage.get(i).toString();
            System.out.println(printText);
        }
    }

    public static void main(String[] args) {
        //initialize Duke and send welcome message
        Duke duke = new Duke();
        duke.greet();

        //input loop
        Scanner sc = new Scanner(System.in);

        while(true) {
            String s = sc.nextLine();
            String command = s.split(" ")[0];
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                duke.printList();
            } else if (command.equals("done")) {
                duke.doTask(Integer.parseInt(s.split(" ")[1]));
            } else if (command.equals("todo")) {
                duke.addToDo(s.split(" ",2)[1]);
            } else if (command.equals("deadline")) {
                String[] params = s.split(" ", 2)[1].split("/by ");
                duke.addDeadline(params[0], params[1]);
            } else if (command.equals("event")) {
                String[] params = s.split(" ", 2)[1].split("/at ");
                duke.addEvent(params[0], params[1]);
            } else {
                duke.store(s);
            }

        }

        //send exit message
        duke.bye();

    }
}
