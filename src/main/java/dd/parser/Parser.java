package parser;

import commands.*;

public class Parser {
    public static Command parse(String input) {
        Command c;

        if (input.equals("bye")) {
            c = new ExitCommand("exit", "");
        }
        else if (input.equals("list")) {
            System.out.println("Here is your current list of task(s)!");
            c = new ListCommand("list", "");
        }
        else if (input.startsWith("todo")) {
            c = new AddCommand("todo", input.substring(5));
        }
        else if (input.startsWith("deadline")) {
            c = new AddCommand("deadline", input.substring(9));
        }
        else if (input.startsWith("event")) {
            c = new AddCommand("event", input.substring(6));
        }
        else if (input.startsWith("done")) {
            c = new DoneCommand("done", input.substring(5));
        }
        else if (input.startsWith("delete")) {
            c = new DeleteCommand("delete", input.substring(7));
        }
        else if (input.startsWith("check")) {
            c = new ListCommand("check", input.substring(6));
        }
        else {
            // not valid task
            c = null;
        }
        return c;
    }
}
