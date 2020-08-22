package parser;

import exceptions.InvalidCommandException;
import utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Enum flags: flags that can be parsed by task parser.
 */
public enum Flag {
    BY("/by"),
    AT("/at"),
    NONFLAG(""),
    ENDFLAG("/end");

    private final String value;

    /**
     * Constructor
     * @param value return a flag
     */
    Flag(String value) {
        this.value = value;
    }

    /**
     * Check whether a string has a form of a flag
     * @param s the input string
     * @return boolean answer
     */
    public static boolean hasFormOfFlag(String s) {
        return s.length() > 0 && s.charAt(0) == '/';
    }

    /**
     * From a token, get the flag corresponding to the token
     * @param token: raw token
     * @return optional of flag. If cannot find any token matched, return Optional.empty()
     */
    private static Optional<Flag> getFlags(String token) {
        if (token.length() == 0) {
            return  Optional.empty();
        }
        for (Flag flag: Flag.values()) {
            if (token.equals(flag.value )) {
                return Optional.of(flag);
            }
        }
        return Optional.empty();
    }

    /**
     * Parse a list of tokens to a list of pair<flag, value>: <"/by", "2020-06-06">, ....
     * @param tokens a list of tokens
     * @return a map of pair(flag, value)
     * @throws InvalidCommandException when that command is invalid
     */
    public static Map<Flag, String> parseFlags(String[] tokens) throws InvalidCommandException {
        StringBuilder sb = new StringBuilder();
        Flag currentFlag = NONFLAG;
        Map<Flag, String> result = new HashMap<>();
        for (String token : tokens) {
            if (hasFormOfFlag(token)) {
                Optional<Flag> flag = getFlags(token);
                if (flag.isEmpty()) {
                    throw new InvalidCommandException("Invalid flag");
                }
                if (sb.length() > 0) {
                    result.put(currentFlag, sb.toString());
                }
                currentFlag = flag.get();
                sb = new StringBuilder();
            } else {
                if (sb.length() > 0) sb.append(" ");
                sb.append(token);
            }
        }
        if (sb.length() > 0) {
            result.put(currentFlag, sb.toString());
        }
        return result;
    }
}
