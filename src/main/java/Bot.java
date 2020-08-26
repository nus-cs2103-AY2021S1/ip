import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Bot {
    final String FILE_PATH = "./assets/userData.txt";
    private String name;
    private ArrayList<Task> taskList;

    public Bot(String name) {
        this.name = name;
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Start the bot's interaction with user.
     */
    public void init() {
        greet();
        try{
            loadFileContents(FILE_PATH);
        } catch (IOException e) {
            System.out.println(responseWrapper("IO Exception"));
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    scanner.close();
                    String farewell = responseWrapper("Have a good day, mate!");
                    System.out.println(farewell);
                    return;
                }
                String response = parseUserInput(input);
                System.out.println(response);
            } catch (InvalidCommandException | InvalidInputException e) {
                System.out.println(responseWrapper(e.getMessage()));
            }
        }
    }

    private void saveUserData(String FILE_PATH) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : this.taskList) {
            fw.write(task.toFileFormat());
        }
        fw.close();
    }

    private Task lineToObj(String line) {
        String[] words = line.split(" \\| ");
        char firstChar = line.charAt(0);
        switch (firstChar) {
            case 'D':
                int len = words[3].length();
                return new Deadline(words[2], words[3].toString().substring(0,len - 1), words[1].equals("1"));
            case 'E':
                int len2 = words[3].length();
                return new Event(words[2], words[3].toString().substring(0, len2 - 1), words[1].equals("1"));
            default:
                return new Todo(words[2], words[1].equals("1"));
        }
    }

    private void loadFileContents(String FILE_PATH) throws IOException {
        File f = new File(FILE_PATH);
        File dir = new File(f.toPath().getParent().toString());
        if(!dir.exists()) {
            dir.mkdir();
        }
        if (!f.exists()) {
            String response = "Hey, you new user eh? I've got your profile created. No worries.";
            System.out.println(responseWrapper(response));
            f.createNewFile();
        }
        String content = Files.readString(Paths.get(FILE_PATH), StandardCharsets.US_ASCII);
        String[] items = content.split("\n");
        for (String item : items) {
            this.taskList.add(lineToObj(item));
        }
    }

    private void greet() {
        String greeting = String.format("Good day, I'm %s. What can I do for you?", name);
        System.out.println(responseWrapper(greeting));
    }

    private String responseWrapper(String str) {
        final String TEXT_LINE = "________________________________________________________________";
        return TEXT_LINE + "\n    " + str + "\n" + TEXT_LINE;
    }

    private String parseUserInput(String input) throws InvalidCommandException,
            InvalidInputException {
        if (input.length() == 0) {
            return responseWrapper("Please input something");
        }
        String[] words = input.split(" ");
        try {
            Command cmd = Command.valueOf(words[0].toUpperCase());
            switch (cmd) {
                case LIST:
                    return cmdList();
                case TODO:
                    return cmdTodo(words);
                case DEADLINE:
                    return cmdDeadline(words);
                case EVENT:
                    return cmdEvent(words);
                case DONE:
                    return cmdDone(words);
                case DELETE:
                    return cmdDelete(words);
                default:
                    return "Valid cmd given however it is not supported yet";
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("What's that again? I can't understand.");
        } catch (InvalidInputException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    private String cmdTodo(String[] words) throws InvalidInputException {
        try {
            StringBuilder name = new StringBuilder();
            for (int i = 1; i < words.length; i++) {
                name.append(words[i]);
                if (i != words.length - 1) {
                    name.append(" ");
                }
            }
            if (name.length() == 0) {
                throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
            }
            Todo newTodo = new Todo(name.toString());
            this.taskList.add(newTodo);
            saveUserData(FILE_PATH);
            return responseWrapper("Got it. I've added this task:\n    " +
                    newTodo + "\n    " +
                    "Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdDeadline(String[] words) throws InvalidInputException {
        try {
            StringBuilder name = new StringBuilder();
            StringBuilder deadline = new StringBuilder();
            Boolean deadlineWords = false;
            for (int i = 1; i < words.length; i++) {
                if (words[i].equals("/by")) {
                    name.deleteCharAt(name.length() - 1);
                    deadlineWords = true;
                    i++;
                }
                if (deadlineWords) {
                    deadline.append(words[i]);
                    deadline.append(" ");
                } else {
                    name.append(words[i]);
                    name.append(" ");
                }
            }
            if (name.length() == 0 || deadline.length() == 0) {
                throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
            }
            deadline.deleteCharAt(deadline.length() - 1);
            Deadline newTask = new Deadline(name.toString(), deadline.toString());
            this.taskList.add(newTask);
            saveUserData(FILE_PATH);
            return responseWrapper("Got it. I've added this task:\n    " +
                    newTask + "\n    " +
                    "Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdEvent(String[] words) throws InvalidInputException {
        try {
            StringBuilder name = new StringBuilder();
            StringBuilder deadline = new StringBuilder();
            Boolean deadlineWords = false;
            for (int i = 1; i < words.length; i++) {
                if (words[i].equals("/at")) {
                    name.deleteCharAt(name.length() - 1);
                    deadlineWords = true;
                    i++;
                }
                if (deadlineWords) {
                    deadline.append(words[i]);
                    deadline.append(" ");
                } else {
                    name.append(words[i]);
                    name.append(" ");
                }
            }
            if (name.length() == 0 || deadline.length() == 0) {
                throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
            }
            deadline.deleteCharAt(deadline.length() - 1);
            Event newTask = new Event(name.toString(), deadline.toString());
            this.taskList.add(newTask);
            saveUserData(FILE_PATH);
            return responseWrapper("Got it. I've added this task:\n    " +
                    newTask + "\n    " +
                    "Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdList() {
        int index = 0;
        StringBuilder string = new StringBuilder("Here are the tasks in your list:\n    ");
        for (Task item : taskList) {
            index++;
            string.append(index).append(".").append(item).append("\n    ");
        }
        if (index == 0) {
            return responseWrapper("Nothing in the list");
        }
        string.delete(string.length() - 5, string.length());
        return responseWrapper(string.toString());
    }

    private String cmdDone(String[] words) throws InvalidInputException {
        try {
            if (words.length != 2) {
                return responseWrapper("Please input 1 argument.");
            }
            Task task = this.taskList.get(Integer.parseInt(words[1]) - 1);
            task.markAsDone();
            saveUserData(FILE_PATH);
            return responseWrapper("Nice! I've marked this task as done: \n    " +
                    task + "\n");
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdDelete(String[] words) throws InvalidInputException {
        try {
            if (words.length != 2) {
                throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
            }
            Task task = this.taskList.get(Integer.parseInt(words[1]) - 1);
            this.taskList.remove(Integer.parseInt(words[1]) - 1);
            saveUserData(FILE_PATH);
            return responseWrapper("Noted. I've removed this task: \n    " +
                    task + "\n    " +
                    "Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }
}
