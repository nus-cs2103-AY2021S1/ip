import java.io.*;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        storage = new Storage();

        try {
            taskList = new TaskList(storage.readData());
            ui = new Ui(taskList, storage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void run() {
        try {
            ui.startProgram();
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

//    public static void main(String[] args) {
//        // Start
//        start();
//
//        // Take in inputs
//        Storage storage = new Storage();
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//
//        try {
//            list = storage.readData();
//
//            while (!input.equals("bye")) {
//                try {
//                    System.out.println(line);
//
//                    // Show list
//                    if (input.equals("list")) {
//                        list();
//
//                        // Add task
//                    } else if (input.contains("todo")) {
//                        todo(input);
//
//                        // Add event
//                    } else if (input.contains("event")) {
//                        event(input);
//
//                        // Add deadline
//                    } else if (input.contains("deadline")) {
//                        deadline(input);
//
//                        // Complete task
//                    } else if (input.contains("done")) {
//                        done(input);
//
//                        // Delete task
//                    } else if (input.contains("delete")) {
//                        delete(input);
//
//                    } else {
//                        throw new WrongCommandException();
//                    }
//
//                    storage.addData(list);
//                    System.out.println(line);
//                    input = sc.nextLine();
//
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                    System.out.println(line);
//                    input = sc.nextLine();
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // End
//        end();
//    }
}
