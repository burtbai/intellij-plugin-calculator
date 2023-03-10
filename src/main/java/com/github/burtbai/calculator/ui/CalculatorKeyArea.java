package com.github.burtbai.calculator.ui;

import com.github.burtbai.calculator.message.CalculatorBundle;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.ui.DarculaTextFieldUI;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

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
    private GridBagConstraints gridBagConstraints = new GridBagConstraints(); // 实例化该对象用来对组件进行管理
    private GridBagLayout gridBagLayout = new GridBagLayout(); //实例化布局对象, 网格袋布局

    // 表达式, 输入行
    private JTextField TF_expression = new JTextField("");
    // 结果, 输出行
    private JTextField TF_result = new JTextField("");

    // 第1行
    private JButton B_copy = new JButton(AllIcons.Actions.Copy); // 复制输入输出
    private JButton B_clear = new JButton("C"); // 清空输入输出
    private JButton B_backspace = new JButton(AllIcons.Actions.Back); // 退格，清除输入的最后一位
    // 第2行
    private JButton B_random = new JButton("Random"); // 随机变量, U{0,10²}
    private JButton B_pow = new JButton("x^n"); // x的n次方
    private JButton B_sqrt = new JButton("2√x"); // 开根号
    private JButton B_mod = new JButton("Mod"); // 取余
    // 第3行
    private JButton B_leftBracket = new JButton("("); // 混合运算的左括号
    private JButton B_rightBracket = new JButton(")"); // 混合运算的右括号
    private JButton B_factorial = new JButton("n!"); // 阶乘
    private JButton B_div = new JButton("/"); // 除号
    //第4行
    private JButton B_7 = new JButton("7"); // 数字7
    private JButton B_8 = new JButton("8"); // 数字8
    private JButton B_9 = new JButton("9"); // 数字9
    private JButton B_mul = new JButton("*"); // 乘号
    //第5行
    private JButton B_4 = new JButton("4"); // 数字4
    private JButton B_5 = new JButton("5"); // 数字5
    private JButton B_6 = new JButton("6"); // 数字6
    private JButton B_dec = new JButton("-"); // 减号
    //第6行
    private JButton B_1 = new JButton("1"); // 数字1
    private JButton B_2 = new JButton("2"); // 数字2
    private JButton B_3 = new JButton("3"); // 数字3
    private JButton B_add = new JButton("+"); // 加号
    //第7行
    private JButton B_0 = new JButton("0"); // 数字0
    private JButton B_point = new JButton("."); // 小数点
    private JButton B_equal = new JButton("="); // 等号


    public CalculatorKeyArea() {
        License.iConfirmNonCommercialUse("burtbai");

        this.setLayout(gridBagLayout); // 窗体对象设置为GridBagLayout布局
        gridBagConstraints.fill = GridBagConstraints.BOTH; // 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况

        int y = 0;

        setControl(TF_expression, 0, y, 4, 1);
        TF_expression.setHorizontalAlignment(JTextField.RIGHT);
        TF_expression.setUI(new DarculaTextFieldUI());

        setControl(TF_result, 0, ++y, 4, 1);
        TF_result.setHorizontalAlignment(JTextField.RIGHT);

        // 按键区第1行
        setControl(B_copy, 1, ++y, 1, 1);
        B_copy.setActionCommand("Copy");
        B_copy.setToolTipText(CalculatorBundle.message("ui.CopyButtonToolTipText"));

        setControl(B_clear, 2, y, 1, 1);
        B_clear.setToolTipText(CalculatorBundle.message("ui.ClearButtonToolTipText"));

        setControl(B_backspace, 3, y, 1, 1);
        B_backspace.setActionCommand("←");
        B_backspace.setToolTipText(CalculatorBundle.message("ui.BackspaceButtonToolTipText"));

        // 第2行
        setControl(B_random, 0, ++y, 1, 1);
        B_random.setToolTipText(CalculatorBundle.message("ui.RandomButtonToolTipText"));
        setControl(B_pow, 1, y, 1, 1);
        setControl(B_sqrt, 2, y, 1, 1);
        setControl(B_mod, 3, y, 1, 1);
        // 第3行
        setControl(B_leftBracket, 0, ++y, 1, 1);
        setControl(B_rightBracket, 1, y, 1, 1);
        setControl(B_factorial, 2, y, 1, 1);
        setControl(B_div, 3, y, 1, 1);
        // 第4行
        setControl(B_7, 0, ++y, 1, 1);
        setControl(B_8, 1, y, 1, 1);
        setControl(B_9, 2, y, 1, 1);
        setControl(B_mul, 3, y, 1, 1);
        // 第5行
        setControl(B_4, 0, ++y, 1, 1);
        setControl(B_5, 1, y, 1, 1);
        setControl(B_6, 2, y, 1, 1);
        setControl(B_dec, 3, y, 1, 1);
        // 第6行
        setControl(B_1, 0, ++y, 1, 1);
        setControl(B_2, 1, y, 1, 1);
        setControl(B_3, 2, y, 1, 1);
        setControl(B_add, 3, y, 1, 1);
        // 第7行
        setControl(B_0, 1, ++y, 1, 1);
        setControl(B_point, 2, y, 1, 1);
        setControl(B_equal, 3, y, 1, 1);
//        JBColor color = new JBColor(new Color(76, 193, 255), Color.WHITE);
//        B_equal.setBackground(color);
//        B_equal.setOpaque(true);
//        B_equal.setBorderPainted(false);

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
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case ".":
                case "+":
                case "-":
                case "*":
                case "/":
                case "(":
                case ")":
                case "e":
                case "π":
                    insertChar = str;
                    break;
                case "=": // 计算结果
                    if (inputString.length() > 0) {
                        Expression expression = new Expression(inputString);
                        double v = expression.calculate();
                        TF_result.setText(String.valueOf(v));
                    }
                    break;
                case "Copy": // 复制输入输出
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection selection = new StringSelection(inputString + " = " + outputString);
                    clipboard.setContents(selection, null);
                    break;
                case "C": // 清空输入输出
                    TF_expression.setText("");
                    TF_result.setText("");
                    break;
                case "←": // 退格，清除一位输入
                    if (inputString.length() > 0) {
                        int index = TF_expression.getCaretPosition();
                        if (0 < index && index <= inputString.length()) {
                            inputString = inputString.substring(0, index - 1) + inputString.substring(index);
                            TF_expression.setText(inputString);
                            if (index - 1 <= inputString.length()) {
                                TF_expression.setCaretPosition(index - 1);
                            }
                        }
                    }
                    break;
                case "Mod": // 取余，运算符符号为"#"，按键显示为"Mod"
                    insertChar = "#";
                    break;
                case "n!": // 阶乘
                    insertChar = "!";
                    break;
                case "2√x": // 2次根下x
                    insertChar = "√";
                    break;
                case "x^n": // x的n次方
                    insertChar = "^";
                    break;
                case "n√x": // n次根下x，需要点击“=”出结果，可以在混合运算中使用
                    insertChar = "^(1/n)";
                    break;
                case "ln": // 以e为底的对数
                    insertChar = "ln(x)";
                    break;
                case "e^x": // e的x次方
                    insertChar = "exp(x)";
                    break;
                case "Random": // 随机数
                    insertChar = "[nat2]";
                    break;
                default:
                    break;
            }
            if (insertChar.length() > 0) {
                TF_expression.replaceSelection(insertChar);
            }
        }
    }
}
