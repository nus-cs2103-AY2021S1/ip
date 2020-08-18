import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Rawr! I'm Dino ><\n" + "What can I do for you?"
                + "\n____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;

        while (!isBye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Rawr. Hope to see you again soon! ><"
                        + "\n____________________________________________________________");
                isBye = true;
            } else {
                System.out.println(input
                        + "\n____________________________________________________________");
            }
        }
        scanner.close();
    }
}
