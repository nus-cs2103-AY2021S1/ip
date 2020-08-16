import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    // Collection of user's tasks
    static List<Task> taskItems = new ArrayList<>();

    static String replyFormatter(String reply) {
        String partition = "__________________________";
        return String.format(partition + "\n%s\n" + partition, reply);
    }

    static String listFormatter(List<Task> ls) {
        String formattedListString = "";
        for (int i = 0; i < ls.size(); i ++) {
            formattedListString+= String.format("%d. %s\n", i + 1, ls.get(i));
        }
        return formattedListString;
    }

    static void printReply(String reply) {
        System.out.println(reply);
    }

    public static void main(String[] args) {
        String logo =
                "8888888 888b    888 88888888888 8888888b.  888     888 888888b.    .d88888b. 88888888888 \n" +
                        "  888   8888b   888     888     888   Y88b 888     888 888  \"88b  d88P\" \"Y88b    888     \n" +
                        "  888   88888b  888     888     888    888 888     888 888  .88P  888     888    888     \n" +
                        "  888   888Y88b 888     888     888   d88P 888     888 8888888K.  888     888    888     \n" +
                        "  888   888 Y88b888     888     8888888P\"  888     888 888  \"Y88b 888     888    888     \n" +
                        "  888   888  Y88888     888     888 T88b   888     888 888    888 888     888    888     \n" +
                        "  888   888   Y8888     888     888  T88b  Y88b. .d88P 888   d88P Y88b. .d88P    888     \n" +
                        "8888888 888    Y888     888     888   T88b  \"Y88888P\"  8888888P\"   \"Y88888P\"     888";
       printReply(replyFormatter("ITS ME: \n" + logo));
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String reply = sc.nextLine();
            if (reply.equals("bye")) {
                printReply(replyFormatter(reply));
                break;
            } else if (reply.startsWith("done ")) {
                Task task = taskItems.get(Integer.parseInt(reply.substring(5,reply.length())) - 1);
                task.markDone();
                printReply(replyFormatter("Nice! I've marked this task as done:\n" +  task.toString()));
            } else if (reply.equals("list")) { // Show all in list
                printReply(replyFormatter(listFormatter(taskItems)));
            } else { // Add to list
                taskItems.add(new Task(reply));
                printReply(replyFormatter(String.format("added: %s", reply)));
            }
        }

    }
}
