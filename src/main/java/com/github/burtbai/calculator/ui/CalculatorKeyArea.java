package com.github.burtbai.calculator.ui;

import com.github.burtbai.calculator.icons.CalculatorIcons;
import com.github.burtbai.calculator.message.CalculatorBundle;
import com.intellij.icons.AllIcons;
import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author burtbai
 */
public class CalculatorKeyArea extends JPanel {
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints(); // 实例化该对象用来对组件进行管理
    private final GridBagLayout gridBagLayout = new GridBagLayout(); //实例化布局对象, 网格袋布局

    // 表达式, 输入行
    private final JTextField TF_expression = new JTextField("");
    // 结果, 输出行
    private final JTextField TF_result = new TextFieldWithIcon(AllIcons.Vcs.Equal);

    // 第1行
    private final JButton B_copy = new JButton(AllIcons.Actions.Copy); // 复制输入输出
    private final JButton B_clear = new JButton(AllIcons.Actions.GC); // 清空输入输出
    private final JButton B_backspace = new JButton(CalculatorIcons.Backspace); // 退格，清除输入的最后一位
    // 第2行
    private final JButton B_random = new JButton("Random"); // 随机变量, U{0,10²}
    private final JButton B_pow = new JButton("x^n"); // x的n次方
    private final JButton B_sqrt = new JButton("2√x"); // 开根号
    private final JButton B_mod = new JButton("Mod"); // 取余
    // 第3行
    private final JButton B_leftBracket = new JButton("("); // 混合运算的左括号
    private final JButton B_rightBracket = new JButton(")"); // 混合运算的右括号
    private final JButton B_factorial = new JButton("n!"); // 阶乘
    private final JButton B_div = new JButton("/"); // 除号
    //第4行
    private final JButton B_7 = new JButton("7"); // 数字7
    private final JButton B_8 = new JButton("8"); // 数字8
    private final JButton B_9 = new JButton("9"); // 数字9
    private final JButton B_mul = new JButton("*"); // 乘号
    //第5行
    private final JButton B_4 = new JButton("4"); // 数字4
    private final JButton B_5 = new JButton("5"); // 数字5
    private final JButton B_6 = new JButton("6"); // 数字6
    private final JButton B_dec = new JButton("-"); // 减号
    //第6行
    private final JButton B_1 = new JButton("1"); // 数字1
    private final JButton B_2 = new JButton("2"); // 数字2
    private final JButton B_3 = new JButton("3"); // 数字3
    private final JButton B_add = new JButton("+"); // 加号
    //第7行
    private final JButton B_0 = new JButton("0"); // 数字0
    private final JButton B_point = new JButton("."); // 小数点
    private final JButton B_equal = new JButton("="); // 等号

    public CalculatorKeyArea() {
        Font font = this.getFont();

        this.setLayout(gridBagLayout); // 窗体对象设置为GridBagLayout布局
        gridBagConstraints.fill = GridBagConstraints.BOTH; // 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况

        int row = 0;
        int maxCol = 4;

        setControl(TF_expression, 0, row, maxCol, 2);
        TF_expression.setHorizontalAlignment(JTextField.RIGHT);
        TF_expression.setFont(new Font(font.getName(), font.getStyle(), font.getSize() * 2));

        row = 2;
        setControl(TF_result, 0, row, maxCol, 2);
        TF_result.setHorizontalAlignment(JTextField.RIGHT);
        TF_result.setFont(new Font(font.getName(), font.getStyle(), font.getSize() * 2));

        // 按键区第1行
        row = 4;
        setControl(B_copy, 1, row, 1, 1);
        B_copy.setActionCommand("Copy");
        B_copy.setToolTipText(CalculatorBundle.message("ui.CopyButton.ToolTipText"));

        setControl(B_clear, 2, row, 1, 1);
        B_clear.setActionCommand("C");
        B_clear.setToolTipText(CalculatorBundle.message("ui.ClearButton.ToolTipText"));

        setControl(B_backspace, 3, row, 1, 1);
        B_backspace.setActionCommand("backspace");

        // 第2行
        ++row;
        setControl(B_random, 0, row, 1, 1);
        B_random.setToolTipText(CalculatorBundle.message("ui.RandomButton.ToolTipText"));
        setControl(B_pow, 1, row, 1, 1);
        setControl(B_sqrt, 2, row, 1, 1);
        setControl(B_mod, 3, row, 1, 1);
        // 第3行
        ++row;
        setControl(B_leftBracket, 0, row, 1, 1);
        setControl(B_rightBracket, 1, row, 1, 1);
        setControl(B_factorial, 2, row, 1, 1);
        setControl(B_div, 3, row, 1, 1);
        // 第4行
        ++row;
        setControl(B_7, 0, row, 1, 1);
        setControl(B_8, 1, row, 1, 1);
        setControl(B_9, 2, row, 1, 1);
        setControl(B_mul, 3, row, 1, 1);
        // 第5行
        ++row;
        setControl(B_4, 0, row, 1, 1);
        setControl(B_5, 1, row, 1, 1);
        setControl(B_6, 2, row, 1, 1);
        setControl(B_dec, 3, row, 1, 1);
        // 第6行
        ++row;
        setControl(B_1, 0, row, 1, 1);
        setControl(B_2, 1, row, 1, 1);
        setControl(B_3, 2, row, 1, 1);
        setControl(B_add, 3, row, 1, 1);
        // 第7行
        ++row;
        setControl(B_0, 1, row, 1, 1);
        setControl(B_point, 2, row, 1, 1);
        setControl(B_equal, 3, row, 1, 1);

        setupControls();
    }

