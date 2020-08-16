import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String separationLine = "     _____________________________________________________\n";
        String indentation = "      ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(separationLine + logo);

        System.out.print(indentation + "Ah another dude trying to be productive. Sure. \n");
        System.out.print(indentation + "Let's see how long it takes. \n");
        System.out.print(indentation + "So, tell me, what do you want, sweetie? \n" + separationLine);

        Scanner sc = new Scanner(System.in);
        String reply = sc.nextLine();
        String topPartOfBotReplyMessage = separationLine + indentation;
        String botPartOfBotReplyMessage = "\n" + separationLine;

        TaskManager taskManager = new TaskManager();

        while (!reply.equals("bye"))
        {
            // System.out.println(topPartOfBotReplyMessage + reply + botPartOfBotReplyMessage);
            if (!reply.equals("list"))
            {
                taskManager.AddTask(new Task(reply));
                System.out.println(topPartOfBotReplyMessage + "added: " + reply + botPartOfBotReplyMessage);
                reply = sc.nextLine();
            }
            else
            {
                String resultList = taskManager.toString().replaceAll("(?m)^\\s+$", "");

                System.out.println(topPartOfBotReplyMessage + resultList + botPartOfBotReplyMessage);
                reply = sc.nextLine();
            }
        }
        String byeMessage = "That's all? Sure. See you again (hopefully LOL).";
        System.out.println(topPartOfBotReplyMessage + byeMessage + botPartOfBotReplyMessage);
        sc.close();
    }
}
