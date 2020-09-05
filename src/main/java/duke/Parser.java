package duke;

import java.util.ArrayList;

public class Parser {
    public Parser(){}

    /**
     * Take in the String input and split into the 3 parts, namely
     * the command, the title and extra_descriptions.
     * @param input The input from the users.
     * @return a String array that contains different components of the input.
     */
    public String[] splitIntoComponents(String input) {
        ArrayList<String> inputComponents = new ArrayList<>();
        int spaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/");
        int infoIndex = input.indexOf(" ", slashIndex);
        if (spaceIndex == -1) {
            inputComponents.add(input);
        } else if (slashIndex == -1) {
            inputComponents.add(input.substring(0, spaceIndex));
            inputComponents.add(input.substring(spaceIndex + 1));
        } else {
            inputComponents.add(input.substring(0, spaceIndex));
            inputComponents.add(input.substring(spaceIndex + 1, slashIndex));
            inputComponents.add(input.substring(infoIndex + 1));
        }
        return inputComponents.toArray(new String[0]);
    }
}
