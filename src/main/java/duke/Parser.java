package duke;

public class Parser {
    private User_Input currentType = null;
    private Ui userInteract;
    protected TaskList tasks;
    public boolean isEnd;


    public Parser(Ui userInteract, TaskList tasks) {
        this.userInteract = userInteract;
        this.tasks = tasks;
        this.isEnd = false;
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

    /**
     * Returns a string array that splits the user's command by ' '.
     * Assigns the user's type of command corresponding to the enum type.
     *
     * @param userCommand User's command in a line scanned by Ui.
     * @return a string array that splits the user's command by ' '.
     */

    public String[] getDukeType(String userCommand) {
        String[] words = userCommand.split(" ");
        if (words[0].equals("deadline")) {
            this.currentType = User_Input.DEADLINE;
        } else if (words[0].equals("todo")) {
            this.currentType = User_Input.TODO;
        } else if (words[0].equals("event")) {
            this.currentType = User_Input.EVENT;
        } else if (words[0].equals("done")) {
            this.currentType = User_Input.DONE;
        } else if (words[0].equals("delete")) {
            this.currentType = User_Input.DELETE;
        } else if (words[0].equals("bye")) {
            this.currentType = User_Input.BYE;
        } else if (words[0].equals("list")) {
            this.currentType = User_Input.LIST;
        }
        return words;
    }

    /**
     * Returns a string of response from duke.
     * The type of response are fixed in Ui.
     *
     * @param userCommand User's command scanned by Ui.
     * @return a string of response from duke.
     * @throws EmptyInputException,NoResponseException exceptions occur when the user's command cannot be responded or lack of input.
     */

    public String parse(String userCommand) throws EmptyInputException, NoResponseException {
        String[] words = this.getDukeType(userCommand);
        String DukeOutput = "";
        switch (this.currentType) {
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
                DukeOutput = this.userInteract.showAdd(newDeadline);
            }
            break;

        case TODO:
            if (words.length == 1) {
                throw new EmptyInputException("todo");
            } else {
                String todoTask = "";
                for (int i = 1; i < words.length; i++) {
                    if (i != words.length - 1) {
                        todoTask = todoTask + words[i] + " ";
                    } else {
                        todoTask = todoTask + words[i];
                    }
                }
                ToDo newToDo = new ToDo(todoTask);
                this.tasks.add(newToDo);
                DukeOutput = this.userInteract.showAdd(newToDo);
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
                DukeOutput = this.userInteract.showAdd(newEvent);
            }
            break;

        case DONE:
            int number = Integer.parseInt(words[1]) - 1;
            DukeOutput = this.userInteract.showDone(number);
            break;

        case DELETE:
            int index = Integer.parseInt(words[1]) - 1;
            DukeOutput = this.userInteract.showDelete(index);
            break;

        case LIST:
            DukeOutput = this.userInteract.showList();
            break;

        case BYE:
            this.isEnd = true;
            DukeOutput = this.userInteract.showBye();
            break;

        default:
            throw new NoResponseException();
        }
        return DukeOutput;
    }
}
