import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static String EXIT_COMMAND = "bye";
    private final static String MARK_DONE_COMMAND = "done";
    private final static String DISPLAY_TASKS_COMMAND = "list";
    private List<Task> tasks;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        duke.handle();
        duke.bye();
    }

    private Duke() {
        tasks = new ArrayList<>();
    }

    private void handle() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(EXIT_COMMAND)){
            if (input.equals(DISPLAY_TASKS_COMMAND)) {
                this.displayTasks();
            } else {
                String[] inputWords = input.split(" ", 2);
                switch (inputWords[0]) {
                    case MARK_DONE_COMMAND:
                        this.markDone(Integer.parseInt(inputWords[1]));
                        break;
                    default:
                        this.addTask(input);
                }
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private void say(String text) {
        System.out.println("------------------------------------------------------------");
        System.out.println(text);
        System.out.println("------------------------------------------------------------\n");
    }

    private void hello() {
        say("Hello! I'm Duke\nWhat can I do for you?");
    }

    private void addTask(String description) {
        Task toAdd = new Task(description);
        this.tasks.add(toAdd);
        say("added: " + description);
    }

    private void displayTasks() {
        String text = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            boolean isLastTask = i == this.tasks.size() - 1;
            text += ((i + 1) + ". " + this.tasks.get(i).toString() + (isLastTask ? "" : "\n"));
        }
        say(text);
    }

    private void bye() {
        say("Bye. Hope to see you again soon!");
    }

    private void markDone(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        Task toDone = this.tasks.get(zeroBasedIndex);
        toDone.markAsDone();
        String text = "Nice! I've marked this task as done: \n" + toDone;
        say(text);
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            String tick = "\u2713";
            String X = "\u2718";
            return (isDone ? tick : X); //return tick or X symbols
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.getDescription();
        }

        public void markAsDone() {
            this.isDone = true;
        }
    }

}

