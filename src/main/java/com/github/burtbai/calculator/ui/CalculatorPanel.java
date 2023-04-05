package com.github.burtbai.calculator.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author burtbai
 */
public class CalculatorPanel extends JPanel {

    private JTextField TF_expression;

    public CalculatorPanel() {
        super(new BorderLayout(10, 10));
        CalculatorKeyArea keyArea = new CalculatorKeyArea();
        add(keyArea);
        setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));

        TF_expression = keyArea.getExpression();
    }

    public JTextField getExpression() {
        return TF_expression;
    }
}
