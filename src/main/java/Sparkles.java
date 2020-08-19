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
            exit();
        } else if (lowerCase.startsWith("delete")) {
            delete(str);
        } else {
            addToList(str);
        }

        printDash();
    }


    private void showList() {
        if (storage.size() == 0) {
            print("     List is Empty.");
        } else {
            for (int i = 1; i < storage.size() + 1; i++) {
                Task task = storage.get(i - 1);
                task.printTask(i);
            }
        }
    }

    private void markAsDone(String str) {
        int index = -1;

        try {
            index = Integer.parseInt(String.valueOf(str.charAt(5)));
            Task task = storage.get(index - 1);
            task.markAsDone();
            print("     Nice! I have marked this task as done :-)");
        } catch (Exception ex) {
            print("     OOPS!! Task in the list to be marked as done is not specified!");
        }
    }

    private void exit() {
        print("     Bye. Hope to see you again!");
        printDash();
        System.exit(0);
    }

    private void delete(String str) {
        int index = -1;

        if (storage.size() == 0) {
            print("     OOPS!! List is empty");
        } else {
            try {
                index = Integer.parseInt(String.valueOf(str.charAt(7)));
                Task task = storage.get(index - 1);
                print("     Noted, I have removed this task:");
                task.printTask();
                storage.remove(index - 1);
                printListSize();
            } catch (Exception ex) {
                print("     OOPS!! Task in the list to be removed is not specified!");
            }
        }
    }

    private void addToList(String str) {
        String lowerCase = str.toLowerCase();

        String[] arr;
        String desc = null;
        Task task;
        if (lowerCase.startsWith("deadline")) {
            arr = str.split(" /by ");
            String by = null;

            try {
                desc = arr[0];
                by = arr[1];
                task = new Deadline(desc, by);
                print("     Got it. I've added this task");
                task.printTask();
                storage.add(task);
                printListSize();
            } catch (Exception ex) {
                print("     OOPS!! The description and deadline of a Deadline cannot be empty!");
            }

        } else if (lowerCase.startsWith("event")) {
            arr = str.split(" /at ");
            String at = null;

            try {
                desc = arr[0];
                at = arr[1];
                task = new Event(desc, at);
                print("     Got it. I've added this task");
                task.printTask();
                storage.add(task);
                printListSize();
            } catch (Exception ex) {
                print("     OOPS!! The description and time of an Event cannot be empty!");
            }

        } else if ((lowerCase.startsWith("todo"))) {
            try {
                desc = str.substring(5).trim();
                task = new Todo(desc);
                print("     Got it. I've added this task");
                task.printTask();
                storage.add(task);
                printListSize();
            } catch (Exception ex) {
                print("     OOPS!! The description of a todo cannot be empty!");
            }
        } else {
            print("     Task need to be more specific!");
        }
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
