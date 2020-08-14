import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> todos;

    public static void main(String[] args) {
        todos = new ArrayList<>();
        greet();
        Scanner sc = new Scanner(System.in);
        converse(sc);
    }

    private static void greet() {
        System.out.println(">> Beep Boop. I am Aq-bot.\n>> How can I help?");
    }

    private static void converse(Scanner sc) {
        boolean running = true;
        while(running) {
            String input = sc.nextLine();
            String[] chunks = input.split(" ", 2);
            String action = chunks[0];
            switch(action) {
                case("bye"):
                    System.out.println(">> Bye! Hope I helped!");
                    running = false;
                    break;
                case("list"):
                    int i = 1;
                    for (Task task : todos) {
                        System.out.println(">> " + i++ + ". " + task);
                    }
                    break;
                case("done"):
                    int index = Integer.parseInt(chunks[1]) - 1;
                    todos.get(index).complete();
                    break;
                default:
                    todos.add(new Task(input));
                    System.out.println(">> added: " + input);
                    break;
            }
        }
    }
}
