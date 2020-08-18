import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void printNice(String s) {
        System.out.println("________________________________________");
        System.out.println("    " + s);
        System.out.println("________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm duckmoon99's Duke\n    What can I do for you?";
        printNice(greeting);
    }

    private static void bye() {
        String exit = "Bye. Hope to see you again soon!";
        printNice(exit);
    }

    private static void listOut(ArrayList<String> tasks){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append((i > 0 ? "\n    " : "") + String.format("%d. %s", i+1, tasks.get(i)));
        }
        printNice(s.toString());
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<String> tasks = new ArrayList<>();
        while(true){
            input = scanner.nextLine();
            switch (input) {
                case "bye":
                    bye();
                    return;
                case "list":
                    listOut(tasks);
                    break;
                default:
                    printNice("added: " + input);
                    tasks.add(input);
                    break;
            }
        }
    }

}
