package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    String name;
    boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + (done ? "✓" : "✗") + "] " + name;
    }
}

class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

public class Duke {
    public static void main(String[] args) {
        // startup
        System.out.println("hi");
        Scanner s = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        Task task = null;

        // main loop
        mainLoop:
        while (true) {
            String input = s.next();
            switch (input) {
                case "bye":
                    break mainLoop;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i+1) + "." + list.get(i));
                    }
                    break;
                case "done":
                    task = list.get(s.nextInt() - 1);
                    task.done = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;
                default:
                    String[] params;

                    switch (input) {
                        case "todo":
                            task = new Todo(s.nextLine());
                            break;
                        case "deadline":
                            params = s.nextLine().split(" /by ");
                            task = new Deadline(params[0], params[1]);
                            break;
                        case "event":
                            params = s.nextLine().split(" /at ");
                            task = new Event(params[0], params[1]);
                            break;
                        default:
                            break;
                    }

                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
            }
        }

        // end
        System.out.println("bye");
    }

}