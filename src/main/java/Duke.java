import java.util.*;

public class Duke {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        String greet = "   ____________________________________________________________\n" +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n";

        String exit = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        String line = "    ____________________________________________________________";

        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                Iterator<String> iterator = list.iterator();
                int count = 0;
                System.out.println(line);
                while (iterator.hasNext()) {
                    count++;
                    System.out.println("    " + count + ". " + iterator.next());
                }
                System.out.println(line);
            } else {
                list.add(input);
                System.out.println(line + "\n" + "     added: " + input + "\n" + line + "\n");
            }
        }
    }
}
