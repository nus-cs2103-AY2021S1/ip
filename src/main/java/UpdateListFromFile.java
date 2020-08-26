import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UpdateListFromFile {
    public static void updateList(String filePath) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            String string;
            Command command;
            int counter = 1;

            while (s.hasNext()) {
                string = s.nextLine();
                if (string.contains("[D]")) {
                    int deadline = string.indexOf("(");
                    int space = string.indexOf(" ");
                    int colon = string.indexOf(":");

                    String description = "deadline "
                            + string.substring(space + 1, deadline) + "/"
                            + string.substring(deadline + 1, colon)
                            + string.substring(colon + 1, string.length()-1);

                    command = new DeadlineCommand(description);
                } else if (string.contains("[E]")) {
                    int time = string.indexOf("(");
                    int space = string.indexOf(" ");
                    int colon = string.indexOf(":");

                    String description = "event "
                            + string.substring(space + 1, time) + "/"
                            + string.substring(time + 1, colon)
                            + string.substring(colon + 1, string.length()-1);

                    command = new EventCommand(description);
                } else {
                    int space = string.indexOf(" ");
                    String description = "todo " + string.substring(7);
                    command = new ToDoCommand(description);
                }
                command.execute();
                if (string.contains("[\u2713]")) {
                    new DoneCommand(counter).execute();
                }
                counter += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EmptyDescriptionException e) {
            e.getMessage();
        }
    }
}
