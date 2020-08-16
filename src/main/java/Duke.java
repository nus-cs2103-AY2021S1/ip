import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void initialMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void exitMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void printMessage(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.print(msg);
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> todoList = new ArrayList<>();

        initialMessage();
        while(true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                exitMessage();
                break;
            } else {
                todoList.add(echo);
                String message = "";
                for (int i = 0; i < todoList.size(); i++) {
                    message = message + "     " + (i + 1) + ". " + todoList.get(i) + "\n";
                }
                printMessage(message);
            }
        }

    }
}
