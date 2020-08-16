import java.util.*;

public class Duke {

    public static final String LINE = "    ____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();

        String greet = LINE + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + LINE;

        String exit = LINE + "     Bye. Hope to see you again soon!\n" + LINE;

        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            try {
                String input = sc.next();
                switch (input) {
                    case BYE:
                        System.out.println(exit);
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

                    case DONE:
                        int taskNumber = sc.nextInt();
                        if (list.size() >= taskNumber) {
                            Task task = list.get(taskNumber - 1);
                            task.markAsDone();
                            System.out.println(LINE +
                                    "    Nice! I've marked this task as done:" + "\n" +
                                    "    " + task.toString() + "\n" +
                                    LINE);
                        } else {
                            throw new DukeException("Oops! No such task!");
                        }
                        break;

                    case TODO:
                        String detail = sc.nextLine();
                        if (detail.trim().equals("")) {
                            throw new DukeException("Oops! Todo cannot be empty");
                        }
                        ToDo toDo = new ToDo(detail);
                        list.add(toDo);
                        System.out.println(LINE +
                                "    Got it! I have added this todo to the list!" + "\n" +
                                "      " + toDo + "\n" +
                                String.format("    Now you have %d tasks in the list.", list.size()) + "\n" +
                                LINE);
                        break;

                    case EVENT: {
                        String s = sc.nextLine();
                        if (s.trim().equals("")) {
                            throw new DukeException("Oops! Event cannot be empty");
                        }
                        String[] arr = s.split("/at");
                        if (arr.length == 1) {
                            throw new DukeException("Oops! You need to include both detail and time.");
                        }
                        Event event = new Event(arr[0], arr[1]);
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
                        Deadline deadline = new Deadline(arr[0], arr[1]);
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
