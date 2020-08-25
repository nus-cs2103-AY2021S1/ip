import java.io.BufferedWriter;
import java.io.File;
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

        StringBuilder s = new StringBuilder(String.format("You currently have %d task(s)", tasks.size()));
        for (int i = 0; i < tasks.size(); i++) {
            s.append("\n    " + String.format("%d.%s", i+1, tasks.get(i)));
        }
        printNice(s.toString());
    }

    private static boolean isBye(String s){
        return s.toLowerCase().equals("bye");
    }

    private static boolean isList(String s){
        return s.toLowerCase().equals("list");
    }

    private static boolean isDone(String s) {
        return s.length() >= 4 && s.substring(0, 4).toLowerCase().equals("done");
    }

    private static boolean isDelete(String s){
        return s.length() >= 6 && s.substring(0, 6).toLowerCase().equals("delete");
    }

    private static void done(ArrayList<Task> tasks, int i) {
        tasks.get(i).markDone();
        printNice("Nice! I've marked this task as done:\n      " + tasks.get(i).toString());
    }

    private static void add(ArrayList<Task> tasks, String s) {
        try {
            String[] processed;
            Task toAdd;
            switch (s.split(" ")[0]) {
            case "todo":
                toAdd = new ToDoTask(s.substring(5));
                break;
            case "event":
                processed = s.substring(6).split(" /at ");
                toAdd = new EventTask(processed[0], processed[1]);
                break;
            case "deadline":
                processed = s.substring(9).split(" /by ");
                toAdd = new DeadlineTask(processed[0], processed[1]);
                break;
            default:
                throw new IllegalArgumentException();
            }
            tasks.add(toAdd);
            writeFile(tasks);
            printNice("Got it. I've added this task:\n" +
                    "        " + toAdd.toString() + "\n" +
                    "    Now you have " + tasks.size() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            printNice("Please reformat the add task command properly.");
        } catch (IllegalArgumentException e) {
            printNice("Unrecognizable command.");
        }
    }

    private static void delete(ArrayList<Task> tasks, int i) {
        printNice("Noted. I've removed this task:\n" +
                "      " + tasks.get(i) +         "\n" +
                "    Now you have " + (tasks.size() - 1) + " tasks in the list."
        );
        tasks.remove(i);

    }

    private static String genString(Random rng, int length) {
        return rng.ints(97, 123)
                .limit(length)
                .mapToObj(x -> (char) x)
                .reduce("", (a, b) -> a + b, (a, b) -> a + b);
    }

    // Run this once to generate input for testing, input.txt should be located at path
    public static void generateInput(String path) {
        Random rng = new Random(500); //constant seed for regression test, or use Time as seed for new set of test cases
        String[] action = {"list", "done", "delete", "todo", "deadline", "event"};
        int cnt = 0;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < 30; i++) {
                switch (action[rng.nextInt(action.length)]) {
                    case "list":
                        bufferedWriter.write("list");
                        break;
                    case "done":
                        bufferedWriter.write("done " + (1 + rng.nextInt(cnt + cnt/10)));
                        break;
                    case "delete":
                        bufferedWriter.write("delete " + (1 + rng.nextInt(cnt + cnt/10)));
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
        loadFile(tasks);
        while (true) {
            input = scanner.nextLine().trim();
            if (isBye(input)) {
                bye();
                return;
            } else if (isList(input)) {
                listOut(tasks);
            } else if (isDone(input)) {
                try {
                    done(tasks, Integer.parseInt(input.split(" ")[1]) - 1);
                } catch (IndexOutOfBoundsException e) {
                    printNice("No such task.");
                }
            } else if (isDelete(input)) {
                try {
                    delete(tasks, Integer.parseInt(input.split(" ")[1]) - 1);
                } catch (IndexOutOfBoundsException e) {
                    printNice("No such task.");
                }
            } else {
                add(tasks, input);
            }
            writeFile(tasks);
        }
    }

    private static Task processTask(String s) {
        String[] arg = s.split(" @@ ");
        Task task = null;
        switch (arg[0]) {
        case "T":
            task = new ToDoTask(arg[2]);
            break;
        case "D":
            task = new DeadlineTask(arg[2], arg[3]);
            break;
        case "E":
            task = new EventTask(arg[2], arg[3]);
            break;
        }
        if (arg[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    private static void loadFile(ArrayList<Task> tasks) {
        try {
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(processTask(scanner.nextLine()));
            }
        } catch (Exception e) {
            printNice("Some error occurred, list may not be complete");
            e.printStackTrace();
        }
        listOut(tasks);
    }

    private static void writeFile(ArrayList<Task> tasks) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/duke.txt"));
            for (Task task: tasks) {
                bufferedWriter.write(task.toSaveString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            printNice("Some error occurred, list may not be complete");
        }
    }

}
