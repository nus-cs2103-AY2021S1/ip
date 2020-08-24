package main.java;

class Parser {

    static Command parse(String input) {

        String[] splitter = input.split(" ", 2);
        String prefix = splitter[0];
        String body = null;
        if (splitter.length == 2) {
            body = splitter[1];
        }

        switch(prefix) {
        case("bye"):
            return new ExitCommand();
        case("deadline"):
            return new CreateDeadlineCommand(body);
        case("delete"):
            try {
                int taskIndex = Integer.parseInt(body);
                return new DeleteTaskCommand(taskIndex - 1);
            } catch (NumberFormatException ignored) {
            }
            break;

        case("done"):
            try {
                int taskIndex = Integer.parseInt(body);
                return new CompleteTaskCommand(taskIndex - 1);
            } catch (NumberFormatException ignored) {
            }
            break;

        case("event"):
            return new CreateEventCommand(body);
        case("list"):
            return new ListTasksCommand();
        case("todo"):
            return new CreateTodoCommand(body);
        }

        return new InvalidCommand();
    }
}
