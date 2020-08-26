public class Parser {

    public Parser() {

    }

    public void parseCommand(String[] inputs, TaskList tasks) {
        String command = inputs[0];
        switch(command) {
            case "list":
                tasks.list();
                break;
            case "done":
                try {
                    tasks.handleDone(inputs);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "deadline":
                tasks.handleDeadline(inputs);
                break;
            case "event":
                tasks.handleEvent(inputs);
                break;
            case "todo":
                try {
                    tasks.handleToDo(inputs);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "delete":
                try {
                    tasks.handleDelete(inputs);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            default:
                try {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }

    }
}
