import java.util.Scanner;

public class Duke {
    protected static Storage storage;

    public Duke() {
        // set up the things needed to start Duke
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveTextFile()) {
            storage.loadData();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        DukeCommandsHandler.greetings();
        String name = sc.nextLine();
        DukeCommandsHandler.addressUser(name);

        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            try {
                if (input.startsWith("todo")) { // add todo tasks
                    DukeCommandsHandler.addToDo(input);
                } else if (input.startsWith("deadline")) { // add deadline tasks
                    DukeCommandsHandler.addDeadline(input);
                } else if (input.startsWith("event")) { // add event tasks
                    DukeCommandsHandler.addEvent(input);
                } else if (input.startsWith("delete")) { // delete tasks
                    DukeCommandsHandler.deleteTask(input);
                } else if (input.startsWith("done")) { // mark tasks done
                    DukeCommandsHandler.markTaskDone(input);
                } else if (input.equals("list")) { // list out the tasks
                    DukeCommandsHandler.listTasks();
                } else if (input.equals("bye")) { // exit the bot
                    DukeCommandsHandler.exitFocus();
                    break;
                } else { // handle invalid inputs
                    DukeCommandsHandler.invalidInput();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}