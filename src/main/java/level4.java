import java.util.*;

public class level4 {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bill ");
        System.out.println("What Can I do for you? ");
        System.out.println("------------------------------------------");

        String input = sc.nextLine();

        List list_Of_Content = new ArrayList();

        while (!input.equals("bye")) {

            int length = input.length();
            String[] isDone = input.split(" ");
            String firstChar = isDone[0];

//            String lastChar = isDone[length - 1];

            if (input.equals("list")) {

                System.out.println("Here are the tasks in your list: ");

                for (int i = 0; i < list_Of_Content.size(); i = i + 1) {
                    String counter = Integer.toString(i + 1) + ". ";
                    System.out.println(counter + list_Of_Content.get(i));
                }
                input = sc.nextLine();
            } else if (firstChar.equals("done")) {

                String lastChar = isDone[isDone.length - 1];
                int index = Integer.parseInt(lastChar);
                String current = String.valueOf(list_Of_Content.get(index - 1));
                String replaced = current.replace("[x]", "[✓]");
                list_Of_Content.set(index - 1, replaced);
                System.out.println("------------------------------------------");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(replaced);
                System.out.println("------------------------------------------");
                input = sc.nextLine();

            } else if (firstChar.equals("todo")) {

                String content_todo = "[T]" + "[x] " + input.substring(firstChar.length() + 1);
                list_Of_Content.add(content_todo);
                System.out.println("------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(content_todo);
                System.out.println("Now you have " + list_Of_Content.size() + " tasks in the list.");
                System.out.println("------------------------------------------");
                input = sc.nextLine();

            } else if (firstChar.equals("deadline")) {

                int index = input.indexOf("/by");
                String content_deadline = "[D]" + "[x] "
                        + input.substring(firstChar.length() + 1, index)
                        + " (by: "
                        + input.substring(index + 4)
                        + ")";
                list_Of_Content.add(content_deadline);
                System.out.println("------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(content_deadline);
                System.out.println("Now you have " + list_Of_Content.size() + " tasks in the list.");
                System.out.println("------------------------------------------");
                input = sc.nextLine();

            } else if (firstChar.equals("event")) {

                int index = input.indexOf("/at");
                String content_event = "[E]" + "[x] "
                        + input.substring(firstChar.length() + 1, index)
                        + " (at: "
                        + input.substring(index + 4)
                        + ")";
                list_Of_Content.add(content_event);
                System.out.println("------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(content_event);
                System.out.println("Now you have " + list_Of_Content.size() + " tasks in the list.");
                System.out.println("------------------------------------------");
                input = sc.nextLine();

            }

            else {
//                list_Of_Content.add("[x] " + input);
//                System.out.println("------------------------------------------");
//                System.out.println("added: " + input);
//                System.out.println("------------------------------------------");
//                input = sc.nextLine();
                String content_todo = "[T]" + "[x] " + input;
                list_Of_Content.add(content_todo);
                System.out.println("------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(content_todo);
                System.out.println("Now you have " + list_Of_Content.size() + " tasks in the list.");
                System.out.println("------------------------------------------");
                input = sc.nextLine();

            }
        }

        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");


    }

}
