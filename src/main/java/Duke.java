import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    private static ResourceBundle strings;
    private static Scanner scanner;
    private static ArrayList<String> list;

    private static void initializeDuke() {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
    }

    private static void printWithDecoration(Object object) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        System.out.println("\t" + object);
        System.out.println(lineDecoration);
    }

    public static void main(String[] args) {

        initializeDuke();

        printWithDecoration(strings.getString("output.greeting"));
        String input;

        while (scanner.hasNext()) {
            input = scanner.nextLine();

            if (input.equals(strings.getString("command.list"))) {
                String output = IntStream.range(0, list.size())
                        .mapToObj(index -> (index + 1) + ". " + list.get(index) + "\n\t")
                        .reduce("", (x, y) -> x + y)
                        .strip();

                printWithDecoration(output);
            } else if (input.equals(strings.getString("command.bye"))) {
                break;
            } else {
                list.add(input);
                printWithDecoration(strings.getString("output.added") + input);
            }

        }

        printWithDecoration(strings.getString("output.bye"));
    }

}
