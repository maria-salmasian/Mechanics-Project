import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Converter {

    private Map<Integer, String> converter = new HashMap<>();
    int n = 8;

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


    public int getSprings(Integer[] binary){
        for(int i = n-1; i >=0; i--){
            if(binary[i] == 1){
                int key =  (int)Math.pow(2, n-i-1) ;
                if(key != 0){
                    StringBuffer val = new StringBuffer();
                    val.append("[");
                    for(int j = 0; j < key; i++){
                        val.append("[]");
                    }
                    val.append("]");
                    this.converter.put(key, String.valueOf(val));
                }

            }
        }

        StringBuffer system = new StringBuffer();
        system.append("[");
        converter.values().forEach(k -> system.append(k));
        system.append("]");



        SpringArray springArray = new SpringArray();
        Spring spring = springArray.equivalentSpring(String.valueOf(system));

        return spring.getK_stiffness();

    }


}