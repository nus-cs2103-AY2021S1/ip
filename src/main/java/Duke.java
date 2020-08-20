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

    public void printList() {
        for (int i = 0; i < storage.size(); i++) {
            String printtext = Integer.toString(i+1) + ". " + storage.get(i).toString();
            System.out.println(printtext);
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
            } else {
                duke.store(s);
            }

        }

        //send exit message
        duke.bye();

    }
}
