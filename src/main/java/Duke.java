package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    String name;
    int id;
    boolean done = false;

    static int count = 0;

    public Task(String name) {
        this.name = name;
        count++;
        this.id = count;
    }

    @Override
    public String toString() {
        return id + ". [" + (done ? "✓" : "✗") + "] " + name;
    }
}

public class Duke {
    public static void main(String[] args) {
        // startup
        System.out.println("hi");
        Scanner s = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        // main loop
        mainLoop:
        while (true) {
            String input = s.next();
            switch (input) {
                case "bye":
                    break mainLoop;
                case "list":
                    for (Task task : list) {
                        System.out.println(task);
                    }
                    break;
                case "done":
                    Task task = list.get(s.nextInt() - 1);
                    task.done = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;
                default:
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                    break;
            }
        }

        // end
        System.out.println("bye");
    }

}