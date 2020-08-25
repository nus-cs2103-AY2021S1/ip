package main.java.duke;

import java.util.ArrayList;

public class Parser {
    public Parser(){}

    /**
     * Take in the String input and split into the 3 parts, namely
     * the command, the title and extra_descriptions.
     * @param input The input from the users.
     * @return a String array that contains different components of the input.
     */
    public String[] interpretInput(String input) {
        ArrayList<String> list = new ArrayList<>();
        int spaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/");
        int infoIndex = input.indexOf(" ", slashIndex);
        if (spaceIndex == -1) {
            list.add(input);
        } else if (slashIndex == -1) {
            list.add(input.substring(0,spaceIndex));
            list.add(input.substring(spaceIndex+1));
        } else {
            list.add(input.substring(0,spaceIndex));
            list.add(input.substring(spaceIndex+1,slashIndex));
            list.add(input.substring(infoIndex+1));
        }
        System.out.println(list.toString());
        return list.toArray(new String[0]);
    }
}
