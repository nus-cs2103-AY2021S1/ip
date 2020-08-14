// Bot.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Bot {
    private final String name;
    private final List<Task> tasks;

    public Bot(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    private void printMessage(List<String> msgs) {
        var line = "-------------------------------------";
        System.out.printf("\n  %s\n", line);
        for (var m : msgs) {
            System.out.printf("  %s\n", m);
        }

        System.out.printf("  %s\n\n", line);
    }

    private void printMessage(String m) {
        var line = "-------------------------------------";
        System.out.printf("\n  %s\n", line);
        System.out.printf("  %s\n", m);
        System.out.printf("  %s\n\n", line);
    }

    public void greet() {
        this.printMessage(Arrays.asList(
            String.format("Hello, I'm %s", this.name),
            String.format("What can I do for you?")
        ));
    }

    public boolean processCommand(String str) {
        var sc = new Scanner(str);
        if (!sc.hasNext()) {
            return true;
        }

        var cmd = sc.next().trim();

        if (cmd.equals("bye")) {
            this.printMessage("goodbye.");
            return false;
        } else if (cmd.equals("list")) {
            this.printMessage(
                Stream.iterate(0, x -> x + 1)
                    .map(i -> String.format("%d. %s", 1 + i, this.tasks.get(i)))
                    .limit(this.tasks.size())
                    .collect(Collectors.toList())
            );
        } else if (cmd.equals("done")) {
            // one-indexed
            var idx = sc.nextInt() - 1;
            assert 0 <= idx && idx < this.tasks.size();

            this.tasks.get(idx).markAsDone();
            this.printMessage(Arrays.asList(
                String.format("marked as done:"),
                String.format("  %s", this.tasks.get(idx))
            ));
        } else if (cmd.equals("todo")) {
            var task = new Todo(sc.nextLine().trim());
            this.tasks.add(task);

            this.printMessage(Arrays.asList(
                String.format("added: %s", task)
            ));
        } else if (cmd.equals("event")) {
            sc.useDelimiter("/");
            var item = sc.next().trim();
            var when = sc.next().trim();

            if (!when.startsWith("at ")) {
                this.printMessage(String.format("expected: event <name> /at time"));
                return true;
            }

            when = when.substring(3).trim();
            var task = new Event(item, when);
            this.tasks.add(task);

            this.printMessage(Arrays.asList(
                String.format("added: %s", task)
            ));
        } else if (cmd.equals("deadline")) {
            sc.useDelimiter("/");
            var item = sc.next().trim();
            var when = sc.next().trim();

            if (!when.startsWith("by ")) {
                this.printMessage(String.format("expected: deadline <name> /by time"));
                return true;
            }

            when = when.substring(3).trim();

            var task = new Deadline(item, when);
            this.tasks.add(task);

            this.printMessage(Arrays.asList(
                String.format("added: %s", task)
            ));
        } else {
            this.printMessage(String.format("unknown command '%s'", cmd));
        }

        return true;
    }
}
