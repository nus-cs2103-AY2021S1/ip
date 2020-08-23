import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


//        LocalDateTime localDateTime = Duke.strToDate("2019-12-01 1800");
//        System.out.println(Duke.parseDateTime(localDateTime));

        FileHandler database = new FileHandler("data/Duke.txt");

        Duke.echo("Hello! I'm Duke\nWhat can I do for you?");

        Duke.checkCommands(sc, database);
    }

    public static void echo(String s) {
        String line = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", line, s, line);
    }

    public static void printList(List<Task> lst) {
        String s = "";
        for (int i = 1; i <= lst.size(); i++) {
            Task item = lst.get(i-1);
            s += String.format("%d.%s", i, item);
            s += (i == lst.size()) ? "" : "\n";
        }
        Duke.echo(s);
    }

    public static LocalDateTime strToDate(String by) {
        String[] arr = by.split("\\s+");
        LocalDate localDate;
        LocalDateTime localDateTime;
        try {
            if (arr.length == 1) {
                // Only date provided
                String date = arr[0].replace("/", "-");
                localDate = LocalDate.parse(date);
                localDateTime = localDate.atTime(0, 0);
            } else if (arr.length == 2 && arr[1].length() == 4) {
                // Date and time provided
                String date = arr[0].replace("/", "-");
                localDate = LocalDate.parse(date);
                String time = arr[1];
                int hours = Integer.parseInt(time.substring(0,2));
                int minutes = Integer.parseInt(time.substring(2,4));

                localDateTime = localDate.atTime(hours, minutes);
            } else {
                throw new InvalidCommandException();
            }

            return localDateTime;
        } catch (Exception e) {
            Duke.echo("☹ OOPS!!! Please enter date format as yyyy-mm-dd hhmm, e.g. 2019-12-01 1800");
            return null;
        }
    }

    public static String parseDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mma"));
    }

    public static void checkCommands(Scanner sc, FileHandler database) {
        loop: while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String[] arr = sentence.split("\\s+");
            String command = arr[0];
            String comText = "";
            for (int i = 1; i < arr.length; i++) {
                comText += arr[i] + " ";
            }
            comText = comText.trim();
            try {
                switch (command) {
                    case "todo":
                        if (arr.length == 1) {
                            throw new EmptyDescException();
                        } else {
                            Task todo = new Todo(comText, "0");
                            database.addTask(todo.getStringArr());
                            Duke.echo(String.format("Got it. I've added this task:" +
                                    "\n%s\nNow you have %d tasks in the list.", todo, database.getSize()));
                        }
                        break;
                    case "deadline":
                        int dIdx = comText.lastIndexOf("/by");
                        if (arr.length == 1) {
                            throw new EmptyDescException();
                        } else if (dIdx == -1 || comText.length() == (dIdx+3)){
                            throw new DeadlineException();
                        } else {
                            String desc = comText.substring(0, dIdx-1);
                            String by = comText.substring(dIdx+4, comText.length()).trim();
                            LocalDateTime deadlineDate = Duke.strToDate(by);
                            Task deadline = new Deadline(desc, "0", deadlineDate);
                            database.addTask(deadline.getStringArr());
                            Duke.echo(String.format("Got it. I've added this task:" +
                                    "\n%s\nNow you have %d tasks in the list.", deadline, database.getSize()));
                        }
                        break;
                    case "event":
                        int eIdx = comText.lastIndexOf("/at");
                        if (arr.length == 1) {
                            throw new EmptyDescException();
                        } else if (eIdx == -1 || comText.length() == (eIdx+3)){
                            throw new EventTaskException();
                        } else {
                            String desc = comText.substring(0, eIdx-1);
                            String at = comText.substring(eIdx+4, comText.length()).trim();
                            LocalDateTime eventDate = Duke.strToDate(at);
                            Task event = new Event(desc, "0", eventDate);
                            database.addTask(event.getStringArr());
                            Duke.echo(String.format("Got it. I've added this task:" +
                                    "\n%s\nNow you have %d tasks in the list.", event, database.getSize()));
                        }
                        break;
                    case "list":
                        List<Task> lst = database.getFileContents();
                        Duke.printList(lst);
                        break;
                    case "done":
                        if (arr.length != 2) {
                            throw new InvalidCommandException();
                        }
                        int doneNum = Integer.parseInt(arr[1]);

                        database.completeTask(doneNum);
                        Task doneItem = database.getTask(doneNum);
                        Duke.echo(String.format("Nice! I've marked this task as done:\n%s", doneItem));
                        break;
                    case "delete":
                        if (arr.length != 2) {
                            throw new InvalidCommandException();
                        }
                        int deleteNum = Integer.parseInt(arr[1]);

                        Task deleteItem = database.getTask(deleteNum);
                        database.deleteTask(deleteNum);
                        Duke.echo(String.format("Noted. I've removed this task:\n%s" +
                                    "\nNow you have %d tasks in the list.", deleteItem, database.getSize()));
                        break;
                    case "bye":
                        Duke.echo("Bye. Hope to see you again soon!");
                        break loop;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (Exception e) {
                Duke.echo(e.getMessage());
            }

        }
    }
}
