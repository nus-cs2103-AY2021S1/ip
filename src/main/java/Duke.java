import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final UI ui;

    public Duke() {
        // set up the things needed to start Duke
        ui = new UI();
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveTextFile()) {
            taskList = new TaskList(storage.loadData());
        } else {
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetUser();
        String name = sc.nextLine();
        ui.addressUser(name);

        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            try {
                if (input.startsWith("todo")) { // add todo tasks
                    taskList.addToDo(input, storage);
                } else if (input.startsWith("deadline")) { // add deadline tasks
                    taskList.addDeadline(input, storage);
                } else if (input.startsWith("event")) { // add event tasks
                    taskList.addEvent(input, storage);
                } else if (input.startsWith("delete")) { // delete tasks
                    taskList.deleteTask(input, storage);
                } else if (input.startsWith("done")) { // mark tasks done
                    taskList.markTaskDone(input, storage);
                } else if (input.equals("list")) { // list out the tasks
                    taskList.listTasks();
                } else if (input.equals("bye")) { // exit the bot
                    ui.exitFocus();
                    break;
                } else { // handle invalid inputs
                    ui.invalidInput();
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