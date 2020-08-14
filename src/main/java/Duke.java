import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        ArrayList<String> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty.");
                } else {
                    StringBuilder listOutput = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        int num = i + 1;
                        listOutput.append(num + ". " + list.get(i) + "\n");
                    }
                    System.out.println(listOutput);
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            input = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
