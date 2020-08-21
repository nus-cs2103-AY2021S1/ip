import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greetings = "Hello! I'm Mr. Duke, your personal assistant\n     What can I do for you? : )";
    public static String farewell = "Bye. Hope to see you again soon!";
    public static String doneAlert = "Nice! I've marked this task as done:";
    public static String addTaskFrontAlert = "Got it. I've added this task for you:";
    public static String addTaskTailAlert = "Now you have %d tasks in the list.";
    public static String deleteTaskFrondAlert = "Noted. I've removed this task:";

    public static void main(String[] args) {
        List<Task> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        result = ListStorage.readFromFile();
        printAnswer("", greetings, "");
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            String command;
            EnumCommand enumCommand;
            Integer indexOfSplit = instruction.indexOf(' ');

            if (indexOfSplit == -1) {
                command = instruction;
            } else {
                command = instruction.substring(0, indexOfSplit);
            }

            try {
                if (command.equals("bye")) {
                    enumCommand = EnumCommand.BYE;
                } else if (command.equals("done")) {
                    enumCommand = EnumCommand.DONE;
                } else if (command.equals("delete")) {
                    enumCommand = EnumCommand.DELETE;
                } else if (command.equals("list")) {
                    enumCommand = EnumCommand.LIST;
                } else if (command.equals("todo")) {
                    enumCommand = EnumCommand.TODO;
                } else if (command.equals("deadline")) {
                    enumCommand = EnumCommand.DEADLINE;
                } else if (command.equals("event")) {
                    enumCommand = EnumCommand.EVENT;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means, there is a typo  :-(");
                }
                execute(enumCommand, instruction, result);
                ListStorage.storeToFile(result);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void execute(EnumCommand enumCommand, String instruction, List<Task> result) throws DukeException {

        switch (enumCommand) {
            case TODO:
                if (instruction.substring(4).strip().equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                result.add(new ToDo(instruction.substring(4).strip()));
                printAnswer(addTaskFrontAlert, "   " + result.get(result.size() - 1).toString(), String.format(addTaskTailAlert, result.size()));
                break;
            case DEADLINE:
                if (instruction.substring(8).strip().equals("")) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }

                String[] tempDeadline = instruction.substring(8).strip().split("/by ");

                if (tempDeadline.length < 2) {
                    throw new DukeException("The date and time of the deadline cannot be empty.");
                }

                String descDeadline = tempDeadline[0].strip();
                String dateDeadline = tempDeadline[1].strip();
                result.add(new Deadlines(descDeadline, dateDeadline));
                printAnswer(addTaskFrontAlert, "   " + result.get(result.size() - 1).toString(), String.format(addTaskTailAlert, result.size()));
                break;
            case EVENT:
                if (instruction.substring(5).strip().equals("")) {
                    throw new DukeException("The description of an event cannot be empty.");
                }

                String[] tempEvent = instruction.substring(5).strip().split("/at ");

                if (tempEvent.length < 2) {
                    throw new DukeException("The date and time of the event cannot be empty.");
                }

                String descEvent = tempEvent[0].strip(); // clear the white spaces at the front and at the back
                String dateEvent = tempEvent[1].strip(); // clear the white spaces at the front and at the back
                result.add(new Events(descEvent, dateEvent));
                printAnswer(addTaskFrontAlert, "   " + result.get(result.size() - 1).toString(), String.format(addTaskTailAlert, result.size()));
                break;
            case BYE:
                printAnswer("", farewell, "");
                System.exit(1);
            case DONE:

                if (instruction.substring(4).strip().equals("")) {
                    throw new DukeException("The description of a done message cannot be empty.");
                }

                Integer indexDone = Integer.valueOf(instruction.substring(5).strip()) - 1;

                if (indexDone + 1 > result.size()) {
                    throw new DukeException("The index of the task to be done is out of range.");
                }

                Task tempDone = result.get(indexDone);
                tempDone.markAsDone();
                result.set(indexDone, tempDone);
                printAnswer(doneAlert, "   " + tempDone.toString(), "");
                break;
            case DELETE:
                if (instruction.substring(6).strip().equals("")) {
                    throw new DukeException("The description of a delete message cannot be empty.");
                }

                Integer indexDelete = Integer.valueOf(instruction.substring(7).strip()) - 1;

                if (indexDelete + 1 > result.size()) {
                    throw new DukeException("The index of the task to be deleted is out of range.");
                }

                Task tempDelete = result.get(indexDelete);
                result.remove((int) indexDelete);
                printAnswer(deleteTaskFrondAlert, "   " + tempDelete.toString(), String.format(addTaskTailAlert, result.size()));
                break;
            case LIST:
                printList("", result, "");
        }

    }

    public static void printAnswer(String FrontGuidance, String answer, String TailGuidance) {
        String line = "___________________________________________________________________________________";
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
        String line = "___________________________________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";
        String reminder = ":) Here are all the tasks in your list:";

        System.out.println(smallSpace + line);
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

        System.out.println(smallSpace + line + "\n");
    }
}
