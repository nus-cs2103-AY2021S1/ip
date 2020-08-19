import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatterbox {

    private static class Task {
        private final String contents;
        private boolean done = false;

        public Task(String contents) {
            this.contents = contents;
        }

        public void markDone() {
            done = true;
        }

        public String toString() {
            return (done ? "[✓]" : "[✗]") + " " + contents;
        }
    }

    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    private static final List<Task> ITEMS = new ArrayList<>();

    private static String format(String s) {
        return SEPARATOR + "\n" + s + "\n" + SEPARATOR;
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                if (ITEMS.size() != 0) {
                    StringBuilder fullList = new StringBuilder("\n");
                    for (int i = 0; i < ITEMS.size(); i++) {
                        fullList.append(i + 1).append(". ").append(ITEMS.get(i)).append("\n");
                    }
                    System.out.println(format(fullList.toString()));
                } else {
                    System.out.println(format("Your list is currently empty."));
                }
            } else if (input.split(" ")[0].equals("done")) {
                int taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNo < 0 || taskNo >= ITEMS.size()) {
                    System.out.println(format("Invalid task number."));
                } else {
                    Task t = ITEMS.get(taskNo);
                    t.markDone();
                    System.out.println(format("Nice! I've marked this task as done: \n" + t));
                }
            } else {
                ITEMS.add(new Task(input));
                System.out.println(format("added: " + input));
            }
            input = s.nextLine();
        }

        System.out.println(format("Goodbye! Hope to see you again soon!"));
    }
}

