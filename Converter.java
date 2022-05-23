import java.util.Arrays;

public class Converter {


    public Converter() {
    }

    public String validate(int[] binaryDigits) {
        if(binaryDigits.length != 8) {
            throw new RuntimeException("binary digit count should be 8");
        }
        Arrays.stream(binaryDigits).forEach(digit -> {
            if (digit != 0 && digit != 1) {
                throw new RuntimeException("input should be either 1 or 0");
            }
        });
        return "OK";
    }
}