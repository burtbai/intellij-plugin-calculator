package com.github.burtbai.calculator.ui.action.DocPanel;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.DoubleClickListener;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Enumeration;

/**
 * @author burtbai
 */
public class DocumentationPanel extends JBPanel<DocumentationPanel> {
    private static final Logger LOG = Logger.getInstance(DocumentationPanel.class);

    private SearchTextField mySearchTextField;

    DocumentationTableModel tableModel;
    private TableRowSorter<DocumentationTableModel> sorter;
    private JBTable myDocTable;
    private JBScrollPane scrollPane;

    private JBPopup myJBPopup;
    private JTextField myExpressionTextField;

    public DocumentationPanel(JTextField expressionTextField) {
        myExpressionTextField = expressionTextField;

        initComponents();
        initListener();

        mySearchTextField.requestFocusInWindow();
    }

    public void setJBPopup(JBPopup jbPopup) {
        myJBPopup = jbPopup;
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        mySearchTextField = new SearchTextField(false);

        tableModel = new DocumentationTableModel();
        sorter = new TableRowSorter<>(tableModel);
        sorter.setComparator(0, Comparator.comparingInt((String s) -> Integer.parseInt(s)));
        myDocTable = new JBTable(tableModel);
        myDocTable.setRowSorter(sorter);
        myDocTable.setFocusable(false);
        myDocTable.setShowColumns(true);
        myDocTable.setShowGrid(false);
        myDocTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fitTableColumns(myDocTable, myDocTable.getWidth());

        scrollPane = new JBScrollPane(myDocTable);

        this.add(mySearchTextField, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void fitTableColumns(JBTable myTable, float totalWidth) {
        totalWidth -= 17;
        int[] width = {40, 120, 120, 330, 150, 40};
        int i = 0;
        Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = columns.nextElement();
            if (i < width.length) {
                column.setPreferredWidth((int) ((totalWidth / 800) * width[i]));
            }
            i++;
        }
    }

    private void initListener() {
        mySearchTextField.addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                newFilter(mySearchTextField.getText());
            }
        });

        new DoubleClickListener() {
            @Override
            protected boolean onDoubleClick(@NotNull MouseEvent event) {
                if (event.getSource() != myDocTable) {
                    return false;
                }
                useSelectedRow();
                return true;
            }
        }.installOn(myDocTable);
    }

    private void newFilter(String text) {
        RowFilter<DocumentationTableModel, Object> rf;
        try {
            rf = RowFilter.regexFilter("(?i)" + text, 0, 1, 2, 3, 4, 5);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void useSelectedRow() {
        int row = myDocTable.getSelectedRow();
        if (row == -1) {
            return;
        }
        LOG.info("row: " + row);
        int modelRow = myDocTable.convertRowIndexToModel(row);
        LOG.info("modelRow: " + modelRow);
        String value = tableModel.getExpressionString(modelRow);
        LOG.info("useSelectedRow: " + value);

        if (myJBPopup != null && myJBPopup.canClose()) {
            myJBPopup.cancel();
        }

        if (myExpressionTextField != null) {
            myExpressionTextField.replaceSelection(value);
            myExpressionTextField.requestFocusInWindow();
        }
    }
}
