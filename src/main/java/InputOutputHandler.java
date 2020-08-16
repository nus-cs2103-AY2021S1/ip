import java.util.Scanner;
import java.util.stream.IntStream;

public class InputOutputHandler {
    String separationLine = "     _____________________________________________________\n";
    String indentation = "      ";
    Scanner sc = new Scanner(System.in);
    String reply = sc.nextLine();

    String[] replyArr = reply.split(" ");

    String topPartOfBotReplyMessage = separationLine + indentation;
    String botPartOfBotReplyMessage = "\n" + separationLine;

    TaskManager taskManager = new TaskManager();

    private void handleUserInput()
    {
        if (replyArr[0].equals("list")) {
            String resultList = taskManager.toString();
            System.out.println(topPartOfBotReplyMessage + resultList + botPartOfBotReplyMessage);
        }
        else if (replyArr[0].equals("done")) {
            int taskIndex = Integer.parseInt(replyArr[1]) - 1;
            taskManager.markTaskAsDone(taskIndex);
            String botReply = "Wah finally. Wondering how long more I need to wait... \n";
            String taskDone = indentation + taskManager.getTask(taskIndex).toString();
            String fullReply = botReply + taskDone;
            System.out.println(topPartOfBotReplyMessage + fullReply + botPartOfBotReplyMessage);
        }
        else {
            taskManager.addTask(new Task(reply));
            System.out.println(topPartOfBotReplyMessage + "added: " + reply + botPartOfBotReplyMessage);
        }
    }

    public void handleInput()
    {
        while (!reply.equals("bye"))
        {
            handleUserInput();
            reply = sc.nextLine();
            replyArr = reply.split(" ");
        }
        String byeMessage = "That's all? Sure. See you again (hopefully LOL).";
        System.out.println(topPartOfBotReplyMessage + byeMessage + botPartOfBotReplyMessage);
        sc.close();
    }
}
