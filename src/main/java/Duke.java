import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // this field is used when Duke requires a horizontal line
    public static final String LINE = "    ____________________________________________________________";

    // this function greets the user when Duke is started
    public static void greeting(){
        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke and I was designed by Xuan Ming!\n");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }

    // this function says bye to the user when Duke recieves the input "bye"
    public static void goodbye() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    // calling this function will cause Duke to echo what the user inputs to Duke
    public static void echo(String s) {
        System.out.println(LINE);
        System.out.println("     " + s);
        System.out.println(LINE);
    }

    // this function takes in the input from the user and adds it to the list of tasks Duke is tracking
    public static void addTask(String s, ArrayList<String> tasks) {
        tasks.add(s);
        System.out.println(LINE);
        System.out.println("     " + "added: " + s);
        System.out.println(LINE);
    }

    // this function lists the list of tasks Duke is tracking
    public static void list(ArrayList<String> tasks) {
        int counter = 1;
        System.out.println(LINE);
        for (String task : tasks) {
            System.out.println("     " + counter + ". " + task);
            counter++;
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();

        // this field keeps track of the tasks given to Duke
        ArrayList<String> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // if the user inputs a "bye", we simply break out of the Duke program
            if (sc.hasNext("bye")) {
                goodbye();
                break;
            }

            // if the user inputs a "list", Duke will iterate through the added text and print them in the order
            // they were given to Duke.
            if (sc.hasNext("list")) {
                list(tasks);
                sc.nextLine();
            }

            else {
                String input = sc.nextLine();
                addTask(input, tasks);
            }
        }
    }
}
