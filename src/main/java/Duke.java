import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        boolean isDone = false;

        System.out.println("Hello! I'm Eggy\n" + "How may I help you?");

        while (!isDone) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                isDone = true;
                System.out.println("Bye. See you soon!");
            } else {
                System.out.println(command);
            }
        }
    }
}