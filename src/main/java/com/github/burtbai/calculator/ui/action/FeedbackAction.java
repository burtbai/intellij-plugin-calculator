package com.github.burtbai.calculator.ui.action;

import com.github.burtbai.calculator.message.CalculatorBundle;
import com.intellij.icons.AllIcons;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

/**
 * @author burtbai
 */
public class FeedbackAction extends DumbAwareAction {
    public FeedbackAction() {
        super(() -> CalculatorBundle.message("ui.GitHubAction.description"), AllIcons.Vcs.Vendors.Github);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        BrowserUtil.browse(CalculatorBundle.message("ui.GitHubAction.url"));
    }
}
