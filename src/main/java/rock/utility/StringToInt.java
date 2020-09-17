package rock.utility;

import rock.exception.RockException;

public class StringToInt {
    /**
     * Convert string to index
     * @param value String
     * @return c index
     * @throws RockException invalid index
     */
    public static int stringToIndex(String value, int limit) throws RockException {
        int c = 0;
        for (int i = 0; i < value.length(); ++i) {
            if ('0' > value.charAt(i) || value.charAt(i) > '9') {
                throw new RockException("Index should be an integer.");
            }
            c = c * 10 + value.charAt(i) - '0';
        }
        if (0 > c || c > limit) {
            throw new RockException("Index is out of range.");
        }
        return c;
    }
}
