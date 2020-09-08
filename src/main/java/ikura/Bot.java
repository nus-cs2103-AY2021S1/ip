// Bot.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.Map;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Optional;
import java.util.function.BiConsumer;

import ikura.task.Task;
import ikura.task.Todo;
import ikura.task.Event;
import ikura.task.Deadline;
import ikura.task.DatedTask;
import ikura.task.TaskParser;
import ikura.util.StreamUtils;
import ikura.task.TaskDescription;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import ikura.util.InvalidInputException;

/**
 * The main class containing the functionality of the application. Does not own the
 * tasklist nor the frontend.
 */
public class Bot {

    private final TaskList tasks;
    private final Frontend ui;

    private final Map<String, BiConsumer<String, String>> commandMap = Map.of(
        "done",     this::cmdDone,
        "list",     this::cmdList,
        "find",     this::cmdFind,
        "reset",    this::cmdReset,
        "delete",   this::cmdDelete,
        "todo",     this::cmdAddTask,
        "event",    this::cmdAddTask,
        "deadline", this::cmdAddTask,
        "edit",     this::cmdEditTask
    );

    /**
     * Constructs a new Bot using the given Frontend interface and the given TaskList.
     *
     * @param ui    the frontend interface to use.
     * @param tasks the TaskList to use.
     */
    public Bot(Frontend ui, TaskList tasks) {
        this.tasks  = tasks;
        this.ui     = ui;
    }

    /**
     * Gets the task list used by the bot.
     *
     * @return the task list.
     */
    public TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Parses a line of user input, and executes the command as appropriate. The
     * return value specifies whether or not the bot should print the exit message.
     *
     * @param str the line user input.
     * @return true if the bot should continue, false if it should exit.
     */
    public boolean processCommand(String str) {

        var sc = new Scanner(str);
        if (!sc.hasNext()) {
            return true;
        }

        var cmd = sc.next().strip();

        if (cmd.equals("quit")) {
            this.ui.println("goodbye");
            return false;
        }

        var input = sc.hasNextLine()
            ? sc.nextLine().strip()
            : "";

        this.ui.beginLog();

        Optional.ofNullable(commandMap.get(cmd)).ifPresentOrElse(x -> {
            x.accept(cmd, input);
        }, () -> {
            this.ui.println("unknown command '%s'", cmd);
        });

        this.ui.endLog();

        // save the tasks every time, i suppose.
        this.tasks.save();

        return true;
    }


    private void cmdEditTask(String cmd, String input) {
        assert cmd.equals("edit");

        var usage = "edit <task number> [/title <new title>] [/desc <new description>] [/date <new date>]";

        // usage: edit <N> [/title owo] [/desc uwu] [/date kekw]
        // first, extract the task number.
        int taskIndex = 0;

        Runnable printUsage = () -> {
            this.ui.println("invalid arguments");
            this.ui.println("usage: %s", usage);
        };


        {
            // split into 2, because we wanna just pass the rest of the stuff at the end to the TaskParser.
            var pieces = input.split(" ", 2);
            if (pieces.length < 2) {
                printUsage.run();
                return;
            }

            try {
                taskIndex = Integer.parseInt(pieces[0].strip());
            } catch (NumberFormatException e) {
                this.ui.println("invalid task number '%s'", pieces[0]);
                this.ui.println("usage: %s", usage);
                return;
            }

            // java is dumb, yet again.
            final var taskIdx = taskIndex;

            this.tasks.getTaskByNumber(taskIdx)
                .ifPresentOrElse(task -> {

                    TaskDescription descs = null;

                    try {
                        descs = TaskParser.parse("edit", pieces[1], "date",
                            /* dateCompuslory: */ false, usage);
                    } catch (InvalidInputException e) {
                        this.ui.println("error: %s", e);
                        this.ui.println("usage: %s", e.getUsage());
                    }

                    if (descs.hasDate()) {

                        if (task instanceof DatedTask) {
                            var datedTask = ((DatedTask) task);
                            datedTask.setDate(descs.getDate().get());
                        } else {
                            this.ui.println("this task does not accept a date (task not modified)");
                            return;
                        }
                    }

                    System.out.printf("%s, %s\n", descs.hasTitle(), descs.hasDescription());

                    if (descs.hasTitle() && !descs.getTitle().get().isEmpty()) {
                        task.setTitle(descs.getTitle().get());
                    }

                    if (descs.hasDescription()) {
                        task.setDescription(descs.getDescription().get());
                    }

                    this.ui.println("successfully updated task:");
                    this.ui.println("  %s", task);
                }, () -> {
                    this.ui.println("task number %d does not exist", taskIdx);
                    return;
                });




        }
    }


