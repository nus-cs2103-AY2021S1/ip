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

    private static void listOut(ArrayList<Task> tasks){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append((i > 0 ? "\n    " : "") + String.format("%d.%s", i+1, tasks.get(i)));
        }
        printNice(s.toString());
    }

    private static boolean isBye(String s){
        return s.toLowerCase().equals("bye");
    }

    private static boolean isList(String s){
        return s.toLowerCase().equals("list");
    }

    private static boolean isDone(String s){
        return s.length() >= 4 && s.substring(0, 4).toLowerCase().equals("done");
    }

    private static void done(ArrayList<Task> tasks, int i){
        tasks.get(i).done();
        printNice("Nice! I've marked this task as done:\n      " + tasks.get(i).toString());
    }

    private static void add(ArrayList<Task> tasks, String s){
        tasks.add(new Task(s));
        printNice("added: " + s);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        while(true) {
            input = scanner.nextLine();
            if (isBye(input)) {
                bye();
                return;
            } else if (isList(input)) {
                listOut(tasks);
            } else if (isDone(input)) {
                done(tasks, Integer.parseInt(input.split(" ")[1]) - 1);
            } else {
                add(tasks, input);
            }
        }
    }

}
