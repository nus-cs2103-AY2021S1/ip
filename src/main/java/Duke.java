import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! I'm Duke your friendly neighbourhood chat bot");
        System.out.println("What can i do for you?");
        Duke duke = new Duke();
        duke.run();

    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String checker;
            if (input.length() > 5) {
                checker = input.substring(0, 4);
            } else {
                checker = "nothing";
            }
            if (input.equals("list")) {
                showList();
            } else if (input.equals("bye")) {
                System.out.println("        Bye have a good day!");
                break;
            } else if(checker.equals("done")) {
                int num = Character.getNumericValue(input.charAt(5));
                done(num);
            } else {
                add(input);
            }
        }
    }

    public void done(int number) {
        if (number <= list.size()) {
            Task current = list.get(number - 1);
            current.markAsDone();
            System.out.println("        I have marked this as done:");
            System.out.println("        [" + current.isDone + "] " + current.description);
        } else {
            System.out.println("        no such task");
        }
    }

    public void add(String input) {
        this.list.add(new Task(input));
        System.out.println("        added: " + input);
    }

    public void showList() {
        System.out.println("        Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list) {
            System.out.println("        " + counter +  ".[" + t.isDone + "]" + t.description);
            counter++;
        }
    }

    public static void echo(String input) {
        System.out.println("        " + input);
    }
}
