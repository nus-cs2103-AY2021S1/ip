import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Viscount {
    private static final String VISCOUNT_LOGO = "        _  _____  _____                  _    \n" +
            "       (_)/ ____|/ ____|                | |   \n" +
            " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
            " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
            "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
            "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";

    private static List<String> list = new ArrayList<>();

    private static void printLogo() {
        System.out.println(Viscount.VISCOUNT_LOGO);
    }

    private static void speak(String message) {
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println();
    }

    private static String listToString() {
        String result = "";

        for (int i = 0; i < list.size(); i++) {
            result += (i == list.size() - 1)
                ? String.format("%d. %s", i + 1, list.get(i))
                : String.format("%d. %s\n", i + 1, list.get(i));
        }

        return result;
    }

    private static void addToList(String text) {
        list.add(text);
    }

    private static void chat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Viscount.speak(Viscount.listToString());
            } else {
                Viscount.addToList(input);
                Viscount.speak(String.format("added: %s", input));
            }

            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Viscount.printLogo();

        Viscount.speak("Good day to you sir/ma'am! I'm Viscount!\nWhat can I do for you on this blessed day?");

        Viscount.chat();

        Viscount.speak("Farewell, and hope to see you again!");
    }
}
