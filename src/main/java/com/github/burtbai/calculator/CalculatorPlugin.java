package com.github.burtbai.calculator;

import com.github.burtbai.calculator.ui.CalculatorPanel;
import com.github.burtbai.calculator.ui.action.FeedbackAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author burtbai
 */
public class CalculatorPlugin implements ToolWindowFactory {

    /**
     * Create the tool window content.
     *
     * @param project    current project
     * @param toolWindow current tool window
     */
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(new CalculatorPanel(), "", false);
        toolWindow.getContentManager().addContent(content);

        List<AnAction> actionList = new ArrayList<>();
        actionList.add(new FeedbackAction());
        toolWindow.setTitleActions(actionList);
    }

}
