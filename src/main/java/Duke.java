import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String catLogo = "        /\\_____/\\\n" +
                "       /  o   o  \\\n" +
                "      ( ==  ^  == )\n" +
                "       )         (\n" +
                "      (           )\n" +
                "     ( (  )   (  ) )\n" +
                "    (__(__)___(__)__)";
        System.out.println(catLogo);

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm NEKOBOT!!");
        System.out.println("    What can I do for you :>");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while(!command.equals("bye")) {
            if(command.equals("list")){
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list!!");
                Integer index = 1;
                for(Task task: list) {
                    System.out.println("    " + index.toString() + ". " + task);
                    index++;
                }
                System.out.println("    ____________________________________________________________");
            } else if (command.substring(0, 4).equals("done")){
                Integer index = Integer.parseInt(command.substring(5));
                Task task = list.get(index - 1).markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Yay! I've marked this task as done :3");
                System.out.println("       " + task);
                System.out.println("    ____________________________________________________________");

            } else {
                String typeOfTask = command.split(" ")[0];
                if (typeOfTask.equals("todo")) {
                    list.add(new Todo(command.substring(5)));
                } else if (typeOfTask.equals("event")) {
                    String taskName = command.substring(command.indexOf("event ") + 6, command.indexOf(" /at"));
                    String taskDate = command.substring(command.indexOf("/at ") + 4);
                    list.add(new Event(taskName, taskDate));
                } else if (typeOfTask.equals("deadline")) {
                    String taskName = command.substring(command.indexOf("deadline ") + 9, command.indexOf(" /by"));
                    String taskDate = command.substring(command.indexOf("/by ") + 4);
                    list.add(new Deadline(taskName, taskDate));
                }

                System.out.println("    ____________________________________________________________");
                System.out.println("     Okies! I've added this task~");
                System.out.println("       " + list.get(list.size() - 1));
                System.out.println("     Now you have " + list.size() + " tasks in the list uwu");
                System.out.println("    ____________________________________________________________");
            }
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye~ Hope to see you again soon ;w;");
        System.out.println("    ____________________________________________________________");
    }
}
