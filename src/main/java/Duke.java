import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.activate();
    }

    private void activate() {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.print(greeting + "\nMe: ");
        String input = "";

        while (!input.equals("bye") && sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                quit();
            } else {
                echo(input);
            }
        }
    }

    private void quit() {
        System.out.println("Duke: Adios!\n");
    }

    private void echo(String input) {
        System.out.println("Duke: " + input + "\n");
        System.out.print("Me: ");
    }

}
