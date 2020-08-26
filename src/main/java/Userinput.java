import java.util.ArrayList;

public class Userinput {
    private boolean terminate;
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected UserInput current = null;

    public Userinput() {
        this.terminate = false;
    }

    enum UserInput {
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        BYE,
        LIST,
    }

    String[] getDukeType(String input) {
        String[] words = input.split(" ");
        if (words[0].equals("deadline")) {
            this.current = UserInput.DEADLINE;
        } else if (words[0].equals("todo")) {
            this.current = UserInput.TODO;
        } else if (words[0].equals("event")) {
            this.current = UserInput.EVENT;
        } else if (words[0].equals("done")) {
            this.current = UserInput.DONE;
        } else if (words[0].equals("delete")) {
            this.current = UserInput.DELETE;
        } else if (words[0].equals("bye")) {
            this.current = UserInput.BYE;
        } else if (words[0].equals("list")) {
            this.current = UserInput.LIST;
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

            default:
                throw new NoResponseException();
        }

        return DukeOutput;
    }


    boolean getTerminate() {
        return this.terminate;
    }

}
