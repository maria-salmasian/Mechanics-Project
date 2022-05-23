import java.util.ArrayList;
import java.util.List;

public class SpringArray {
    private static List<Spring> springArrayList = new ArrayList<>();
    private static List<Integer> size = new ArrayList<>();

    public static Spring equivalentSpring(String stringExp, Spring[] springs) {
        if (stringExp.length() < 2) {
            throw new RuntimeException("Invalid Expression");
        }

        if (stringExp.equals("[]")) {
            return new Spring(springs[0].getK_stiffness());
        } else if (stringExp.equals("{}")) {
            return null;
        }


        int charec = 0;
        int spring = 0;

        do {
            char now = stringExp.charAt(charec);
            boolean brace = now == '{';
            boolean bracket = now == '[';

            boolean isOpening = brace || bracket;
            if (isOpening) {
                char next = stringExp.charAt(charec + 1);

                if (now == '[' && next == ']') {
                    springArrayList.add(springs[spring++]);
                    size.add(size.get(size.size() - 1) + 1);
                    charec++;
                } else {
                    size.add(0);
                }
            } else {
                int new_size = size.get(size.size() - 1);
                while (new_size > 1) {
                    Spring first = springArrayList.get(size.size() - 1);
                    Spring second = springArrayList.get(size.size() - 1);
                    if (now == ']') {
                        springArrayList.add(first.inParallel(first, second));
                    } else if (now == '}') {
                        springArrayList.add(first.inSeries(first, second));
                    } else {
                        throw new RuntimeException("Bad expression!");
                    }
                    new_size--;
                }
                if (!size.isEmpty()) {
                    size.add(size.get(size.size() - 1) + 1);
                }
            }
            charec++;
        }
        while (charec < stringExp.length());

        if (!springArrayList.isEmpty()) {
            return springArrayList.get(size.size() - 1);
        } else {
            return null;
        }
    }

    public static Spring equivalentSpring(String stringExpr) {
        Spring[] springs = new Spring[stringExpr.length() / 2];
        for (int i = 0; i < springs.length; i++) {
            springs[i] = new Spring();
        }
        return equivalentSpring(stringExpr, springs);
    }

}