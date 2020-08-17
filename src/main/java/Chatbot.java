import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Chatbot {
    private BufferedReader textParser;

    Chatbot(InputStream in) {
        textParser = new BufferedReader(new InputStreamReader(in));
    }

    private String stylise(String textToStyle) {
        String styledText = String.format("\t!%s!\n", " @ . ".repeat(20));
        styledText += String.format("\t %s \n", textToStyle);
        styledText += String.format("\t!%s!\n", " @ . ".repeat(20));

        return styledText;
    }

    private void greet() {
        System.out.print(stylise("yOu HavE nO cOnTrOL ovEr ME!"));
    }

    private String parseCommand() {
        String command = "";

        try {
            command = textParser.readLine();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to parse input stream!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return command;
    }

    public void start() {
        String command = "";

        greet();
        for (command = parseCommand(); ; command = parseCommand()) {
            if (command.equals("bye")) {
                break;
            }

            System.out.println(stylise(command));
        }
    }
}
