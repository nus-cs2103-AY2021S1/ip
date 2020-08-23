package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;


import java.util.Scanner;
import java.io.File;

public class Duke {
    private final static String LINE = "*********************************************************";
    private static Parser parser;

    public static void main(String[] args) {
        File f = new File("D:\\24092014\\Joven\\UNI STUFF\\CS2103\\IP\\task.txt");
        welcome();
        Storage storage = new Storage(f);
        TaskList shelf = new TaskList(storage.loadFile());
        UI ui = new UI(shelf, storage);
        parser = new Parser(ui);
        Scanner sc = new Scanner(System.in);
        handler(sc);
        sc.close();
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
        System.out.println("Hello from " + logo);
        System.out.println("What can I do for you pal? :D");
        System.out.println(LINE);
    }

    public static void handler(Scanner sc) {
        while (true) {
            String response = sc.nextLine();
            parser.listen(response);
            System.out.println(LINE);
            if (parser.toStop(response)) {
                break;
            }
        }
    }
}