    private void cmdFind(String cmd, String input) {
        assert cmd.equals("find");

        var matches = this.tasks.findTasksByKeywords(Arrays.asList(input.split(" ")));

        this.ui.println("found %d result%s:", matches.size(), matches.size() == 1 ? "" : "s");

        matches.stream()
            .map(p -> p.mapFst(x -> x + 1))             // convert to 1-indexed for printing
            .map(t -> String.format("  %d. %s", t.fst(), t.snd()))
            .forEach(x -> this.ui.println("%s", x));
    }

    private void cmdReset(String cmd, String input) {
        assert cmd.equals("reset");

        this.tasks.clearTasks();

        this.ui.println("cleared all tasks.");
    }

    private void cmdList(String cmd, String input) {

        assert cmd.equals("list");

        this.printTaskStatistics();

        StreamUtils.indexed(this.tasks.stream())
            .map(p -> p.mapFst(x -> x + 1))             // convert to 1-indexed for printing
            .map(t -> String.format("  %d. %s", t.fst(), t.snd()))
            .forEach(x -> this.ui.println("%s", x));
    }

    private void cmdDelete(String cmd, String input) {

        assert cmd.equals("delete");

        this.parseTaskNumber(cmd, input)
            .ifPresent(task -> {

                this.tasks.removeTask(task);

                this.ui.println("deleted task:");
                this.ui.println("  %s\n", task);

                this.printTaskStatistics();
            });
    }

    private void cmdDone(String cmd, String input) {

        assert cmd.equals("done");

        this.parseTaskNumber(cmd, input)
            .ifPresent(task -> {

                if (task.isDone()) {

                    this.ui.println("task is already marked as done:");
                    this.ui.println("  %s", task);

                } else {
                    task.markAsDone();

                    this.ui.println("marked as done:");
                    this.ui.println("  %s\n", task);

                    this.printTaskStatistics();
                }
            });
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

            this.tasks.addTask(task);
            this.ui.println("added: %s", task);

        } catch (InvalidInputException e) {

            this.ui.println("error: %s", e);
            this.ui.println("usage: %s", e.getUsage());
        }
    }

    private Optional<Task> parseTaskNumber(String cmd, String input) {

        try {
            // this is some cursed use of optional
            var idx = new Scanner(input).nextInt();
            return Optional.of(this.tasks.getTaskByNumber(idx)
                .orElseThrow(() -> new IndexOutOfBoundsException("")));

        } catch (InputMismatchException e) {
            this.ui.println("error: expected an integer task number ('%s' invalid)", input);
        } catch (NoSuchElementException e) {
            this.ui.println("error: expected a task number for '%s' command", cmd);
        } catch (IndexOutOfBoundsException e) {
            this.ui.println("error: task '%s' does not exist", input);
        }

        return Optional.empty();
    }

    private void printTaskStatistics() {

        var doneTasks = this.tasks.getNumCompletedTasks();
        var pendingTasks = this.tasks.getNumPendingTasks();

        this.ui.println("currently tracking %d task%s (%d pending, %d done, %.1f%% complete)",
            this.tasks.count(), this.tasks.count() == 1 ? "" : "s", pendingTasks, doneTasks,
            this.tasks.count() == 0 ? 100.0 : 100.0 * ((double) doneTasks / this.tasks.count()));
    }
}
