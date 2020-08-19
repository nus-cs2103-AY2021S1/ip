import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
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
        Task toAdd = new Task("unrecognized");
        String[] processed;
        switch (s.split(" ")[0]) {
            case "todo":
                toAdd = new ToDoTask(s.substring(5));
                break;
            case "event":
                processed = s.substring(6).split(" /at ");
                toAdd = new EventTask(processed[0],processed[1]);
                break;
            case "deadline":
                processed = s.substring(9).split(" /by ");
                toAdd = new DeadlineTask(processed[0],processed[1]);
                break;

        }
        tasks.add(toAdd);

        printNice("Got it. I've added this task:\n" +
                "        " + toAdd.toString() + "\n" +
                "   Now you have " + tasks.size() + " task(s) in the list.");
    }

    private static String genString(Random rng, int length){
        return rng.ints(97, 123)
                .limit(length)
                .mapToObj(x -> (char) x)
                .reduce("", (a, b) -> a + b, (a, b) -> a + b);
    }

    // Run this once to generate input for testing, input.txt should be located at path
    public static void generateInput(String path){
        Random rng = new Random(System.currentTimeMillis());
        String[] action = {"list", "done", "todo", "deadline", "event"};
        int cnt = 0;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < 20; i++) {
                switch (action[rng.nextInt(action.length)]) {
                    case "list":
                        bufferedWriter.write("list");
                        break;
                    case "done":
                        if (cnt > 0) bufferedWriter.write("done " + rng.nextInt(cnt));
                        break;
                    case "todo":
                        cnt++;
                        bufferedWriter.write("todo " + genString(rng, 15));
                        break;
                    case "deadline":
                        cnt++;
                        bufferedWriter.write("deadline " + genString(rng, 15) + " /by " + genString(rng, 10));
                        break;
                    case "event":
                        cnt++;
                        bufferedWriter.write("event " + genString(rng, 15) + " /at " + genString(rng, 10));
                        break;
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.write("bye");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        while(true) {
            input = scanner.nextLine().trim();
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
