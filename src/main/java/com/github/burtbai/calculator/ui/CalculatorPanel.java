package com.github.burtbai.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author burtbai
 */
public class CalculatorPanel extends JPanel {

    public CalculatorPanel() {
        super(new BorderLayout(10, 10));
//        CalculatorHead head = new CalculatorHead();
//        add(head, BorderLayout.LINE_START);
        CalculatorKeyArea keyArea = new CalculatorKeyArea();
//        add(keyArea, BorderLayout.SOUTH);
        add(keyArea);
        setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setLocation(500, 100);
        CalculatorPanel panel = new CalculatorPanel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
