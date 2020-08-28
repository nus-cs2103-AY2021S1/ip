import java.util.*;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            e.printStackTrace();
            ui.displayLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        ui.displayWelcome();

        loop: while (sc.hasNextLine()) {
            String commandLine = sc.nextLine();
            try {
                parser.setCommandLine(commandLine);
                String command = parser.getCommandWord();

                switch (command) {
                    case "bye":
                        storage.save(taskList);
                        ui.displayBye();
                        sc.close();
                        break loop;
                    case "list":
                        ui.displayList(taskList);
                        break;
                    case "done":
                        taskList.done(parser);
                        break;
                    case "delete":
                        taskList.delete(parser);
                        break;
                    case "deadline":
                    case "event":
                    case "todo":
                        taskList.add(parser);
                        break;
                }
            } catch (DukeException e) {
                ui.displayMessage(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Duke("duke_save_data.txt").run();
    }

}

