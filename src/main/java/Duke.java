import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> storage = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "How can I help you, Boss?");

        Scanner sc = new Scanner(System.in);
        String toEcho = sc.nextLine();
        while (!toEcho.equals("bye")) {
            System.out.println("--------------------------------------");
            if (toEcho.equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println(i + ". " + storage.get(i));
                }
            } else {
                System.out.println(toEcho);
                storage.add(toEcho);
            }
            System.out.println("--------------------------------------");
            toEcho = sc.nextLine();
        }
        System.out.println("--------------------------------------\n" +
            "Bye Boss! Hope to see you again!" + "\n"
            + "--------------------------------------");
    }
}
