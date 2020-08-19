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

    public void greet() {
        println("Hello, I'm %s", this.name);
        println("What can I do for you?");
    }

    public boolean processCommand(String str) {
        var sc = new Scanner(str);
        if (!sc.hasNext()) {
            return true;
        }

        var cmd = sc.next().strip();
        beginLog();

        if (cmd.equals("bye")) {

            println("goodbye");
            return false;

        } else if (cmd.equals("list")) {

            var doneTasks = this.tasks.stream().filter(x -> x.isDone()).count();
            var pendingTasks = this.tasks.size() - doneTasks;

            println("tracking %d tasks (%d pending, %d done, %.1f%% complete)",
                this.tasks.size(), pendingTasks, doneTasks,
                this.tasks.isEmpty() ? 100.0 : 100.0 * ((double) doneTasks / this.tasks.size()));

            Stream.iterate(0, x -> x + 1)
                .map(i -> String.format("  %d. %s", 1 + i, this.tasks.get(i)))
                .limit(this.tasks.size())
                .forEach(Bot::println);

        } else if (cmd.equals("done")) {

            // one-indexed
            var idx = sc.nextInt() - 1;
            assert 0 <= idx && idx < this.tasks.size();

            this.tasks.get(idx).markAsDone();

            println("marked as done:");
            println("  %s", this.tasks.get(idx));

        } else if (cmd.equals("todo")) {

            var task = new Todo(sc.nextLine().strip());
            this.tasks.add(task);

            println("added: %s", task);

        } else if (cmd.equals("event")) {

            sc.useDelimiter("/");
            var item = sc.next().strip();
            var when = sc.next().strip();

            if (!when.startsWith("at ")) {
                println("expected: event <name> /at <time>");
                return true;
            }

            when = when.substring(3).strip();
            var task = new Event(item, when);
            this.tasks.add(task);

            println("added: %s", task);

        } else if (cmd.equals("deadline")) {

            sc.useDelimiter("/");
            var item = sc.next().strip();
            var when = sc.next().strip();

            if (!when.startsWith("by ")) {
                println("expected: deadline <name> /by <time>");
                return true;
            }

            when = when.substring(3).strip();

            var task = new Deadline(item, when);
            this.tasks.add(task);

            println("added: %s", task);

        } else {
            println("unknown command '%s'", cmd);
        }

        endLog();
        return true;
    }

    private static void println(String fmt, Object... args) {
        System.out.printf(fmt, args);
        System.out.println();
    }

    private static void beginLog() {
        println("");
    }

    private static void endLog() {
        println("--------------------------------------");
    }
}
