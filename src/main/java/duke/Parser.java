package duke;

import java.util.List;

public class Parser {
    protected boolean isEnd;
    protected TaskList tasks;
    private UserInput currentType = null;
    private Ui userInteract;

    /**
     * Returns a parser which interprets the user's command
     * This is a constructor of parser which takes in two arguments of user interact class and task list
     * Utilizes user interact class to return a response string and adds new tasks into the task list
     *
     * @param userInteract A class that return different types of duke response
     * @param tasks        List of tasks
     * @return A parser
     */
    public Parser(Ui userInteract, TaskList tasks) {
        this.userInteract = userInteract;
        this.tasks = tasks;
        this.isEnd = false;
    }

    enum UserInput {
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        BYE,
        LIST,
        FIND,
        HELP
    }

    /**
     * Returns a string array that splits the user's command by ' '.
     * Assigns the user's type of command corresponding to the enum type.
     *
     * @param userCommand User's command in a line scanned by Ui.
     * @return A string array that splits the user's command by ' '.
     */

    public String[] getDukeType(String userCommand) {
        String[] words = userCommand.split(" ");
        if (words[0].equals("deadline")) {
            this.currentType = UserInput.DEADLINE;
        } else if (words[0].equals("todo")) {
            this.currentType = UserInput.TODO;
        } else if (words[0].equals("event")) {
            this.currentType = UserInput.EVENT;
        } else if (words[0].equals("done")) {
            this.currentType = UserInput.DONE;
        } else if (words[0].equals("delete")) {
            this.currentType = UserInput.DELETE;
        } else if (words[0].equals("bye")) {
            this.currentType = UserInput.BYE;
        } else if (words[0].equals("list")) {
            this.currentType = UserInput.LIST;
        } else if (words[0].equals("find")) {
            this.currentType = UserInput.FIND;
        } else if (words[0].equals("help")) {
            this.currentType = UserInput.HELP;
        }
        return words;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    /**
     * Returns a string of response from duke
     * The type of response are fixed in Ui
     *
     * @param userCommand User's command scanned by Ui.
     * @return A string of response from duke.
     * @throws EmptyInputException Exceptions occur when the user's command cannot be understood
     * @throws NoResponseException Exceptions occur when the user's command is incomplete
     */

    public String parse(String userCommand) throws EmptyInputException, NoResponseException {
        String[] words = this.getDukeType(userCommand);
        String dukeOutput = "";
        assert this.currentType != null : "This command is not supported by Duke";
        switch (this.currentType) {
        case DEADLINE:
            if (words.length == 1) {
                throw new EmptyInputException("deadline");
            }

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
            dukeOutput = this.userInteract.showAdd(newDeadline, this.tasks);

            break;

        case TODO:
            if (words.length == 1) {
                throw new EmptyInputException("todo");
            }

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
            dukeOutput = this.userInteract.showAdd(newToDo, this.tasks);

            break;

        case EVENT:
            if (words.length == 1) {
                throw new EmptyInputException("event");
            }
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
            dukeOutput = this.userInteract.showAdd(newEvent, this.tasks);

            break;

        case FIND:
            String keyWord = words[1];
            List<Task> matchedTasks = this.tasks.findMatchingTask(keyWord);
            dukeOutput = this.userInteract.showFind(matchedTasks);

            break;

        case DONE:
            int number = Integer.parseInt(words[1]) - 1;
            dukeOutput = this.userInteract.showDone(number, this.tasks);
            break;

        case DELETE:
            int index = Integer.parseInt(words[1]) - 1;
            dukeOutput = this.userInteract.showDelete(index, this.tasks);
            break;

        case LIST:
            dukeOutput = this.userInteract.showList(this.tasks);
            break;

        case BYE:
            this.isEnd = true;
            dukeOutput = this.userInteract.showBye();
            break;

        case HELP:
            dukeOutput = this.userInteract.showHelp();
            break;

        default:
            throw new NoResponseException();
        }
        return dukeOutput;
    }
}
