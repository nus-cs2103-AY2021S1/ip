import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    TaskList taskList = new TaskList();
    Ui ui = new Ui(taskList);

    private void activate() {
        Scanner sc = new Scanner(System.in);
        ui.greeting();

        String input = "";
        while (!input.equals("bye") && sc.hasNextLine()) {
            input = sc.nextLine();
            reply(input);
        }
    }

    private void reply(String input) {

        Command command = null;
        try {
            command = Parser.parse(input);
            switch(command) {
                case HELP:
                    ui.help();
                    break;
                case QUIT:
                    ui.quit();
                    break;
                case LIST:
                    ui.list();
                    break;
                case DONE:
                    markAsDone(input);
                    break;
                case DELETE:
                    delete(input);
                    break;
                case TODO:
                    addTodo(input);
                    break;
                case DEADLINE:
                    addDeadline(input);
                    break;
                case EVENT:
                    addEvent(input);
                    break;
            }
        } catch (DukeException e) {
            e.printStackTrace();
        }


    }

    private void markAsDone(String input) throws InvalidTaskIdException {
        int taskId = Parser.parseTaskId(input);

        if (taskId < 0 || taskId >= taskList.taskSize())
            throw new InvalidTaskIdException(taskId + 1 + "");

        Task task = taskList.markAsDone(taskId);
        ui.markAsDone(task);
    }

    private void delete(String input) throws InvalidTaskIdException {
        int taskId = Parser.parseTaskId(input);

        if (taskId < 0 || taskId >= taskList.taskSize())
            throw new InvalidTaskIdException(taskId + 1 + "");

        Task task = taskList.delete(taskId);
        ui.delete(task);
    }

    private void addTodo(String input) throws EmptyDescriptionException {
        String description = Parser.parseTodo(input);
        Task task = new Todo(description);
        taskList.add(task);
        ui.add(task);
    }

    private void addDeadline(String input) throws EmptyDescriptionException, InvalidFormatException {
        String[] details = Parser.parseDeadline(input);
        String title = details[0];
        String by = details[1];
        Task task = new Deadline(title, by);
        taskList.add(task);
        ui.add(task);
    }

    private void addEvent(String input) throws EmptyDescriptionException, InvalidFormatException {
        String[] details = Parser.parseEvent(input);
        String title = details[0];
        String at = details[1];
        Task task = new Deadline(title, at);
        taskList.add(task);
        ui.add(task);
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.activate();
    }
}
