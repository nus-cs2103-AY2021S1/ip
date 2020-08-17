import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void greeting() {
        String greetingMsg = "\n___________________________________________________________"
                + "\n Hello! I'm DukeBT. |°‿°|"
                + "\n What can I do for you?"
                + "\n\n **Type 'help' to see what I can do |^_^|"
                + "\n___________________________________________________________\n";
        System.out.println(greetingMsg);
    }

    public static ArrayList<String> addTask(String inputMsg, ArrayList<String> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n added: " + inputMsg
                + "\n___________________________________________________________\n";

        System.out.println(outputMsg);

        tasks.add(inputMsg);
        return tasks;
    }

    public static void getAllTasks(ArrayList<String> tasks) {
        String outputMsg = "";

        if (tasks.isEmpty()) {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n Task list is empty, please try to add some tasks first."
                    + "\n___________________________________________________________\n";
        } else {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n You have " + tasks.size() + " tasks in total."
                    + "\n Here they are:";
            for (int i = 0; i < tasks.size(); i++) {
                outputMsg += "\n      " + (i + 1) + ". " + tasks.get(i);
            }
            outputMsg += "\n___________________________________________________________\n";
        }

        System.out.println(outputMsg);
    }

    public static void help() {
        String commandList = "**************************************************************"
                + "\n ┗( ⊙.⊙ )┛ "
                + "\n These are all the commands you may use:"
                + "\n     1.list -- show all tasks"
                + "\n     2.help -- show all commands"
                + "\n     3.bye -- exit the chatbot"
                + "\n**************************************************************\n";
        System.out.println(commandList);
    }

    public static void bye() {
        String byeMsg = "\n___________________________________________________________"
                + "\n Bye. Hope to see you again soon! |^_^|┛"
                + "\n___________________________________________________________\n";

        System.out.println(byeMsg);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        greeting();
        String inputMsg = sc.nextLine();
        while (!inputMsg.equals("bye")) {
            if (inputMsg.equals("list")) {
                getAllTasks(tasks);
            } else if (inputMsg.equals("help")) {
                help();
            } else {
                tasks = addTask(inputMsg, tasks);
            }
            inputMsg = sc.nextLine();
        }
        bye();
    }
}
