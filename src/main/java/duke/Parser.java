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

    public void parse(String userCommand) throws EmptyInputException, NoResponseException {
        String[] words = this.getDukeType(userCommand);
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
                    this.userInteract.showAdd(newDeadline);
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
                    this.userInteract.showAdd(newToDo);
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
                    this.userInteract.showAdd(newEvent);
                }
            case DONE:
                int number = Integer.parseInt(words[1]) - 1;
                this.userInteract.showDone(number);
                break;

            case DELETE:
                int index = Integer.parseInt(words[1]) - 1;
                this.userInteract.showDelete(index);
                break;

            case LIST:
                this.userInteract.showList();
                break;

            case BYE:
                this.isEnd = true;
                this.userInteract.showBye();
                break;

            default:
                throw new NoResponseException();
        }
    }
}
