// Bot.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.BiConsumer;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

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

        if (cmd.equals("bye")) {
            println("goodbye");
            return false;
        }

        var input = sc.hasNextLine()
            ? sc.nextLine().strip()
            : "";

        beginLog();

        Optional.ofNullable(Map.<String, BiConsumer<String, String>>of(
            "done",     this::cmdDone,
            "list",     this::cmdList,
            "todo",     this::cmdAddTask,
            "event",    this::cmdAddTask,
            "deadline", this::cmdAddTask
        ).get(cmd))
            .ifPresentOrElse(x -> {
                x.accept(cmd, input);
            }, () -> {
                println("unknown command '%s'", cmd);
            });

        endLog();
        return true;
    }



    private void cmdList(String cmd, String input) {

        assert cmd.equals("list");

        var doneTasks = this.tasks.stream().filter(x -> x.isDone()).count();
        var pendingTasks = this.tasks.size() - doneTasks;

        println("tracking %d task%s (%d pending, %d done, %.1f%% complete)",
            this.tasks.size(), this.tasks.size() == 1 ? "" : "s", pendingTasks, doneTasks,
            this.tasks.isEmpty() ? 100.0 : 100.0 * ((double) doneTasks / this.tasks.size()));

        Stream.iterate(0, x -> x + 1)
            .map(i -> String.format("  %d. %s", 1 + i, this.tasks.get(i)))
            .limit(this.tasks.size())
            .forEach(Bot::println);
    }

    private void cmdDone(String cmd, String input) {

        assert cmd.equals("done");

        try {
            // one-indexed
            var idx = new Scanner(input).nextInt() - 1;

            this.tasks.get(idx).markAsDone();

            println("marked as done:");
            println("  %s", this.tasks.get(idx));

        } catch (InputMismatchException e) {

            println("error: expected an integer task number ('%s' invalid)", input);

        } catch (NoSuchElementException e) {

            println("error: expected a task number for 'done' command");

        } catch (IndexOutOfBoundsException e) {

            println("error: task number '%s' was out of bounds", input);
        }
    }

    private void cmdAddTask(String cmd, String input) {

        assert cmd.equals("todo") || cmd.equals("event") || cmd.equals("deadline");

        Task task = null;

        try {
            switch (cmd) {
            case "todo":
                task = Todo.parse(input);
                break;

            case "event":
                task = Event.parse(input);
                break;

            case "deadline":
                task = Deadline.parse(input);
                break;

            default:
                assert false;
            }

            this.tasks.add(task);
            println("added: %s", task);

        } catch (InvalidInputException e) {

            println("error: %s", e);
            println("usage: %s", e.getUsage());
        }
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
