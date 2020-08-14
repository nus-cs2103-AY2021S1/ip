import java.util.Scanner;

public class Duke {
    private final static String EXIT_COMMAND = "bye";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(EXIT_COMMAND)){
            duke.echo(input);
            input = scanner.nextLine();
        }
        duke.bye();
    }

    private void hello() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("------------------------------------------------------------\n");
    }

    private void echo(String word) {
        System.out.println("------------------------------------------------------------");
        System.out.println(word);
        System.out.println("------------------------------------------------------------\n");
    }

    private void bye() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------\n");
    }
}

