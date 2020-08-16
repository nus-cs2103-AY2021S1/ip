import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello I'm Vector\n" + "How can I help you today?");
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(command);
            }
        }
    }
}