    /**
     * 设置控件位置及大小
     */
    private void setControl(JComponent e, int x, int y, int width, int height) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = height;
        gridBagLayout.setConstraints(e, gridBagConstraints);
        this.add(e);
    }

    private void setupControls() {
        MyActionListener myActionListener = new MyActionListener();
        B_copy.addActionListener(myActionListener);
        B_clear.addActionListener(myActionListener);
        B_backspace.addActionListener(myActionListener);
        B_random.addActionListener(myActionListener);
        B_pow.addActionListener(myActionListener);
        B_sqrt.addActionListener(myActionListener);
        B_mod.addActionListener(myActionListener);
        B_leftBracket.addActionListener(myActionListener);
        B_rightBracket.addActionListener(myActionListener);
        B_factorial.addActionListener(myActionListener);
        B_div.addActionListener(myActionListener);
        B_7.addActionListener(myActionListener);
        B_8.addActionListener(myActionListener);
        B_9.addActionListener(myActionListener);
        B_mul.addActionListener(myActionListener);
        B_4.addActionListener(myActionListener);
        B_5.addActionListener(myActionListener);
        B_6.addActionListener(myActionListener);
        B_dec.addActionListener(myActionListener);
        B_1.addActionListener(myActionListener);
        B_2.addActionListener(myActionListener);
        B_3.addActionListener(myActionListener);
        B_add.addActionListener(myActionListener);
        B_0.addActionListener(myActionListener);
        B_point.addActionListener(myActionListener);
        B_equal.addActionListener(myActionListener);
    }

    class MyActionListener implements ActionListener { // 实现动作Listener接口。实现里面的actionPerformed方法
        public void actionPerformed(ActionEvent e) {
            String inputString = TF_expression.getText();
            String outputString = TF_result.getText();

            String str = e.getActionCommand();
            String insertChar = "";
            switch (str) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", "*", "/", "(", ")", "e", "π" ->
                        insertChar = str;
                case "=" -> // 计算结果
                        calcAndSetResult(inputString);
                case "Copy" -> { // 复制输入输出
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection selection = new StringSelection(inputString + " = " + outputString);
                    clipboard.setContents(selection, null);
                }
                case "C" -> { // 清空输入输出
                    TF_expression.setText("");
                    TF_result.setText("");
                }
                case "backspace" -> { // 退格，清除一位输入
                    if (!inputString.isEmpty()) {
                        int index = TF_expression.getCaretPosition();
                        if (0 < index && index <= inputString.length()) {
                            inputString = inputString.substring(0, index - 1) + inputString.substring(index);
                            TF_expression.setText(inputString);
                            if (index - 1 <= inputString.length()) {
                                TF_expression.setCaretPosition(index - 1);
                            }
                        }
                    }
                }
                case "Mod" -> // 取余，运算符符号为"#"，按键显示为"Mod"
                        insertChar = "#";
                case "n!" -> // 阶乘
                        insertChar = "!";
                case "2√x" -> // 2次根下x
                        insertChar = "√";
                case "x^n" -> // x的n次方
                        insertChar = "^";
                case "n√x" -> // n次根下x，需要点击“=”出结果，可以在混合运算中使用
                        insertChar = "^(1/n)";
                case "ln" -> // 以e为底的对数
                        insertChar = "ln(x)";
                case "e^x" -> // e的x次方
                        insertChar = "exp(x)";
                case "Random" -> // 随机数
                        insertChar = "[nat2]";
                default -> {
                }
            }
            if (!insertChar.isEmpty()) {
                TF_expression.replaceSelection(insertChar);
            }
        }

        void calcAndSetResult(String inputString) {
            if (!inputString.isEmpty()) {
                Expression expression = new Expression(inputString);
                double v = expression.calculate();
                String vStr = String.valueOf(v);
                if (vStr.endsWith(".0")) {
                    vStr = vStr.substring(0, vStr.length() - 2);
                }
                TF_result.setText(vStr);
            }
        }
    }

    public JTextField getExpression() {
        return TF_expression;
    }
}
