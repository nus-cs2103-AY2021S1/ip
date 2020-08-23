import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {
        String logo = "Duke Melvin";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");

        List<Task> ls = TextToArrayListConverter.readFile();
        String line = "";
        Scanner in = new Scanner(System.in);

        while (!line.equals("bye") && in.hasNextLine()) {
            line = in.nextLine();
            String firstArg = line.split(" ")[0];
            try {
                switch (line) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        ArrayListToTextConverter.convertArrayListToText(ls);
                        break;
                    case "list":
                        for (int x = 0; x < ls.size(); x++) {
                            System.out.println(x + 1 + ":" + ls.get(x).toString());
                        }
                        break;
                    default:
                        switch (firstArg) {
                            case "done":
                                String[] doneCommandArray = line.split(" ");
                                if (doneCommandArray.length < 2) {
                                    throw new InvalidDoneCommandException();
                                } else {
                                    int itemToBeMarkedAsDone = Integer.parseInt(doneCommandArray[1]);
                                    if (itemToBeMarkedAsDone > ls.size() || itemToBeMarkedAsDone <= 0) {
                                        throw new InvalidDoneCommandException();
                                    } else {
                                        ls.get(itemToBeMarkedAsDone - 1).markAsDone();
                                        ArrayListToTextConverter.convertArrayListToText(ls);
                                    }
                                }
                                break;
                            case "delete":
                                String[] deleteCommandArray = line.split(" ");
                                if (deleteCommandArray.length < 2) {
                                    throw new InvalidDeleteCommandException();
                                } else {
                                    int itemToBeDeleted = Integer.parseInt(deleteCommandArray[1]);
                                    if (itemToBeDeleted > ls.size() || itemToBeDeleted <= 0) {
                                        throw new InvalidDeleteCommandException();
                                    } else {
                                        Task item = ls.get(itemToBeDeleted - 1);
                                        ls.remove(itemToBeDeleted - 1);
                                        System.out.println("Noted. I've removed this task:");
                                        System.out.println(item.toString());
                                        System.out.format("Now you have %d tasks in the list\n", ls.size());

                                        ArrayListToTextConverter.convertArrayListToText(ls);

                                    }
                                }


                                break;

                            case "todo":
                                if (line.trim().length() <= 4) {
                                    throw new EmptyToDoException();
                                } else {
                                    String todoString = line.substring(5);
                                    ToDo todo = new ToDo(todoString);
                                    ls.add(todo);
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println(todo.toString());
                                    System.out.format("Now you have %d tasks in the list\n", ls.size());
                                    ArrayListToTextConverter.convertArrayListToText(ls);

                                    break;
                                }


                            case "deadline":
                                String [] deadlineArr = line.split("/",2);
                                String by = deadlineArr[1].substring(deadlineArr[1].indexOf("by") + 3);
                                String deadlineString = deadlineArr[0].substring(9);
                                LocalDate localDeadlineDate = TimeFormatter.localDate(by);
                                Deadline deadline = new Deadline(deadlineString, localDeadlineDate);
                                ls.add(deadline);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(deadline.toString());
                                System.out.format("Now you have %d tasks in the list\n", ls.size());
                                ArrayListToTextConverter.convertArrayListToText(ls);

                                break;

                            case "event":
                                String[] eventArr =line.split("/",2);
                                String at = eventArr[1].substring(eventArr[1].indexOf("at") + 3);
                                String eventString = eventArr[0].substring(6);
                                LocalDate localEventDate = TimeFormatter.localDate(at);
                                Event event = new Event(eventString, localEventDate);
                                ls.add(event);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(event.toString());
                                System.out.format("Now you have %d tasks in the list\n", ls.size());
                                ArrayListToTextConverter.convertArrayListToText(ls);

                                break;

                            default:
                                throw new InvalidCommandException();
                        }

                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());


            }

        }
    }

}



