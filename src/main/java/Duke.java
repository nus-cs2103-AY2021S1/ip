import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void formatResponse(ArrayList<String> response) {
        System.out.println(INDENT + LINE);
        for (String resp: response) {
            System.out.println(INDENT + " " + resp);
        }
        System.out.println(INDENT + LINE);
        System.out.println();
    }

    private static void formatResponse(String ...response) {
        ArrayList<String> lst = new ArrayList<>();
        for (String resp: response) {
            lst.add(resp);
        }
        formatResponse(lst);
    }

    private static void formatList(ArrayList<Task> response) {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("Here are the tasks in your list:");
        for (int i = 0; i < response.size(); i++) {
            lst.add((i + 1) + ". " + response.get(i).toString());
        }
        formatResponse(lst);
    }

    private static void formatDoneTask(Task task) {
        formatResponse("Nice! I've marked this task as done:", INDENT + task.toString());
    }

    private static void addTask(String display) {
        if (display.length() >= 4 && display.substring(0, 4).equals("todo")) {
            tasks.add(new ToDo(display.substring(5)));
        } else if (display.length() >= 8 && display.substring(0, 8).equals("deadline")) {
            int idx = display.indexOf(" /by ");
            tasks.add(new Deadline(display.substring(9, idx), display.substring(idx + 5)));
        } else if (display.length() >= 5 && display.substring(0, 5).equals("event")) {
            int idx = display.indexOf(" /at ");
            tasks.add(new Event(display.substring(6, idx), display.substring(idx + 5)));
        } else {
            System.out.println("Could not identify task type.");
            return;
        }

        Task task = tasks.get(tasks.size() - 1);
        formatResponse("Got it. I've added this task: ", INDENT + task.toString(), "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        formatResponse("Hello! I'm Duke", "What can I do for you?");
        String display = "";
        while (!display.equals("bye")) {
            display = sc.nextLine();
            if (display.equals("list")) {
                formatList(tasks);
            } else if (display.length() >= 4 && display.substring(0, 4).equals("done")) {
                int idx = Integer.parseInt(String.valueOf(display.charAt(5))) - 1;
                tasks.get(idx).markAsDone();
                formatDoneTask(tasks.get(idx));
            } else if (!display.equals("bye")) {
                addTask(display);
            }
        }
       formatResponse("Bye. Hope to see you again soon!");
       sc.close();
    }
}
