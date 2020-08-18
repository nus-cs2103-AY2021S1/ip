import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi I'm Duke, your personal task-tracker bot!");
        System.out.println("You can add todos, deadlines, or events to my " +
                                   "list.");

        try {
            Scanner.scan();
        } catch (IOException exception) {
            System.out.println("An exception occurred:");
            System.out.println(exception.getMessage());
        }
    }
}
