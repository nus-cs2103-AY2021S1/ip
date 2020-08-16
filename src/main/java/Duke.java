import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String DIVIDER = "__________________________________________________________";
    private static String BYE_COMMAND = "bye";
    private static String LIST_COMMAND = "list";

    private static void messageEcho(String word) {
        System.out.println(DIVIDER);
        System.out.println(word);
        System.out.println(DIVIDER + "\n");
    }

    private static String listIterator(ArrayList<String> list) {
        StringBuffer string = new StringBuffer();
        for (int i =0; i < list.size(); i++){
            if (list.size() == i + 1) {
                string.append((i + 1) + ". " + list.get(i));
            } else {
                string.append((i + 1) + ". " + list.get(i) + "\n");
            }
        }
        return string.toString();
    }

    public static void main(String[] args) {
        messageEcho("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        while (sc.hasNext()) {
            String word  = sc.nextLine();
            if (word.equals(BYE_COMMAND)) {
                messageEcho("Bye. Hope to see you again soon!");
                break;
            }
            if (word.equals(LIST_COMMAND)) {
                messageEcho(listIterator(list));
                continue;
            }

            list.add(word);
            messageEcho("added: " + word);
        }
    }
}
