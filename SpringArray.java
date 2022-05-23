import java.util.*;
import java.util.stream.Collectors;

public class SpringArray {
    private final String OPEN_BRACKET = "[";
    private final String CLOSE_BRACKET = "]";
    private final String OPEN_BRACE = "{";
    private final String CLOSE_BRACE = "}";
    private final List<String> OPENERS = List.of(OPEN_BRACE, OPEN_BRACKET);
    private final List<String> CLOSERS = List.of(CLOSE_BRACE, CLOSE_BRACKET);

    private int index = 0;
    private List<Spring> springArray;

    public Spring equivalentSpring(String springExpr) {
        springArray = new ArrayList<>();
        Map<Integer, Integer> indexPairs = indexes(springExpr);
        return solve(springExpr, indexPairs);
    }

    public Spring equivalentSpring(String springExpr, Spring[] springs) {
        springArray = Arrays.stream(springs).collect(Collectors.toList());
        Map<Integer, Integer> indexPairs = indexes(springExpr);
        return solve(springExpr, indexPairs);
    }

    public Map<Integer, Integer> indexes(String springExpression) {
        Map<Integer, Integer> indexes = new LinkedHashMap<>();
        for (int i = 0; i < springExpression.length(); i++) {
            if (OPENERS.contains(Character.toString(springExpression.charAt(i)))) {
                indexes.put(i, getClosingIndex(springExpression, i));
            }
        }
        return indexes;

    }

    public int getClosingIndex(String springExpression, int index) {
        String current = Character.toString(springExpression.charAt(index));
        String opener = current.equals(OPEN_BRACE) ? OPEN_BRACE : OPEN_BRACKET;
        String closer = current.equals(OPEN_BRACE) ? CLOSE_BRACE : CLOSE_BRACKET;
        Queue<String> queue = new ArrayDeque<>();
        queue.add(current);
        for (int i = index + 1; i < springExpression.length(); i++) {
            String currentChar = Character.toString(springExpression.charAt(i));

            if (currentChar.equals(closer)) {
                queue.poll();
            } else if (currentChar.equals(opener)) {
                queue.add(currentChar);
            }
            if (queue.size() == 0) {
                return i;
            }
        }
        throw new RuntimeException("Invalid Spring Expression");
    }

    private Spring solve(String springExpression, Map<Integer, Integer> indexes) {
        int start = 0;
        int end = springExpression.length();
        if (start == end - 1) {
            if (springArray.size() != 0) {
                return springArray.get(index++);
            } else {
                return new Spring();
            }
        }
        List<Spring> subSprings;
        Map<Integer, Integer> filteredIndexes = indexes
                .entrySet().stream()
                .filter(x -> x.getKey() > start && x.getValue() < end)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        Set<Integer> index = filteredIndexes.keySet();
        List<Integer> subIndexes = new ArrayList<>();
        return null;
    }
}



