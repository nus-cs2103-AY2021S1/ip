public class Parser {
    public Parser() {

    }

    public static Command parse(String nextCommand, TaskList tasks) {
        String[] line = nextCommand.split(" ", 2);
        String operation = line[0];
        switch (operation) {
        case "bye":
            System.out.println("Bye bye. See you again soon!");
        case "list":
            try {
                Helper.showList(tasks.getTasks());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "done":
            try {
                Helper.markAsDone(line, tasks.getTasks());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            if (line.length == 1) {
                System.out.println("☹ OOPS! Description for command '"
                        + operation + "' not found, try again!");
                break;
            } else if (operation.equals("todo")) {
                try {
                    Helper.addToDo(line, tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (operation.equals("deadline")) {
                try {
                    Helper.addDeadline(line, tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    Helper.addEvent(line, tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
        case "delete":
            try {
                Helper.deleteTask(line, tasks.getTasks());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            System.out.println("☹ Sorry, I don't recognise that command! "
                    + "Try one of the following instead: todo, event, deadline, done or delete");
        }
        return null;
    }

}
