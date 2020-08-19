import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<String> list = new ArrayList<>();
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
            if (input.equals("list")) {
                showList();
            } else if (input.equals("bye")) {
                System.out.println("        Bye have a good day!");
                break;
            } else {
                add(input);
            }
        }
    }

    public void add(String input) {
        this.list.add(input);
        System.out.println("        added: " + input);
    }

    public void showList() {
        int counter = 1;
        for (String s : list) {
            System.out.println("        " + counter + ". " + s);
            counter++;
        }
    }

    public static void echo(String input) {
        System.out.println("        " + input);
    }
}
