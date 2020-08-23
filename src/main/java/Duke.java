import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static final String LINE = "    ____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String DELETE = "delete";
    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    public static List<Task> list = new ArrayList<>();
    public static final java.nio.file.Path path = java.nio.file.Paths.get(".", "data.txt");

    public static void readData() {
        boolean directoryExists = java.nio.file.Files.exists(path);
        try {
            if (directoryExists) {
                FileReader fileReader = new FileReader(String.valueOf(path));
                BufferedReader buffReader = new BufferedReader(fileReader);
                while (buffReader.ready()) {
                    String savedTask = buffReader.readLine();
                    String type = savedTask.substring(0, 1);
                    switch (type) {
                        case "T":
                            list.add(new ToDo(savedTask.substring(4).trim(), savedTask.substring(2, 3).equals("T")));
                            break;
                        case "D":
                            String deadlineDetails = savedTask.substring(4);
                            String[] deadlineArr = deadlineDetails.split("/by");
                            Deadline deadline = new Deadline(
                                    deadlineArr[0].trim(),
                                    savedTask.substring(2, 3).equals("T"),
                                    LocalDateTime.parse(deadlineArr[1].trim()));
                            list.add(deadline);
                            break;
                        case "E":
                            String eventDetails = savedTask.substring(4);
                            String[] eventArr = eventDetails.split("/at");
                            Event event = new Event(
                                    eventArr[0].trim(),
                                    savedTask.substring(2, 3).equals("T"),
                                    LocalDateTime.parse(eventArr[1].trim()));
                            list.add(event);
                            break;
                    }
                }
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void greet() {
        String greet = LINE + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + LINE;
        System.out.println(greet);
    }

    public static void bye() {
        String exit = LINE + "     Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(exit);
    }

    public static void addData(String data) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(String.valueOf(path), true), "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateData(String data, int number) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));
            String newData = "";
            String oldData;
            int lineNumber = 1;
            while (br.ready()) {
                oldData = br.readLine();
                if (lineNumber == number) {

                    newData += data.equals("") ? data : data + "\n";
                } else {
                    newData += oldData + "\n";
                }
                lineNumber++;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(path)));
            bw.write(newData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readData();
        greet();

        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            try {
                String command = sc.next();
                switch (command) {
                    case BYE:
                        bye();
                        break label;

                    case LIST:
                        Iterator<Task> iterator = list.iterator();
                        int count = 0;
                        System.out.println(LINE + "    Here are the tasks in your list:");
                        while (iterator.hasNext()) {
                            count++;
                            System.out.println("    " + count + ". " + iterator.next().toString());
                        }
                        System.out.println(LINE);
                        break;

                    case DONE: {
                        int taskNumber = sc.nextInt();
                        if (list.size() >= taskNumber) {
                            Task task = list.get(taskNumber - 1);
                            task.markAsDone();
                            updateData(task.store(), taskNumber);
                            System.out.println(LINE +
                                    "    Nice! I've marked this task as done:" + "\n" +
                                    "    " + task.toString() + "\n" +
                                    LINE);
                        } else {
                            throw new DukeException("Oops! No such task!");
                        }
                        break;
                    }

                    case DELETE: {
                        int taskNumber = sc.nextInt();
                        if (list.size() >= taskNumber && taskNumber > 0) {
                            Task task = list.remove(taskNumber - 1);
                            updateData("", taskNumber);
                            System.out.println(LINE +
                                    "    Noted. I've removed this task:" + "\n" +
                                    "      " + task.toString() + "\n" +
                                    String.format("    Now you have %d tasks in the list.\n", list.size()) +
                                    LINE);
                        } else {
                            throw new DukeException("Oops! No such task!");
                        }
                        break;
                    }

                    case TODO: {
                        String detail = sc.nextLine().trim();
                        if (detail.equals("")) {
                            throw new DukeException("Oops! Todo cannot be empty");
                        }
                        ToDo toDo = new ToDo(detail);
                        addData("T F " + detail + "\n");
                        list.add(toDo);
                        System.out.println(LINE +
                                "    Got it! I have added this todo to the list!" + "\n" +
                                "      " + toDo + "\n" +
                                String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                                LINE);
                        break;
                    }

                    case EVENT: {
                        String s = sc.nextLine();
                        if (s.trim().equals("")) {
                            throw new DukeException("Oops! Event cannot be empty");
                        }
                        String[] arr = s.split("/at");
                        if (arr.length == 1) {
                            throw new DukeException("Oops! You need to include both detail and time.");
                        }
                        String detail = arr[0].trim();
                        LocalDateTime date = LocalDateTime.parse(arr[1].trim(), df);
                        addData("E F " + detail +" /at " + date + "\n");
                        Event event = new Event(detail, date);
                        list.add(event);
                        System.out.println(LINE +
                                "    Got it! I have added this event to the list!" + "\n" +
                                "      " + event + "\n" +
                                String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                                LINE);
                        break;
                    }

                    case DEADLINE: {
                        String s = sc.nextLine();
                        if (s.trim().equals("")) {
                            throw new DukeException("Oops! Deadline cannot be empty");
                        }
                        String[] arr = s.split("/by");
                        if (arr.length == 1) {
                            throw new DukeException("Oops! You need to include both detail and time.");
                        }
                        String detail = arr[0].trim();
                        LocalDateTime date = LocalDateTime.parse(arr[1].trim(), df);
                        addData("D F " + detail + " /by " + date + "\n");
                        Deadline deadline = new Deadline(detail, date);
                        list.add(deadline);
                        System.out.println(LINE +
                                "    Got it! I have added this deadline to the list!" + "\n" +
                                "      " + deadline + "\n" +
                                String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                                LINE);
                        break;
                    }

                    default:
                        throw new DukeException("Oops! I'm sorry, but I don't know what that means");
                }
            } catch (DukeException e) {
                System.out.println(LINE + "    " + e.getMessage() + "\n" + LINE);
            }
        }
    }
}
