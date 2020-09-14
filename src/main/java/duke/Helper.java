package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Helper {
    public static String getHelp() {
        StringBuilder currLine = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("help.txt"));
            String nextLine = reader.readLine();
            while (nextLine != null) {
                currLine.append(nextLine);
                currLine.append("\n");
                nextLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        assert currLine.length() > 0 : "Curr line should have something";
        return currLine.toString();
    }
}
