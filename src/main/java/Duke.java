import java.util.Scanner;

public class Duke {
    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String command = sc.next();
            if(command.equals("bye")) {
                duke.exit();
                break;
            } else {
                System.out.println(command);
            }
        }
        sc.close();
    }
}
