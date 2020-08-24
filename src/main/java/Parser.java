package main.java;

public class Parser {

    public Parser () {}

    public Command interpret (String line) {
        if (line.indexOf("todo") == 0) {
            return new Command(line, "todo");
        } else if (line.indexOf("delete") == 0) {
            return new Command(line, "delete");
        } else if (line.indexOf("deadline") == 0) {
            return new Command(line, "deadline");
        } else if (line.indexOf("event") == 0) {
            return new Command(line, "event");
        } else if (line.indexOf("done") == 0 || line.equals("bye") || line.equals("list")) {
            return new Command(line, line);
        } else {
            return new Command(line, null);
        }
    }
}
