import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Spring spring = new Spring(2);
        SpringArray springArray = new SpringArray();

        int[] digits = {1, 1, 0, 0, 1, 1, 0, 1};
        Converter converter = new Converter();
        System.out.println(converter.validate(digits));


        String expr = "{[{}{}][]}";
        String expr2 = "{[{[][]}{}][{}]}";

        Map<Integer, Integer> indexes = springArray.indexes(expr);
        indexes.forEach((k,v) -> System.out.println(k + " | " + v));


        System.out.println();

        Map<Integer, Integer> indexes2= springArray.indexes(expr2);
        indexes2.forEach((k,v) -> System.out.println(k + " | " + v));

    }
}
