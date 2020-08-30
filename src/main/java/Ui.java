import java.util.Scanner;

public class Ui {

    String introduction;
    Scanner reader;

    public Ui() {
        reader = new Scanner(System.in);
        introduction = "Hi, I'm your Professor, Martin." +
                "\nWhat can I do for you? You can ask me to do these:" +
                "\nlist: List the current tasks in your list." +
                "\ntodo: Add a To-Do task." +
                "\nevent: Add an Event task." +
                "\ndeadline: Add a Deadline task." +
                "\nfind: Find a task which matches your description.";
    }

    public String readCommand() {
        System.out.println("Enter input:");
        return reader.hasNextLine() ? reader.nextLine() : "";
    }

    public void showIntroduction() {
        System.out.println(introduction);
    }

    public void showLine() {
        System.out.println("------------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void closeUi() {
        this.reader.close();
    }

    public void showException(Exception e) {
        System.out.println(e);
    }

}
