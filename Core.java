import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Core {

    public static BigDecimal calc(BigDecimal a, BigDecimal b, String op) {
        switch (op) {
            case "+":
                return a.add(b);
            case "-":
                return a.subtract(b);
            case "*":
                return a.multiply(b);
            case "/":
                return b.signum() == 0 ? null : a.divide(b, 10, RoundingMode.HALF_UP);
            default:
                return null;
        }
    }

    public static BigDecimal sqrt(BigDecimal a) {
        if (a.signum() < 0)
            return null;
        return a.sqrt(new MathContext(10));
    }

    public static BigDecimal pow(BigDecimal a, int b) {
        return a.pow(b);
    }

    public static BigDecimal convertCtoF(BigDecimal c) {
        return c.multiply(new BigDecimal("1.8")).add(new BigDecimal("32"));
    }

    public static BigDecimal convertCtoK(BigDecimal c) {
        return c.add(new BigDecimal("273.15"));
    }

    public static BigDecimal convertUsdToInr(BigDecimal usd) {
        return usd.multiply(new BigDecimal("83.50"));
    }

    public static BigDecimal convertUsdToEur(BigDecimal usd) {
        return usd.multiply(new BigDecimal("0.92"));
    }
}
