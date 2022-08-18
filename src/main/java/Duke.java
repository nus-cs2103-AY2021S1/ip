import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static String line = "___________________________________";
    private static ArrayList<String> words = new ArrayList<>();
    private static ArrayList<Boolean> finished = new ArrayList<>();

    public static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void addWord(String word) {
        System.out.println(line);
        words.add(word);
        finished.add(false);
        System.out.println("added: " + word);
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

    public static void addlist() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < words.size(); i++) {
            int counter = i + 1;
            String checkBox = finished.get(i) ? "[/]" : "[x]";
            System.out.println(counter + ". " + checkBox + " " + words.get(i));
        }
        System.out.println(line);
    }

    public static void handleDone(String word) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        String[] doneTasks = word.split(" ");
        for (int i = 1; i < doneTasks.length; i++) {
            int taskNo = Integer.parseInt(doneTasks[i]);
            if (taskNo - 1 < words.size()) {
                finished.set(taskNo - 1, true);
                String checkBox = "[/]";
                System.out.println(checkBox + " " + words.get(taskNo - 1));
            }
        }
        System.out.println(line);
    }

    public static void handle(String word) {
        if (word.equals("bye") || word.equals("Bye")) {
            bye();
        } else if (word.equals("list") || word.equals("List")) {
            addlist();
        } else if (word.contains("done") || word.contains("Done")) {
            handleDone(word);
        } else {
            addWord(word);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String nextWord = sc.nextLine();
            if (!nextWord.equals("")) {
                handle((nextWord));
            }
        }
    }
}
