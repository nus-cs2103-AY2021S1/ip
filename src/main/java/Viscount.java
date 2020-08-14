import java.util.Scanner;

public class Viscount {
    private static final String VISCOUNT_LOGO = "        _  _____  _____                  _    \n" +
            "       (_)/ ____|/ ____|                | |   \n" +
            " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
            " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
            "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
            "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";

    private static void printLogo() {
        System.out.println(Viscount.VISCOUNT_LOGO);
    }

    private static void speak(String message) {
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(Viscount.HORIZONTAL_LINE);
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Viscount.speak(input);
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Viscount.printLogo();

        Viscount.speak("Good day to you sir/ma'am! I'm Viscount!\nWhat can I do for you on this blessed day?");

        Viscount.echo();

        Viscount.speak("Farewell, and hope to see you again!");
    }
}
