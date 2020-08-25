package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The parser handler. Converts a <code>String</code> line input
 * into a main command and a hashmap of the parameters.
 */
public class Parser {
    /**
     * Parse the main command from the input.
     *
     * @param input the input as a <code>String</code> line
     * @return the main command as a <code>String</code>
     */
    public static String parseMainCommand(String input) {
        return input.split(" ")[0].strip();
    }

    /**
     * Parse all the parameters from a given <code>String</code> line.
     *
     * @param input the input as a <code>String</code> line
     * @return the hash map containing all the parameters
     */
    public static HashMap<String, String> parseParameters(String input) {
        input = input.replace(parseMainCommand(input), "").strip();

        HashMap<String, String> parameters = new HashMap<>();

        if (input.equals("")) {
            return parameters;
        }

        List<String> split = Arrays.asList(input.split("/"));
        parameters.put("argument", split.get(0).strip());

        for (String pair : split.subList(1, split.size())) {
            String key = pair.split(" ")[0];
            parameters.put(key, pair.replace(key, "").strip());
        }

        return parameters;
    }
}
