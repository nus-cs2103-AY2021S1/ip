// Bot.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Bot {
    private String name;
    private List<String> inputs;

    public Bot(String name) {
        this.name = name;
        this.inputs = new ArrayList<>();
    }

    private void printMessage(List<String> msgs) {
        var line = "-------------------------------------";
        System.out.printf("\n  %s\n", line);
        for (var m : msgs) {
            System.out.printf("  %s\n", m);
        }

        System.out.printf("  %s\n\n", line);
    }

    public void greet() {
        this.printMessage(Arrays.asList(
            String.format("Hello, I'm %s", this.name),
            String.format("What can I do for you?")
        ));
    }

    public boolean processCommand(String cmd) {
        if (cmd.equals("bye")) {
            this.printMessage(Arrays.asList(
                String.format("goodbye.")
            ));
            return false;
        } else if (cmd.equals("list")) {
            this.printMessage(
                Stream.iterate(0, x -> x + 1)
                    .map(i -> String.format("%d. %s", 1 + i, this.inputs.get(i)))
                    .limit(this.inputs.size())
                    .collect(Collectors.toList())
            );
            return true;
        } else {
            this.inputs.add(cmd);
            this.printMessage(Arrays.asList(
                String.format("added: %s", cmd)
            ));
            return true;
        }
    }
}
