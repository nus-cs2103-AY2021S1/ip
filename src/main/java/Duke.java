import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greet();

        ArrayList<String> todo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

       while (!input.equals("bye")) {
           add(input);
           todo.add(input);
           input = sc.nextLine();
           if (input.equals("list")) {
                printList(todo);
                input = sc.nextLine();
           }
       }
       Bye();

    }
    public static void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(ArrayList<String> todo) {
        int taskNum = 0;
        System.out.println("    ____________________________________________________________");
        for (String task : todo) {
            taskNum++;
            System.out.println("     " + taskNum + ". " + task);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void add(String todo) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + todo);
        System.out.println("    ____________________________________________________________");
    }

    public static void Bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
