import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sparkles {

    private static final String dash = "     ____________________________________________________________";
    private static final List<Task> storage = new ArrayList<>();

    private void respond(String str) {
        String lowerCase = str.toLowerCase();

        printDash();

        if (lowerCase.equals("list")) {
            showList();
        } else if (lowerCase.startsWith("done")) {
            markAsDone(str);
        } else if (lowerCase.equals("bye")) {
            exit(str);
        } else {
            addToList(str);
        }

        printDash();
    }

    private void showList() {
        for(int i = 1; i < storage.size() + 1; i++) {
            Task task = storage.get(i - 1);
            task.printTask(i);
        }
    }

    private void markAsDone(String str) {
        int index = Integer.parseInt(String.valueOf(str.charAt(5)));
        Task task = storage.get(index - 1);
        task.markAsDone();
        print("     Nice! I have marked this task as done :-)");
    }

    private void exit(String str) {
        print("     Bye. Hope to see you again!");
        printDash();
        System.exit(0);
    }

    private void addToList(String str) {
        print("     Got it. I've added this task");

        String lowerCase = str.toLowerCase();

        String[] arr = null;
        String desc = null;
        Task task = null;
        if (lowerCase.startsWith("deadline")) {
            arr = str.split(" /by ");
            desc = arr[0];
            String by = arr[1];
            task = new Deadline(desc, by);
        } else if (lowerCase.startsWith("event")) {
            arr = str.split(" /at ");
            desc = arr[0];
            String at = arr[1];
            task = new Event(desc, at);
        } else if ((lowerCase.startsWith("todo"))) {
            desc = str.substring(5).trim();
            task = new Todo(desc);
        } else {
            task = new Task(str);
        }
        task.printTask();
        storage.add(task);
        printListSize();
    }

    private void printListSize() {
        String output = "     Now you have " + storage.size() + " task(s) in your list.";
        print(output);
    }

    private void run(String str) {
        printResult(str);
    }

    private void printResult(String str) {
        respond(str);
    }

    private void print(String str) {
        System.out.println(str);
    }

    private void printDash() {
        print(dash);
    }

    private static void welcome() {
        System.out.println(dash);
        System.out.println("     *Hello, I am Sparkles*\n\n     How can I help you?");
        System.out.println(dash);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sparkles sparkles = new Sparkles();
        welcome();
        while (sc.hasNext()) {
            sparkles.run(sc.nextLine());
        }
    }
}
