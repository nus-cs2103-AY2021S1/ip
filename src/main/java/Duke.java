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
                    + "\n Hi Michaelia, I am your productivity tracker bot."
                    + "\n Start adding your tasks to the to-do list right now!"
                    + "\n____________________________________________________________";
        System.out.println(logo + greet);
    }

    public static void echo(String str) {
        String echo = "   ____________________________________________________________"
                    + "\n    added: " + str
                    + "\n   ____________________________________________________________";
        System.out.println(echo);
    }

    public static void bye() {
        String bye = "   ____________________________________________________________"
                + "\n    Bye! Hope to see you again soon."
                + "\n   ____________________________________________________________";
        System.out.println(bye);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();

        greeting();
        String input = sc.nextLine();
        while (input.isEmpty()) {
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                String lst = "   ____________________________________________________________"
                        + "\n    Here are the tasks in your list:";
                for (int i = 0; i < toDoList.size(); i++) {
                    int index = i + 1;
                    lst += "\n    " + index + ". " + tasks.get(i);
                }
                lst += "\n   ____________________________________________________________";
                System.out.println(lst);
            } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(input.substring(5));
                tasks.get(index - 1).markAsDone();
                String done = "   ____________________________________________________________"
                        + "\n    Nice! I've marked this task as done:\n      "
                        + tasks.get(index - 1)
                        + "\n   ____________________________________________________________";
                System.out.println(done);
            } else {
                toDoList.add(input);
                tasks.add(new Task(input));
                echo(input);
            }
            input = sc.nextLine();
        }
    }
}
