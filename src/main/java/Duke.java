import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> storage = new ArrayList<>();
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
                    int number = i + 1;
                    System.out.println(number + ". " + storage.get(i));
                }
            } else if (toEcho.length() > 4 && toEcho.substring(0, 5).equals("done ")) {
                int index = Integer.parseInt(toEcho.substring(5)) - 1;
                if (storage.size() <= index) {
                    System.out.println("Sorry Boss! There is no task number " + (index + 1));
                    System.out.println("--------------------------------------");
                    toEcho = sc.nextLine();
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                storage.get(index).markAsDone();
                System.out.println("    " + storage.get(index));
            } else {
                System.out.println("added: " + toEcho);
                storage.add(new Task(toEcho));
            }
            System.out.println("--------------------------------------");
            toEcho = sc.nextLine();
        }
        System.out.println("--------------------------------------\n" +
            "Bye Boss! Hope to see you again!" + "\n"
            + "--------------------------------------");
    }
}
