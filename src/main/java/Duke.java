import java.util.Scanner;

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
                    + "\n   " + str
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
        greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("Bye")) {
                bye();
                break;
            } else {
                echo(input);
            }
        }
    }
}