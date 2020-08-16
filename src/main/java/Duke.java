import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke, como estas mi amigo?";
    private final static String ACTION_LIST = "list";
    private final static String TERMINATION = "bye";
    private final static String TERMINATION_MESSAGE = "Adios, amigos!";
    private static ArrayList<String> userInputsList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            switch (userInput) {
                case ACTION_LIST:
                    for (String s : userInputsList) {
                        System.out.println(s);
                    }
                    break;
                case TERMINATION:
                    System.out.println(TERMINATION_MESSAGE);
                    userInputsList.clear();
                    break;
                default:
                    System.out.println("added: " + userInput);
                    int position = userInputsList.size() + 1;
                    userInputsList.add(position + ". " + userInput);
            }
            if (userInput.equals(TERMINATION)) {
                sc.close();
                break;
            }
        }
    }
}
