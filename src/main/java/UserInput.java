import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UserInput {
    private boolean terminate;
    protected ArrayList<Task> tasks = new ArrayList<>();
    private User_Input current = null;

    public UserInput() {
        this.terminate = false;
    }
    enum User_Input {
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        BYE,
        LIST,
    }


    void handleFile() {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String previousTask = sc.nextLine();
                String[] words = previousTask.split(" @ ",0);
                for (int i =0; i < words.length; i ++) {
                    System.out.println(words[i]);
                }
                if (words[0].equals("D")) {
                    Deadline previousDeadline = new Deadline(words[2],words[3]);
                    if (words[1].equals("1")) {
                        previousDeadline.setIsDone();
                    }
                    this.tasks.add(previousDeadline);
                } else if (words[0].equals("T")) {
                    ToDo previousToDo = new ToDo(words[2]);
                    if (words[1].equals("1")) {
                        previousToDo.setIsDone();
                    }
                    this.tasks.add(previousToDo);
                } else if (words[0].equals("E")) {
                    Event previousEvent = new Event(words[2],words[3]);
                    if (words[1].equals("1")) {
                        previousEvent.setIsDone();
                    }
                    this.tasks.add(previousEvent);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    String[] getDukeType (String input) {
        String[] words = input.split(" ");
        if (words[0].equals("deadline")) {
            this.current = User_Input.DEADLINE;
        } else if (words[0].equals("todo")) {
            this.current = User_Input.TODO;
        } else if (words[0].equals("event")) {
            this.current = User_Input.EVENT;
        } else if (words[0].equals("done")) {
            this.current = User_Input.DONE;
        } else if (words[0].equals("delete")) {
            this.current = User_Input.DELETE;
        } else if (words[0].equals("bye")) {
            this.current = User_Input.BYE;
        } else if (words[0].equals("list")) {
            this.current = User_Input.LIST;
        }
        return words;
    }

    String getDukeResponse (String input) throws EmptyInputException, NoResponseException {
        String[] words = this.getDukeType(input);
        String DukeOutput = "";
        String taskGrammar = "task";
        if (this.tasks.size() > 0) {
            taskGrammar = "tasks";
        }
        switch (this.current) {
            case DEADLINE:
                if (words.length == 1) {
                    throw new EmptyInputException("deadline");
                } else {
                    String deadlineTask = "";
                    String deadlineDate = "";
                    int byPosition = 0;
                    for (int i = 1; i < words.length; i++) {
                        if (!words[i].equals("/by")) {
                            deadlineTask = deadlineTask + words[i] + " ";
                        } else {
                            byPosition = i;
                            break;
                        }
                    }

                    for (int k = byPosition + 1; k < words.length; k++) {
                        if (k != words.length - 1) {
                            deadlineDate = deadlineDate + words[k] + " ";
                        } else {
                            deadlineDate = deadlineDate + words[k];
                        }
                    }
                    deadlineTask = deadlineTask.trim();
                    Deadline newDeadline = new Deadline(deadlineTask, deadlineDate);
                    this.tasks.add(newDeadline);
                    DukeOutput = "____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + newDeadline.toString() + "\n" +
                            "     Now you have " + this.tasks.size() + " " + taskGrammar + " in the list.\n" +
                            "____________________________________________________________\n";
                }
                break;

            case TODO:
                if (words.length == 1) {
                    throw new EmptyInputException("todo");
                } else {
                    String todoTask = "";
                    for (int i = 1; i < words.length; i++) {
                        if (i != words.length -1) {
                            todoTask = todoTask + words[i] + " ";
                        } else {
                            todoTask = todoTask + words[i];
                        }
                    }
                    ToDo newToDo = new ToDo(todoTask);
                    this.tasks.add(newToDo);
                    DukeOutput = "____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + newToDo.toString() + "\n" +
                            "     Now you have " + this.tasks.size() + " " + taskGrammar + " in the list.\n" +
                            "____________________________________________________________\n";
                }
                break;

            case EVENT:
                if (words.length == 1) {
                    throw new EmptyInputException("event");
                } else {
                    String eventTask = "";
                    String eventDate = "";
                    int atPosition = 0;
                    for (int i = 1; i < words.length; i++) {
                        if (!words[i].equals("/at")) {
                            eventTask = eventTask + words[i] + " ";
                        } else {
                            atPosition = i;
                            break;
                        }
                    }

                    for (int k = atPosition + 1; k < words.length; k++) {
                        if (k != words.length - 1) {
                            eventDate = eventDate + words[k] + " ";
                        } else {
                            eventDate = eventDate + words[k];
                        }
                    }
                    Event newEvent = new Event(eventTask, eventDate);
                    this.tasks.add(newEvent);
                    DukeOutput = "____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + newEvent.toString() + "\n" +
                            "     Now you have " + this.tasks.size() + " " + taskGrammar + " in the list.\n" +
                            "____________________________________________________________\n";
                }
                break;
            case DONE :
                int number = Integer.parseInt(words[1]) - 1;
                DukeOutput = this.tasks.get(number).markAsDone();
                break;

            case DELETE:
                int index = Integer.parseInt(words[1]) - 1;
                Task removed = this.tasks.get(index);
                this.tasks.remove(index);
                DukeOutput = "____________________________________________________________\n" +
                        "     Noted. I've removed this task:\n" +
                        "       " + removed.toString() + "\n" +
                        "     Now you have "+ this.tasks.size() + " tasks in the list.\n" +
                        "____________________________________________________________";
                break;

            case LIST :
                DukeOutput = "____________________________________________________________\n" +
                        "Here are the tasks in your list:\n";

                for (int i = 0; i < this.tasks.size(); i++) {
                    String label = Integer.toString(1 + i);
                    DukeOutput = DukeOutput + label + ". " + this.tasks.get(i).toString() + "\n";
                }

                DukeOutput = DukeOutput + "____________________________________________________________";
                break;

            case BYE :
                this.terminate = true;
                DukeOutput = "____________________________________________________________\n" +
                        "       Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                break;

            default :
                throw new NoResponseException();
        }

        return DukeOutput;
    }


    boolean getTerminate() {
        return this.terminate;
    }

}
