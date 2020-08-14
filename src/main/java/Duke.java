import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        echo(sc);
    }

    private static void greet() {
        System.out.println(">> Beep Boop. I am Aq-bot.\n>> How can I help?");
    }

    private static void echo(Scanner sc) {
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(">> Bye! Hope I helped!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
