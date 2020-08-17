import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greetings = "Hello! I'm Duke\n     What can I do for you?";
    public static String farewell = "Bye. Hope to see you again soon!";
    public static String doneAlert = "Nice! I've marked this task as done:";
    public static String addTaskFrontAlert = "Got it. I've added this task:";
    public static String addTaskTailAlert = "Now you have %d tasks in the list.";
    public static String deleteTaskFrondAlert = "Noted. I've removed this task:";




    public static void main(String[] args) {
        List<Task> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        printAnswer("", greetings, "");
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();

            try {
                if (instruction.equals("bye")) {
                    printAnswer("", farewell, "");
                    break;
                } else if (instruction.length() >= 4 && instruction.substring(0, 4).equals("done")) {

                    if (instruction.length() <= 5) {
                        throw new DukeException("The description of a done message cannot be empty.");
                    }

                    Integer index = Integer.valueOf(instruction.substring(5)) - 1;

                    if (index + 1 > result.size()) {
                        throw new DukeException("The index of the task to be done is out of range.");
                    }

                    Task temp = result.get(index);
                    temp.markAsDone();
                    result.set(index, temp);
                    printAnswer(doneAlert, "   " + temp.toString(), "");
                } else if (instruction.equals("list")) {
                    printList("", result, "");
                } else if (instruction.length() >= 4 && instruction.substring(0, 4).equals("todo")) {

                    if (instruction.length() <= 5) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    result.add(new ToDo(instruction.substring(4)));
                    printAnswer(addTaskFrontAlert, "   " + result.get(result.size() - 1).toString(), String.format(addTaskTailAlert, result.size()));

                } else if (instruction.length() >= 8 && instruction.substring(0, 8).equals("deadline")) {

                    if (instruction.length() <= 9) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                    String[] temp = instruction.substring(8).split("/by ");

                    if (temp.length < 2) {
                        throw new DukeException("The date and time of the event cannot be empty.");
                    }

                    String desc = temp[0];
                    String date = temp[1];


                    result.add(new Deadlines(desc, date));
                    printAnswer(addTaskFrontAlert, "   " + result.get(result.size() - 1).toString(), String.format(addTaskTailAlert, result.size()));

                } else if (instruction.length() >= 5 && instruction.substring(0, 5).equals("event")) {

                    if (instruction.length() <= 6) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }

                    String[] temp = instruction.substring(5).split("/at ");

                    if (temp.length < 2) {
                        throw new DukeException("The date and time of the event cannot be empty.");
                    }

                    String desc = temp[0];
                    String date = temp[1];



                    result.add(new Events(desc, date));
                    printAnswer(addTaskFrontAlert, "   " + result.get(result.size() - 1).toString(), String.format(addTaskTailAlert, result.size()));

                } else if (instruction.length() >= 6 && instruction.substring(0, 6).equals("delete")) {
                    if (instruction.length() <= 7) {
                        throw new DukeException("The description of a delete message cannot be empty.");
                    }

                    Integer index = Integer.valueOf(instruction.substring(7)) - 1;

                    if (index + 1 > result.size()) {
                        throw new DukeException("The index of the task to be deleted is out of range.");
                    }

                    Task temp = result.get(index);
                    result.remove((int) index);
                    printAnswer(deleteTaskFrondAlert, "   " + temp.toString(), String.format(addTaskTailAlert, result.size()));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }


    }

    public static void printAnswer(String FrontGuidance, String answer, String TailGuidance) {
        String line = "____________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + line);
        if (FrontGuidance.length() != 0) {
            System.out.println(bigSpace + FrontGuidance);
        }
        System.out.println(bigSpace + answer);
        if (TailGuidance.length() != 0) {
            System.out.println(bigSpace + TailGuidance);
        }
        System.out.println(smallSpace + line + "\n");
    }

    public static void printList(String FrontGuidance, List<Task> result, String TailGuidance) {
        String Line = "____________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";
        String reminder = "Here are the tasks in your list:";

        System.out.println(smallSpace + Line);
        if (FrontGuidance.length() != 0) {
            System.out.println(bigSpace + FrontGuidance);
        }
        System.out.println(bigSpace + reminder);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(bigSpace + (i + 1) + ". " + result.get(i));
        }

        if (TailGuidance.length() != 0) {
            System.out.println(bigSpace + TailGuidance);
        }

        System.out.println(smallSpace + Line + "\n");
    }
}
