import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(">Hello");
        looper();
    }

    private static void reply(String text) {
        System.out.printf("> %s\n", text);
    }

    private static void looper() {
        Boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            String input = sc.nextLine();
            if (input.matches("bye")) {
                exit = true;
            } else {
                reply(input);
            }

        }
        reply("Goodbye.");
    }



}
