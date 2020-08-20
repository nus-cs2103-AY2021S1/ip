import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    TextList textList;

    Duke() {
        this.textList = new TextList();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }

    private void initialise() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                list();
            } else {
                add(text);
            }
        }
        exit();
    }

    private void greet() {
        System.out.println("    ------------------------------------------------------\n" +
                "     Hello! I'm Duke \n     What can I do for you?\n" +
                "    ------------------------------------------------------");
    }

    private void echo(String command) {
        System.out.println("    ------------------------------------------------------\n" +
                "     " + command + "\n" +
                "    ------------------------------------------------------");
    }

    private void exit() {
        System.out.println("    ------------------------------------------------------\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ------------------------------------------------------");
    }

    private void add(String text) {
        textList.acceptText(text);
        System.out.println("    ------------------------------------------------------\n" +
                "     added: " + text + "\n" +
                "    ------------------------------------------------------");
    }

    private void list() {
        System.out.println("    ------------------------------------------------------");
        ArrayList<String> texts = textList.retrieve();
        ListIterator<String> listIterator = texts.listIterator();
        int i = 1;
        while (listIterator.hasNext()) {
            System.out.println("     " + i + ". " + listIterator.next());
            i++;
        }
        System.out.println("    ------------------------------------------------------");
    }
}
