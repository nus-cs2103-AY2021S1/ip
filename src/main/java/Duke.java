import java.util.*;


public class Duke {
    List<String> toDoLst;
    HashMap<Integer, Boolean> toDoItemToStatus;
    HashMap<Integer, String> toDoItemToType;

    Duke() {
        toDoLst = new ArrayList<>();
        toDoItemToStatus = new HashMap<>();
        toDoItemToType = new HashMap<>();
    }

    public List<String> getToDoLst() {
        return toDoLst;
    }

    public int getToDoLstSize() {
        return toDoLst.size();
    }

    // needed?
    public HashMap<Integer, Boolean> getToDoItemToStatus() {
        return toDoItemToStatus;
    }

    // needed?
    public HashMap<Integer, String> getToDoItemToType() {
        return toDoItemToType;
    }

    public String getToDoItemDescription(int i) {
        return String.format("[%s][%s] %s", toDoItemToType.get(i), toDoItemToStatus.get(i) ? "✓" : "✗", toDoLst.get(i));
    }

    public void setToDoItemStatus(int i, boolean bool) {
        toDoItemToStatus.put(i, bool);
    }

    public void setToDoItemToType(int i, String type) {
        toDoItemToType.put(i, Character.toString(type.charAt(0)).toUpperCase());
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        Scanner scanner = new Scanner(System.in);
        String line = "";

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    for (int i = 0; i < duke.getToDoLst().size(); i++) {
                        System.out.println(String.format("%d.%s", i + 1, duke.getToDoItemDescription(i)));
                    }
                } else if (line.split(" ")[0].equals("done")) {
                    String[] lineData = line.split(" ");
                    int i = Integer.parseInt(lineData[1]) - 1;

                    duke.setToDoItemStatus(i, true);

                    System.out.println("Nice! I've marked this task as done:");

                    System.out.println(duke.getToDoItemDescription(i));
                } else {
                    String[] lineData = line.split(" ");
                    String type = lineData[0];

                    duke.setToDoItemStatus(duke.getToDoLstSize(), false);
                    duke.setToDoItemToType(duke.getToDoLstSize(), type);

                    if (type.equals("todo")) {
                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length));
                    } else if (type.equals("deadline")) {
                        for (int i = 0; i < lineData.length; i++) {
                            if (lineData[i].equals("/by")) {
                                lineData[i] = "(by:";
                                break;
                            }
                        }

                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length)) + ")";
                    } else {
                        for (int i = 0; i < lineData.length; i++) {
                            if (lineData[i].equals("/at")) {
                                lineData[i] = "(at:";
                                break;
                            }
                        }

                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length)) + ")";
                    }

                    duke.getToDoLst().add(line);

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(duke.getToDoItemDescription(duke.getToDoLstSize() - 1));
                    System.out.println(String.format("Now you have %d %s in the list.", duke.getToDoLstSize(), duke.getToDoLstSize() > 1 ? "tasks" : "task"));
                }
            }

            line = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}