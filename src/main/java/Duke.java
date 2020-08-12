import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void greeting() {
        String logo = "\n███▄ ▄███▓  ██▓   ▄████▄   ██░  ██ "
                    + "\n▓██▒▀█▀ ██▒ ▓██▒ ▒██▀ ▀██ ▓██░  ██▒"
                    + "\n▓██    ▓██░ ▒██▒ ▒▓█    ▄ ▒██▀▀▀██░"
                    + "\n▒██    ▒██  ░██░▒ ▓▓▄ ▄██▒░▓█  ░██ "
                    + "\n▒██▒   ░██▒ ░██░▒  ▓███▀ ░░▓█▒ ░██▓"
                    + "\n░ ▒░   ░  ░░ ▒  ░  ░▒ ▒  ░ ▒ ░ ░▒░▒"
                    + "\n░  ░      ░  ▒ ░   ░  ▒    ▒ ░ ▒░ ░"
                    + "\n░      ░     ▒ ░░          ░   ░░ ░"
                    + "\n       ░     ░  ░ ░        ░   ░  ░";

        String greet = "\n____________________________________________________________"
                     + "\nHi, I'm Mich!"
                     + "\nHow can I help you today?"
                     + "\n____________________________________________________________";

        System.out.println(logo + greet);
    }

    public static void echo(String str) {
        String echo = "   ____________________________________________________________"
                    + "\n   added: " + str
                    + "\n   ____________________________________________________________";
        System.out.println(echo);
    }

    public static void bye() {
        String bye = "   ____________________________________________________________"
                   + "\n   Bye! Hope to see you again soon."
                   + "\n   ____________________________________________________________";
        System.out.println(bye);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();

        greeting();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                String lst = "   ____________________________________________________________";
                for (int i = 0; i < toDoList.size(); i++) {
                    int index = i + 1;
                    lst += "\n   " + index + ". " + toDoList.get(i);
                }
                lst += "\n   ____________________________________________________________";
                System.out.println(lst);
            } else {
                toDoList.add(input);
                echo(input);
            }
        }

    }
}