import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static String LINE = "____________________________________________________________";

    private final static String BYE = "bye";
    private final static String LIST = "list";

    public static void main(String[] args) {
        looper();
    }

    private static void reply(String text) {
        System.out.printf("%s\n%s\n%s\n", LINE, text.trim(), LINE);
    }

    private static String[] readCommand(String text) {
        String[] split = text.split(" ", 2);
        return split;
    }

    private static void looper() {
        reply("Hello");
        ArrayList<String> list = new ArrayList<>();
        Boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            String input = sc.nextLine().trim();
            switch(readCommand(input)[0]) {
                case BYE:
                    exit = true;
                    break;
                case LIST:
                    String result = "";
                    for (int i = 0; i < list.size(); i++) {
                        result += String.format("%d. %s\n", i+1, list.get(i));
                    }
                    reply(result);
                    break;
                default:
                    list.add(input);
                    reply(input);
                    break;
            }

        }
        reply("Goodbye.");
    }



}
