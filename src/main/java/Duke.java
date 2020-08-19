import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

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

        String input, output = "", inputMainCommand;

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            inputMainCommand = input.split(" ")[0];

            if (input.equals(strings.getString("command.list"))) {
                
                output = IntStream.range(0, tasks.size())
                        .mapToObj(index -> (index + 1) + ". " + tasks.get(index) + "\t")
                        .reduce("", (x, y) -> x + y)
                        .strip();

            } else if (inputMainCommand.equals(strings.getString("command.done"))) {
                
                int toMark = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(toMark).markAsDone();
                output = (strings.getString("output.done") + tasks.get(toMark)).strip();
                
            } else if (inputMainCommand.equals(strings.getString("command.todo"))) {
                
                Task toAdd = new Todo(input.replace(strings.getString("command.todo"), "").strip());
                tasks.add(toAdd);

                output = String.format(strings.getString("output.added"), toAdd, tasks.size());
                
            } else if (inputMainCommand.equals(strings.getString("command.deadline"))) {
                
                String description = input.split("/by")[0]
                        .replace(strings.getString("command.todo"), "")
                        .strip();

                String by = input.split("/by")[1]
                        .strip();
                
                Task toAdd = new Deadline(description, by);
                tasks.add(toAdd);
                
                output = String.format(strings.getString("output.added"), toAdd, tasks.size());
                
            } else if (inputMainCommand.equals(strings.getString("command.event"))) {

                String description = input.split("/at")[0]
                        .replace(strings.getString("command.deadline"), "")
                        .strip();

                String at = input.split("/at")[1]
                        .strip();

                Task toAdd = new Event(description, at);
                tasks.add(toAdd);

                output = String.format(strings.getString("output.added"), toAdd, tasks.size());

            } else if (input.equals(strings.getString("command.bye"))) {
                break;
            }

            printWithDecoration(output);
        }

        printWithDecoration(strings.getString("output.bye"));
    }

}
