import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String startMsg =
            DIVIDER + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(startMsg);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals(EXIT_CMD)) {
            handleInput(input);
            input = sc.nextLine();
        }

        String exitMsg =
            DIVIDER + "\n" +
            "Bye. Hope to see you again soon!\n" +
            DIVIDER;
        System.out.println(exitMsg);
    }

    private static String createEchoMsg(String str) {
        String text = "added: " + str + "\n";
        return DIVIDER + "\n" + text + DIVIDER;
    }

    private static void handleInput(String in) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
            case (LIST_CMD):
                handleList();
                break;
            case (DONE_CMD):
                handleDone(Integer.parseInt(input[1]));
                break;
            default:
                handleEcho(in);
        }
    }

    private static void handleList() {
        int len = taskList.size();
        System.out.println(DIVIDER);
        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            String output = i + "." + task.toString();
            System.out.println(output);
        }
        System.out.println(DIVIDER);
    }

    private static void handleDone(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println(
            DIVIDER + "\n" +
            "Nice! I've marked this task as done\n" +
            "  " + task.toString() + "\n" +
            DIVIDER
        );
    }

    private static void handleEcho(String str) {
        taskList.add(new Task(str));
        System.out.println(createEchoMsg(str));
    }

    private static class Task {
        protected String description;
        protected boolean isDone = false;

        public Task(String description) {
            this.description = description;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String getStatusIcon() {
            return isDone ? "\u2713" : "\u2718";
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "]" + " " + description;
        }
    }
}
