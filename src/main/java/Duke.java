import java.io.IOException;

public class Duke {
    static final String FILE_PATH = "savedTasks.txt";

    public static void main(String[] args) {
        System.out.println("Hi I'm Duke, your personal task-tracker bot!");
        System.out.println("You can add todos, deadlines, or events to my " +
                                   "list.");

        try {
            if (Reader.doesFileExist(FILE_PATH)) {
                TaskList savedList = Reader.readListFromFile(FILE_PATH);
                System.out.println("Your existing task list has been retrieved from disk.");
                Scanner.scan(savedList);
            } else {
                System.out.println("You don't have an existing saved task list.");
                Scanner.scan();
            }
        } catch (IOException e) {
            System.out.println("An exception occurred:");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
                System.err.println("The file specified doesn't store a TaskList object." + e);
        }
    }
}
