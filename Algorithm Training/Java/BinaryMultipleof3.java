import java.util.regex.Pattern;

public class BinaryMultipleof3 {
    public static Pattern multipleOf3() {
        return Pattern.compile("^(0*(1(01*0)*1)*)*$");
    }
}
