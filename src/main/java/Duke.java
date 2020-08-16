import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void chatbot() {
        Scanner sc = new Scanner(System.in);
        List<Task> lst = new ArrayList<>();
        String listCommand = "list";
        String doneCommand = "done";

        String indent = "    ";
        String upperLine = indent + "___________________________________________________" + "\n";
        String lowerLine =  indent + "___________________________________________________" +"\n";
        String greetings = indent + "Hello! I'm Duke" + "\n"
                + indent + "What can I do for you?" +"\n";
        System.out.println(upperLine + greetings + lowerLine);

        while (sc.hasNext()) {

            String input = sc.nextLine();
            String outputIndent = indent + " ";
            String output = input + "\n";
            String addedMessage = outputIndent + "added: ";
            String markedMessage = "Nice! I've marked this task as done:";

            if (input.equals("bye")) {
                String exit = outputIndent + "Bye. Hope to see you again soon!" + "\n";
                System.out.println(upperLine + exit + lowerLine);
                break;
            }

            if (input.equals(listCommand)) {
                String concat = listTask(lst);
                System.out.println(upperLine + concat + lowerLine);
            }else if (input.contains(doneCommand)) {
                String[] token = input.split(" ");
                String taskNumberString = token[1];
                int inputNum = Integer.parseInt(taskNumberString);

                if (inputNum > lst.size()) {
                    System.out.println("Make sure you keyed in the correct task number!");
                } else {
                    int taskNum = inputNum - 1;
                    Task task = lst.get(taskNum);
                    task.markAsDone();

                    System.out.println(upperLine + outputIndent + markedMessage +"\n" +
                            outputIndent + " " + "[" + task.getStatusIcon() + "]" + " " + task.getDescription()
                            + lowerLine);

                }

            } else {

                lst.add(new Task(output));
                System.out.println(upperLine + addedMessage
                        + input +"\n" + lowerLine);

            }
        }
    }

    public static String listTask(List<Task> lst) {
        String outputIndent = "    ";
        StringBuilder concat = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            int taskNumber = i + 1;
            String s = outputIndent + taskNumber + "." + "[" + task.getStatusIcon() + "]" + " " + task.getDescription();
            concat.append(s);
        }
        return concat.toString();

    }


    public static void main(String[] args) {
        chatbot();
    }
}
