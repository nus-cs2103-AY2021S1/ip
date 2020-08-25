import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isActive = true;

        ui.greet();
        while(isActive) {
            input = sc.nextLine().trim();
            String arr[] = input.split(" ", 2);
            String command = arr[0];
            String remainingText = arr.length == 1 ? null : arr[1].trim();
            isActive = !input.equalsIgnoreCase("bye");

            try {
                switch (command) {
                    case "list": {
                        if (remainingText != null) throw new DukeException("Did you mean to say \'list\'?");
                        new ListCommand().execute(tasks, ui, storage);
                        break;
                    }
                    case "todo": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        Task task = new TodoTask(remainingText);
                        new AddCommand(task).execute(tasks, ui, storage);
                        break;
                    }
                    case "deadline": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        String taskItems[] = remainingText.split(" /by ");
                        String description = taskItems[0].trim();
                        if(taskItems.length == 1) throw new DukeException("The date for a deadline cannot be empty.");
                        LocalDate by = LocalDate.parse(taskItems[1].trim());
                        Task task = new DeadlineTask(description, by);
                        new AddCommand(task).execute(tasks, ui, storage);
                        break;
                    }
                    case "event": {
                        if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                        String taskItems[] = remainingText.split(" /at ");
                        String description = taskItems[0].trim();
                        if(taskItems.length == 1) throw new DukeException("The date for an event cannot be empty.");
                        LocalDate at = LocalDate.parse(taskItems[1].trim());
                        Task task = new EventTask(description, at);
                        new AddCommand(task).execute(tasks, ui, storage);
                        break;
                    }
                    case "done": {
                        if(remainingText == null) throw new DukeException("Please specify an item number.");
                        int index = Integer.parseInt(remainingText) - 1;
                        new DoneCommand(index).execute(tasks, ui, storage);
                        break;
                    }
                    case "delete": {
                        if(remainingText == null) throw new DukeException("Please specify an item number.");
                        int index = Integer.parseInt(remainingText) - 1;
                        new DeleteCommand(index).execute(tasks, ui, storage);
                        break;
                    }
                    case "bye": {
                        if (remainingText != null) throw new DukeException("Did you mean to say \'bye\'?");
                        new ByeCommand().execute(tasks, ui, storage);
                        break;
                    }
                    default: {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch(DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }
}

