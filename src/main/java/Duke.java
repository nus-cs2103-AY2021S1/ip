import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    private static ResourceBundle strings;
    private static Scanner scanner;
    private static ArrayList<Task> tasks;

    private static void initializeDuke() {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
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

        String input, output;

        while (scanner.hasNext()) {
            input = scanner.nextLine();

            if (input.equals(strings.getString("command.list"))) {
                output = IntStream.range(0, tasks.size())
                        .mapToObj(index -> (index + 1) + ". " + tasks.get(index) + "\t")
                        .reduce("", (x, y) -> x + y)
                        .strip();

                printWithDecoration(output);
            } else if (input.split(" ")[0].equals(strings.getString("command.done"))) {
                int toMark = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(toMark).markAsDone();
                output = (strings.getString("output.done") + tasks.get(toMark)).strip();

                printWithDecoration(output);
            } else if (input.equals(strings.getString("command.bye"))) {
                break;
            } else {
                tasks.add(new Task(input));
                printWithDecoration(strings.getString("output.added") + input);
            }

        }

        printWithDecoration(strings.getString("output.bye"));
    }

}
