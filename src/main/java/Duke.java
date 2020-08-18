import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String linePrinter() {
        return ("\n--------------------------------------------------------\n").replaceAll("(?m)^", "\t");
    }

    public static String start() {
        String s = ("Hello! I'm Duke\n" +
                "What can I do for you?");
        return linePrinter() +
                s.replaceAll("(?m)^", "\t") +
                linePrinter();
    }

    public static void main(String[] args) {
        String endCommand = "bye";
        String listCommand = "list";
        ArrayList<String> ls = new ArrayList<String>();

        System.out.println(start());

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while(!s.equals(endCommand)) {
            if (s.equals(listCommand)) {
                System.out.println(linePrinter());
                for (String obj : ls) {
                    System.out.println(
                            ((ls.indexOf(obj) + 1) + ". " +
                            obj).replaceAll("(?m)^", "\t"));
                }
                System.out.println(linePrinter());
                s = in.nextLine();
            } else {
                ls.add(s);
                String thing = "added: " + s;
                System.out.println(linePrinter() +
                        thing.replaceAll("(?m)^", "\t") +
                        linePrinter());
                s = in.nextLine();
            }
        }

        if (s.equals(endCommand)) {
            String byetext = "Bye. Hope to see you again soon!";
            System.out.println(linePrinter() +
                    byetext.replaceAll("(?m)^", "\t") +
                    linePrinter());
        }
    }
}
