import java.io.IOException;

public class Duke {
    static final String FILE_PATH = "savedTasks.txt";

    public static void main(String[] args) {
        System.out.println("Hi I'm Duke, your personal task-tracker bot!");
        System.out.println("You can add todos, deadlines, or events to my " +
                                   "list.");
        Storage storage = new Storage(FILE_PATH);

        try {
            Parser parser;
            if (storage.doesExist()) {
                TaskList savedList = storage.load();
                parser = new Parser(storage, savedList);
                System.out.println("Your existing task list has been retrieved from disk.");
            } else {
                System.out.println("You don't have an existing saved task list.");
                parser = new Parser(storage);
            }
            parser.scan();
        } catch (IOException e) {
            System.out.println("An exception occurred:");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
                System.err.println("The file specified doesn't store a TaskList object." + e);
        }
    }
}
