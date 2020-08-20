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
        storage.add(newTask);
        System.out.println("added: " + s);
    }

    public Task getTask(int i) {
        return storage.get(i-1);
    }

    public void doTask(int i) {
        Task temp = this.getTask(i);
        temp.doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + temp.toString());
    }

    public void printList() {
        for (int i = 0; i < storage.size(); i++) {
            String printText = Integer.toString(i+1) + ". " + storage.get(i).toString();
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
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                duke.printList();
            } else if (s.split(" ")[0].equals("done")) {
                duke.doTask(Integer.parseInt(s.split(" ")[1]));
            } else {
                duke.store(s);
            }

        }

        //send exit message
        duke.bye();

    }
}
