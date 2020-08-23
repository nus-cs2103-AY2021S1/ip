import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  public static void main(String[] args) throws Exception {
    String logo =
        " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println(logo + "\nHello ! I'm Duke\nWhat can I do for you?\n");

    ArrayList<Task> dataArrayList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals("bye")) {
      input = scanner.next();
      // Switch case use for scalability
      switch (input) {
        case "list":
          System.out.println("Here are the tasks in your list: ");
          int count = 1;
          for (Task task : dataArrayList) {
            System.out.printf("%d.%s\n", count, task);
            count++;
          }
          break;
        case "done":
          int change = scanner.nextInt() - 1;
          Task task = dataArrayList.get(change);
          task.markAsDone();
          System.out.println("Nice! I've marked this task as done:");
          System.out.printf("%s\n", task);
          break;
        case "delete":
          int delete = scanner.nextInt() - 1;
          System.out.println("Noted. I've removed this task: ");
          System.out.printf("\t%s\n", dataArrayList.get(delete));
          dataArrayList.remove(delete);
          System.out.printf("Now you have %o tasks in the list\n", dataArrayList.size());
          break;
        default:
          if (!input.equals("bye")) {
            String addMessage = "Got it. I've added this task:";
            switch (input) {
              case "todo":
                String descToDo = scanner.nextLine().trim();
                if (descToDo.equals("")) {
                  throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                System.out.println(addMessage);
                dataArrayList.add(new Todo(descToDo));
                break;
              case "deadline":
                {
                  scanner.useDelimiter("/by");
                  String descDeadline = scanner.next().trim();
                  if (descDeadline.equals("")) {
                    throw new DukeException(
                        "☹ OOPS!!! The description of a deadline cannot be empty.");
                  }
                  scanner.skip("/by");
                  String by = scanner.nextLine().trim();
                  if (by.equals("")) {
                    throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
                  }

                  String[] inputDateTime = by.split(" ");
                  String[] date = inputDateTime[0].split("[/\\\\]|-");
                  LocalDate localDate = dateToLocalDate(date);

                  LocalTime localTime = LocalTime.of(0, 0);
                  if (inputDateTime.length > 1) {
                    int timeLength = inputDateTime[1].length();
                    if (timeLength == 4) {
                      int hour = Integer.parseInt(inputDateTime[1].substring(0, 2));
                      int minute = Integer.parseInt(inputDateTime[1].substring(2, 4));
                      localTime = LocalTime.of(hour, minute);
                    } else if (timeLength == 3) {
                      int hour = Integer.parseInt(String.valueOf(inputDateTime[1].charAt(0)));
                      int minute = Integer.parseInt(inputDateTime[1].substring(1, 3));
                      localTime = LocalTime.of(hour, minute);
                    } else {
                      throw new DukeException("Error with input time");
                    }
                  }

                  LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

                  System.out.println(addMessage);
                  dataArrayList.add(new Deadline(descDeadline, localDateTime));
                  break;
                }
              case "event":
                {
                  System.out.println(addMessage);
                  scanner.useDelimiter("/at");
                  String descEvent = scanner.next().trim();
                  if (descEvent.equals("")) {
                    throw new DukeException(
                        "☹ OOPS!!! The description of a event cannot be empty.");
                  }
                  scanner.skip("/at");
                  String at = scanner.nextLine().trim();
                  if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! The event date cannot be empty.");
                  }
                  String[] inputDateTime = at.split(" ");
                  String[] date = inputDateTime[0].split("[/\\\\]|-");
                  LocalDate localDate = dateToLocalDate(date);

                  LocalTime startLocalTime = LocalTime.of(0, 0);
                  LocalTime endLocalTime = LocalTime.of(23, 59);
                  if (inputDateTime.length > 1) {
                    String[] timeArr = inputDateTime[1].split("-");
                    if (timeArr.length > 1) {
                      endLocalTime = timeToLocalTime(timeArr[1]);
                    }
                    startLocalTime = timeToLocalTime(timeArr[0]);
                  }

                  LocalDateTime startLocalDateTime = LocalDateTime.of(localDate, startLocalTime);
                  LocalDateTime endLocalDateTime = LocalDateTime.of(localDate, endLocalTime);

                  dataArrayList.add(new Event(descEvent, startLocalDateTime, endLocalDateTime));
                  break;
                }
              default:
                throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            int arrListSize = dataArrayList.size();
            System.out.println("\t" + dataArrayList.get(arrListSize - 1));
            scanner.reset();
            System.out.printf("Now you have %o tasks in list.\n", arrListSize);
          }
          break;
      }
    }
    System.out.println("Bye. Hope to see you again soon!");
  }

  private static LocalDate dateToLocalDate(String[] date) throws DukeException {
    LocalDate localDate;
    if (date.length == 3) {
      int day = Integer.parseInt(date[0]);
      int month = Integer.parseInt(date[1]);
      int year = Integer.parseInt(date[2]);
      localDate = LocalDate.of(year, month, day);
    } else {
      throw new DukeException("Error with input date!");
    }
    return localDate;
  }

  private static LocalTime timeToLocalTime(String time) throws DukeException {
    int timeLength = time.length();
    LocalTime localTime;
    if (timeLength == 4) {
      int hour = Integer.parseInt(time.substring(0, 2));
      int minute = Integer.parseInt(time.substring(2, 4));
      localTime = LocalTime.of(hour, minute);
    } else if (timeLength == 3) {
      int hour = Integer.parseInt(String.valueOf(time.charAt(0)));
      int minute = Integer.parseInt(time.substring(1, 3));
      localTime = LocalTime.of(hour, minute);
    } else {
      throw new DukeException("Error with input time");
    }
    return localTime;
  }
}
