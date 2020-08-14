import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> todos;

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
            switch(input) {
                case("bye"):
                    System.out.println(">> Bye! Hope I helped!");
                    running = false;
                    break;
                case("list"):
                    int i = 1;
                    for (String item : todos) {
                        System.out.println(">> " + i++ + ". " + item);
                    }
                    break;
                default:
                    todos.add(input);
                    System.out.println(">> added: " + input);
                    break;
            }
        }
    }
}
