import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {
    // array of valid commands
    private final static List<String> COMMANDS = Arrays.asList("done",
                                                               "delete");
    private final TaskList userTaskList;
    private final Storage storage;

    Parser(Storage storage) {
        this.userTaskList = new TaskList();
        this.storage = storage;
    }

    Parser(Storage storage, TaskList userTaskList) {
       this.userTaskList = userTaskList;
       this.storage = storage;
    }

    void scan() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader((System.in)));

        scanLoop:
        while(true) {
            String line = reader.readLine();
            String command = line.split(" ")[0];

            if (COMMANDS.contains(command) && line.split(" ").length > 1) {
                // handle commands with >1 argument
                switch (command) {
                case "done":
                    try {
                        userTaskList.markAsDone(
                                Integer.parseInt(line.split( " ")[1])
                        );
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        userTaskList.removeItem(
                                Integer.parseInt(line.split( " ")[1])
                        );
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    break;
                }
            } else {
                switch(line) {
                case "bye":
                    System.out.println("Bye. Hope to see you again");
                    break scanLoop;
                case "list":
                    userTaskList.printList();
                    break;
                case "save":
                    storage.save(userTaskList);
                    break;
                default:
                    try {
                        Task addedTask = userTaskList.addItem(line);
                        System.out.println("added: " + addedTask);
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }
            }
        }
    }
}
