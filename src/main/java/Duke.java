
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.ToDo;

public class Duke {
    private final static String EXIT_COMMAND = "bye";
    private final static String MARK_DONE_COMMAND = "done";
    private final static String DISPLAY_TASKS_COMMAND = "list";
    private final static String TODO_COMMAND = "todo";
    private final static String DEADLINE_COMMAND = "deadline";
    private final static String EVENT_COMMAND = "event";
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
                String command = inputWords[0];
                String argument = inputWords[1];
                switch (command) {
                    case MARK_DONE_COMMAND:
                        this.markDone(Integer.parseInt(argument));
                        break;
                    case TODO_COMMAND:
                        this.addTodo(argument);
                        break;
                    case DEADLINE_COMMAND:
                        this.addDeadline(argument);
                        break;
                    case EVENT_COMMAND:
                        this.addEvent(argument);
                        break;
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

    private void addTodo(String description) {
        Task toAdd = new ToDo(description);
        this.tasks.add(toAdd);
        sayAddedTask(toAdd);
    }

    private void addDeadline(String text) {
        String description = text.split(" /by ", 2)[0];
        String deadline = text.split(" /by ", 2)[1];
        Task toAdd = new Deadline(description, deadline);
        this.tasks.add(toAdd);
        sayAddedTask(toAdd);
    }

    private void addEvent(String text) {
        String description = text.split(" /at ", 2)[0];
        String time = text.split(" /at ", 2)[1];
        Task toAdd = new Event(description, time);
        this.tasks.add(toAdd);
        sayAddedTask(toAdd);
    }

    private void sayAddedTask(Task task) {
        say("Got it. I've added this task:\n" + task + "\nNow you have " + this.tasks.size() + " tasks in the list.");
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

}

