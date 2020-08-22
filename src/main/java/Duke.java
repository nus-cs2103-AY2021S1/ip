import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String inputLine = sc.nextLine().trim();

        while (!inputLine.equals("bye")) {
            String[] arr = inputLine.split(" ");
            if (inputLine.equals("list")) {
                displayList();
            }
            else if (arr.length == 2 && (arr[0].equals("done") || arr[0].equals("delete")) && isInteger(arr[1])) {
                int num = Integer.parseInt(arr[1]);
                try {
                    if (num < 1 || num > toDoList.size()) {
                        throw new InvalidRangeError();
                    }
                    if (arr[0].equals("done")) {
                        doneTask(num);
                    }
                    else if (arr[0].equals("delete")) {
                        deleteTask(num);
                    }
                }
                catch (DukeError e) {
                    hrTag();
                    System.out.println(e.getMessage());
                    hrTag();
                }
            }
            else {
                try {
                    switch (arr[0]) {
                        case "todo":
                            String todo = inputLine.substring(4).trim();
                            if (todo.equals("")) {
                                throw new EmptyTaskError();
                            } else {
                                addTask(new ToDo(todo));
                            }
                            break;
                        case "deadline":
                            int by = inputLine.indexOf("/by");
                            String deadline, byDate;
                            if (by == -1) {
                                deadline = inputLine.substring(8).trim();
                                byDate = "";
                            } else {
                                deadline = inputLine.substring(8, by).trim();
                                byDate = inputLine.substring(by + 3).trim();
                            }
                            if (deadline.equals("")) {
                                throw new EmptyTaskError();
                            } else if (byDate.equals("")) {
                                throw new EmptyDateError();
                            } else {
                                addTask(new Deadline(deadline, formatDateTime(byDate)));
                            }
                            break;
                        case "event":
                            int at = inputLine.indexOf("/at");
                            String event, atDate;
                            if (at == -1) {
                                event = inputLine.substring(5).trim();
                                atDate = "";
                            } else {
                                event = inputLine.substring(5, at).trim();
                                atDate = inputLine.substring(at + 3).trim();
                            }
                            if (event.equals("")) {
                                throw new EmptyTaskError();
                            } else if (atDate.equals("")) {
                                throw new EmptyDateError();
                            } else {
                                addTask(new Event(event, formatDateTime(atDate)));
                            }
                            break;
                        default:
                            throw new UnknownCommandError();
                    }
                }
                catch (DukeError e) {
                    hrTag();
                    System.out.println(e.getMessage());
                    hrTag();
                } catch (DateTimeParseException e) {
                    hrTag();
                    System.out.println("Invalid date entered!\nPlease enter the following format! dd/mm/yyyy HH:MM");
                    hrTag();
                }
            }
            inputLine = sc.nextLine();
        }
        goodbyeMessage();
        sc.close();
    }

    public static void hrTag() {
        System.out.println("____________________________________________________________");
    }

    private static LocalDateTime formatDateTime(String s) throws InvalidDateInputError {
        String[] dateAndTime = s.split(" ");

        if (dateAndTime.length != 2) {
            throw new InvalidDateInputError();
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String beforeDate = dateAndTime[0];
        String[] arrDate = beforeDate.split("/");
        if (arrDate[0].length() == 1) {
            beforeDate = "0".concat(beforeDate);
        }
        if (arrDate[1].length() == 1) {
            beforeDate = String.format("%s/0%s/%s", beforeDate.substring(0, 2),
                    arrDate[1], arrDate[2]);
        }

        String beforeTime = dateAndTime[1];
        if (beforeTime.length() == 4) {
            beforeTime = beforeTime.substring(0, 2) + ":" + beforeTime.substring(2);
        }
        String after = beforeDate + " " + beforeTime;
        return LocalDateTime.parse(after, dateFormatter);
    }

    private static void welcomeMessage() {
        hrTag();
        System.out.println("Hello! I am your mother!");
        System.out.println("What can I do for you son!");
        hrTag();
    }

    private static void addTask(Task task) {
        hrTag();
        toDoList.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        if (toDoList.size() == 1) {
            System.out.println(String.format(" Now you have %d task in the list.", toDoList.size()));
        } else {
            System.out.println(String.format(" Now you have %d tasks in the list.", toDoList.size()));
        }
        hrTag();
    }

    private static void displayList() {
        hrTag();
        if (toDoList.isEmpty()) {
            System.out.println("You have no tasks left! Good job my child!");
        } else {
            if (toDoList.size() == 1) {
                System.out.println(" Here is the task in your list:");
            } else {
                System.out.println(" Here are the tasks in your list:");
            }
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println(" " + (i+1) + "." + toDoList.get(i));
            }
        }
        hrTag();
    }

    private static void goodbyeMessage() {
        hrTag();
        System.out.println("Goodbye my child!");
        hrTag();
    }

    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void doneTask(int num) {
        hrTag();
        if (toDoList.get(num - 1).getIsDone()) {
            System.out.println(String.format("Sorry! You have already completed '%s'.",
                    toDoList.get(num - 1).getDescription()));
        } else {
            System.out.println(String.format("Great job son! I'll mark '%s' as done for you. ^^",
                    toDoList.get(num - 1).getDescription()));
            toDoList.get(num - 1).markAsDone();
        }
        hrTag();
    }

    private static void deleteTask(int num) {
        hrTag();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + toDoList.get(num - 1));
        toDoList.remove(num - 1);
        if (toDoList.size() == 0) {
            System.out.println(" Great job son! You're left with no more tasks!");
        }
        else if (toDoList.size() == 1) {
            System.out.println(" Now you have only 1 task in the list!");
        } else {
            System.out.println(String.format(" Now you have %d tasks in the list.", toDoList.size()));
        }
        hrTag();
    }
}
