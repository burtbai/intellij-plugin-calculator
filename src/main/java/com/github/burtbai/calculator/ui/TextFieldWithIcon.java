package com.github.burtbai.calculator.ui;

import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author burtbai
 */
public class TextFieldWithIcon extends JTextField {
    private final Icon myIcon;

    public TextFieldWithIcon(@NotNull Icon icon) {
        super("");
        myIcon = icon;
        this.setMargin(JBUI.insets(0, 40));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int iconWidth = myIcon.getIconWidth();
        int iconHeight = myIcon.getIconHeight();
        int height = this.getHeight();
        myIcon.paintIcon(this, g, (30 - iconWidth), (height - iconHeight) / 2);
    }
}
