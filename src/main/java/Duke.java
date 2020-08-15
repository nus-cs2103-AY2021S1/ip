import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> list = new ArrayList<>();

    public static void Greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void Exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void Echo() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                ListOut();
            } else {
                Add(input);
                System.out.println();
            }
        }
    }

    public static void Add(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + string);
        System.out.println("    ____________________________________________________________");

        list.add(string);
    }

    public static void ListOut() {
        System.out.println("    ____________________________________________________________");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s \n", i + 1, list.get(i));
        }

        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {

        Greet();

        Echo();

        Exit();
    }
}
