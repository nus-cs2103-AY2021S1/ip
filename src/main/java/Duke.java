import java.util.Scanner;

public class Duke {

    static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    static void echo(String command) {
        System.out.println(command);
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            if (command == "bye") {
                exit();
                break;
            } else {
                echo(command);
            }
        }
        sc.close();
    }
}
