package com.github.burtbai.calculator.ui.action;

import com.github.burtbai.calculator.message.CalculatorBundle;
import com.github.burtbai.calculator.ui.action.DocPanel.DocumentationPanel;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author burtbai
 */
public class DocumentationAction extends DumbAwareAction {
    private static final Logger LOG = Logger.getInstance(DocumentationAction.class);

    private JTextField myExpressionTextField;

    public DocumentationAction(JTextField expressionTextField) {
        super(() -> CalculatorBundle.message("ui.DocumentationAction.description"), AllIcons.Toolwindows.Documentation);

        myExpressionTextField = expressionTextField;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        DocumentationPanel docPanel = new DocumentationPanel(myExpressionTextField);
        docPanel.setPreferredSize(new Dimension(817, 500));

        JBPopup popup = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(docPanel, docPanel)
                .setShowBorder(false)
                .setRequestFocus(true)
                .setFocusable(true)
                .setResizable(true)
                .setMovable(true)
                .setTitle(CalculatorBundle.message("ui.DocumentationAction.description"))
                .createPopup();
        docPanel.setJBPopup(popup);
        popup.showInFocusCenter();
    }
}
