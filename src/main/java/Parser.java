package main.java;

class Parser {

    Command parse(String input) {

        String[] prefixParser = input.split(" ", 2);
        String prefix = prefixParser[0];
        String body = prefixParser[1];

        switch(prefix) {
        case("bye"):
            return new ExitCommand();
        case("deadline"):
            return new CreateDeadlineCommand();
        case("delete"):
            return new DeleteTaskCommand();
        case("done"):
            return new CompleteTaskCommand();
        case("event"):
            return new CreateEventCommand();
        case("list"):
            return new ListTasksCommand();
        case("todo"):
            return new CreateTodoCommand();
        default:
            return new InvalidCommand();
        }
    }
}
