package duke;
public class Parser {

    Parser() { }

    /**
     * split up the user input and recognise the executable functions
     * @param input user input
     */
    public void parse(String input) {
        if (input.contains("list")) {
            TaskList.printTaskList();
        } else if (input.contains("bye")) {
            Ui.bye();
        } else {
            try {
                String key = input.split(" ", 2)[0];
                String command = input.split(" ", 2)[1];
                if (key.contains("done")) {
                    TaskList.doneTask(command);
                } else if (key.equals("find")) {
                    TaskList.findTask(command);
                } else if (key.equals("delete")) {
                    TaskList.deleteTask(command);
                } else if (key.equals("todo")) {
                    TaskList.createTodo(command);
                } else if (key.equals("event")) {
                    TaskList.createEvent(command);
                } else if (key.equals("deadline")) {
                    TaskList.createDeadline(command);
                } else {
                    System.out.println(new DukeException("command not found"));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(new DukeException("command not found"));
            }
        }
    }
}
