import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n"
                + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Input> inputs = new ArrayList<>();
        while (true) {
            String nextLine = sc.nextLine();
            if (nextLine.startsWith("done")) {
                int numTaskDone = Integer.valueOf(nextLine.substring(5));
                System.out.println("Nice! I've marked this task as done:");
                if (numTaskDone < inputs.size()) {
                    inputs.get(numTaskDone - 1).taskDone();
                    System.out.println("[/] " + inputs.get(numTaskDone -1).content);
                } else {}
            } else {
                Input input = new Input(nextLine);
                if (!input.content.equals("bye") && !input.content.equals("list")) {
                    inputs.add(input);
                    System.out.println("added: " + input.content);
                } else if (input.content.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int len = inputs.size();
                    for (int i = 1; i <= len; i++) {
                        if (inputs.get(i-1).done) {
                            System.out.println(i + ". [/] " + inputs.get(i - 1).content);
                        } else {
                            System.out.println(i + ". [x] " + inputs.get(i - 1).content);
                        }
                    }
                } else {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
            }
        }
    }


    public static class Input {
        boolean done;
        String content;

        Input(String content) {
            this.content = content;
            boolean done = false;
        }
        Input(boolean done, String content) {
            this.done = done;
            this.content = content;
        }

        void taskDone() {
            this.done = true;
        }
    }
}