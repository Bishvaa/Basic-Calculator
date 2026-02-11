import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class CalculatorApp {

    static ArrayList<String> history = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Simple Java Calculator");
        while (true) {
            System.out.println("\n1. Math  2. Science  3. Units  4. History  5. Exit");
            System.out.print("Choose: ");
            String choice = scan.next();

            if (choice.equals("5"))
                break;
            else if (choice.equals("4")) {
                System.out.println("\n--- History ---");
                for (String s : history)
                    System.out.println(s);
            } else if (choice.equals("1"))
                doMath();
            else if (choice.equals("2"))
                doSci();
            else if (choice.equals("3"))
                doUnits();
        }
    }

    static void doMath() {
        try {
            System.out.print("Num 1: ");
            BigDecimal n1 = new BigDecimal(scan.next());
            System.out.print("Op (+ - * /): ");
            String op = scan.next();
            System.out.print("Num 2: ");
            BigDecimal n2 = new BigDecimal(scan.next());

            BigDecimal res = Core.calc(n1, n2, op);
            if (res == null)
                System.out.println("Error");
            else {
                System.out.println("Res: " + res);
                history.add(n1 + " " + op + " " + n2 + " = " + res);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
    }

    static void doSci() {
        try {
            System.out.print("1. Sqrt  2. Pow: ");
            String c = scan.next();
            if (c.equals("1")) {
                System.out.print("Num: ");
                BigDecimal n = new BigDecimal(scan.next());
                BigDecimal res = Core.sqrt(n);
                System.out.println("Res: " + res);
                history.add("sqrt(" + n + ")=" + res);
            } else {
                System.out.print("Base: ");
                BigDecimal b = new BigDecimal(scan.next());
                System.out.print("Exp: ");
                int e = scan.nextInt();
                BigDecimal res = Core.pow(b, e);
                System.out.println("Res: " + res);
                history.add(b + "^" + e + "=" + res);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
    }

    static void doUnits() {
        try {
            System.out.println("1. C->F  2. C->K  3. USD->INR  4. USD->EUR");
            String c = scan.next();
            System.out.print("Value: ");
            BigDecimal v = new BigDecimal(scan.next());
            BigDecimal res = null;
            if (c.equals("1"))
                res = Core.convertCtoF(v);
            if (c.equals("2"))
                res = Core.convertCtoK(v);
            if (c.equals("3"))
                res = Core.convertUsdToInr(v);
            if (c.equals("4"))
                res = Core.convertUsdToEur(v);

            System.out.println("Res: " + res);
            history.add("Conv type " + c + " val " + v + " -> " + res);
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
    }
}
