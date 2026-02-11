import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CalculatorGUI extends JFrame {

    ArrayList<String> history = new ArrayList<>();
    JTextField screen, sciNum, sciExp, unitVal;
    JComboBox<String> unitType;
    JTextArea histArea;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(350, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Basic", basicPanel());
        tabs.add("Sci", sciPanel());
        tabs.add("Units", unitPanel());
        tabs.add("Hist", histPanel());

        add(tabs);
        setVisible(true);
    }

    JPanel basicPanel() {
        JPanel p = new JPanel(new BorderLayout());
        screen = new JTextField();
        screen.setFont(new Font("Arial", 1, 20));
        p.add(screen, "North");

        JPanel grid = new JPanel(new GridLayout(4, 4));
        String[] keys = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+" };
        for (String k : keys) {
            JButton b = new JButton(k);
            b.addActionListener(e -> {
                String txt = e.getActionCommand();
                if ("=".equals(txt))
                    eval();
                else
                    screen.setText(screen.getText() + txt);
            });
            grid.add(b);
        }
        p.add(grid);
        return p;
    }

    void eval() {
        try {
            String txt = screen.getText();
            String op = "";
            if (txt.contains("+"))
                op = "\\+";
            else if (txt.contains("-"))
                op = "-";
            else if (txt.contains("*"))
                op = "\\*";
            else if (txt.contains("/"))
                op = "/";

            String[] parts = txt.split(op);
            BigDecimal n1 = new BigDecimal(parts[0]);
            BigDecimal n2 = new BigDecimal(parts[1]);
            String rawOp = op.replace("\\", "");

            BigDecimal res = Core.calc(n1, n2, rawOp);
            screen.setText(res.toString());
            updateHist(n1 + rawOp + n2 + "=" + res);
        } catch (Exception e) {
            screen.setText("Err");
        }
    }

    JPanel sciPanel() {
        JPanel p = new JPanel(new GridLayout(4, 1));
        sciNum = new JTextField("Num");
        sciExp = new JTextField("Exp (for pow)");
        JButton b1 = new JButton("Sqrt");
        JButton b2 = new JButton("Pow");

        b1.addActionListener(e -> {
            BigDecimal res = Core.sqrt(new BigDecimal(sciNum.getText()));
            JOptionPane.showMessageDialog(this, res);
            updateHist("sqrt " + res);
        });
        b2.addActionListener(e -> {
            BigDecimal res = Core.pow(new BigDecimal(sciNum.getText()), Integer.parseInt(sciExp.getText()));
            JOptionPane.showMessageDialog(this, res);
            updateHist("pow " + res);
        });

        p.add(sciNum);
        p.add(sciExp);
        p.add(b1);
        p.add(b2);
        return p;
    }

    JPanel unitPanel() {
        JPanel p = new JPanel(new GridLayout(3, 1));
        unitVal = new JTextField();
        unitType = new JComboBox<>(new String[] { "C->F", "C->K", "USD->INR", "USD->EUR" });
        JButton b = new JButton("Convert");

        b.addActionListener(e -> {
            BigDecimal v = new BigDecimal(unitVal.getText());
            int idx = unitType.getSelectedIndex();
            BigDecimal r = BigDecimal.ZERO;
            if (idx == 0)
                r = Core.convertCtoF(v);
            if (idx == 1)
                r = Core.convertCtoK(v);
            if (idx == 2)
                r = Core.convertUsdToInr(v);
            if (idx == 3)
                r = Core.convertUsdToEur(v);
            JOptionPane.showMessageDialog(this, r);
            updateHist("Conv " + r);
        });
        p.add(unitVal);
        p.add(unitType);
        p.add(b);
        return p;
    }

    JPanel histPanel() {
        JPanel p = new JPanel(new BorderLayout());
        histArea = new JTextArea();
        p.add(new JScrollPane(histArea));
        return p;
    }

    void updateHist(String s) {
        history.add(s);
        histArea.setText("");
        for (String h : history)
            histArea.append(h + "\n");
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
