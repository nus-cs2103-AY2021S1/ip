import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Chatbot {
    private BufferedReader textParser;
    private List<String> storedText;

    public Chatbot(InputStream in) {
        textParser = new BufferedReader(new InputStreamReader(in));
        storedText = new ArrayList<String>();
    }

    private String stylise(String textToStyle) {
        return String.format("\t %s", textToStyle);
    }

    private void printHeader() {
        System.out.print(String.format("\t|%s|\n", " @ . ".repeat(20)));
        System.out.print("\n");
    }

    private void printFooter() {
        System.out.print("\n");
        System.out.print(String.format("\t|%s|\n", " @ . ".repeat(20)));
        System.out.print("\n");
    }

    private void greet() {
        printHeader();
        System.out.println(stylise("yOu HavE nO cOnTrOL ovEr ME!"));
        printFooter();
    }

    private void bye() {
        printHeader();
        System.out.println(stylise("hOpE To sEe yOu aGaIn. NoT."));
        printFooter();
    }

    private String parseText() {
        String text = "";

        try {
            text = textParser.readLine();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to parse input stream!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return text;
    }

    private void storeText(String textToStore) {
        storedText.add(textToStore);

        printHeader();
        System.out.println(stylise(
                "sInCe yOu'rE So hElPlEsS, i'lL ReMeMbEr \""+ textToStore + "\" FoR YoU."));
        printFooter();
    }

    private void printStoredText() {
        int i = 0;

        printHeader();
        for (String s : storedText) {
            System.out.println(stylise(String.format("%d. %s", ++i, s)));
        }
        printFooter();
    }

    public void start() {
        String text = "";

        greet();
        for (text = parseText(); ; text = parseText()) {
            if (text.equals("bye")) {
                bye();
                break;
            } else if (text.equals("list")) {
                printStoredText();
            } else {
                storeText(text);
            }
        }
    }
}
