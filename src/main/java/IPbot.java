import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IPbot {

    /**
     * Entry point of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        print("Hello from iPbot, what can I do for you?");

        // user input
        final Scanner sc = new Scanner(System.in);

        // task list
        final ArrayList<Task> tasks = new ArrayList<>();

        Stream.generate(sc::nextLine)
            .takeWhile(input -> !"bye".equals(input))
            .forEach(input -> {
                final String output;
                if ("list".equals(input)) {
                    // list tasks
                    output = tasks.isEmpty()
                            ? "No tasks added."
                            : tasks.stream()
                                .map(new Function<Task, String>() {
                                    int id = 1;
                                    public String apply(Task t) {
                                        return String.format("%d. %s", id++, t.toString());
                                    }
                                })
                                .collect(Collectors.joining("\n"));
                } else {
                    // add task
                    tasks.add(new Task(input));
                    output = "added: " + input;
                }
                print(output);
            });

        sc.close();
        print("Goodbye!");
    }

    /**
     * Prints output generated by the bot.
     * @param output string of output to be printed
     */
    public static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________");
        System.out.println();
    }
}
