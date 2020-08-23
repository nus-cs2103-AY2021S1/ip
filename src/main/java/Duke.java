import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private TaskList list;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeInvalidData dukeInvalidData) {
            ui.showLoadingError();
            list = new TaskList();
        }
    }

    public void run() {
        ui.intro();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("bye")) {
                ui.printBye();
                break;
            } else if (nextLine.equals("list")) {
                ui.printWindow(list.getListAsString());
            } else if (nextLine.startsWith("done")) {
                try {
                    ui.showMarkDone(list.markDone(nextLine));
                } catch (DukeEmptyDoneIndexException | DukeInvalidDoneIndexException e) {
                    ui.printWindow(e.getMessage());
                }
            } else if (nextLine.startsWith("delete")) {
                try {
                    ui.showDeleted(list.deleteTask(nextLine), list.getSize());
                } catch (DukeInvalidDeleteIndexException | DukeEmptyDeleteIndexException e) {
                    ui.printWindow(e.getMessage());
                }
            } else {
                try {
                    ui.showAdded(list.addTask(nextLine), list.getSize());
                } catch (DukeUnknownInputException |
                        DukeEmptyByException |
                        DukeEmptyAtException |
                        DukeEmptyDescriptionException e) {
                    ui.printWindow(e.getMessage());
                }
            }
            try {
                storage.save(list.list);
            } catch (IOException e) {
                ui.printWindow(e.getMessage());
            }
        }
    }

    public static void checkAndMakeDir(String filePath) {
        new File(filePath).mkdir();
    }

    public static void main(String[] args) {
        String homePath = System.getProperty("user.home");
        checkAndMakeDir(homePath + "/data");
        Duke duke = new Duke(homePath + "/data/duke.txt");
        duke.run();
    }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
}
