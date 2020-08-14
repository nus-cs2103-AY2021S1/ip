// Bot.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
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

    public boolean processCommand(String cmd) {
        var command = parseCommand(cmd);
        switch (command.getType()) {
        case Quit:
            this.printMessage(Arrays.asList(
                String.format("goodbye.")
            ));
            return false;

        case AddItem:
            var name = command.getItemName().get();
            this.tasks.add(new Task(name));
            this.printMessage(String.format("added: %s", name));
            break;

        case ListItems:
            this.printMessage(
                Stream.iterate(0, x -> x + 1)
                    .map(i -> String.format("%d. %s", 1 + i, this.tasks.get(i)))
                    .limit(this.tasks.size())
                    .collect(Collectors.toList())
            );
            break;

        case MarkItemAsDone:
            var idx = command.getItemIndex().get();
            assert 0 < idx && idx <= this.tasks.size();

            // one-indexed
            idx -= 1;

            this.tasks.get(idx).markAsDone();
            this.printMessage(Arrays.asList(
                String.format("marked as done:"),
                String.format("  %s", this.tasks.get(idx))
            ));
            break;
        }

        return true;
    }

    private static Command parseCommand(String input) {
        var sc = new Scanner(input);
        var cmd = sc.next().trim();

        if (cmd.equals("bye")) {
            return Command.quit();
        } else if (cmd.equals("list")) {
            return Command.listItems();
        } else if (cmd.equals("done")) {
            return Command.markItemAsDone(sc.nextInt());
        } else {
            return Command.addItem(input);
        }
    }
}
