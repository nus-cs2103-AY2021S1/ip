import java.util.Scanner;

public class Duke {

    static String replyFormatter(String reply) {
        String partition = "__________________________";
        return String.format(partition + "\n%s\n" + partition, reply);
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
        System.out.println("ITS ME: \n" + logo);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String reply = sc.nextLine();
            if (reply.equals("bye")) {
                printReply(replyFormatter(reply));
                break;
            }
            printReply(replyFormatter(reply));
        }

    }
}
