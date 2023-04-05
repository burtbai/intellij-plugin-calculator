package com.github.burtbai.calculator.ui.action.DocPanel;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author burtbai
 */
public class DocumentationTableModel extends AbstractTableModel implements TableModel {

    private static final DocumentationTableModel INSTANCE = new DocumentationTableModel();

    private final List<String> columnNames = new ArrayList<>();

    private final List<DocumentationRow> data = new ArrayList<>();

    public static final String[] CATEGORY_VALUES = new String[]{
            "Operator",
            "Boolean Operator",
            "Binary Relation",
            "Bitwise Operator",
            "Parser Symbol",
    };
    public static final Set<String> CATEGORY_SET = new HashSet<>(Arrays.asList(CATEGORY_VALUES));

    public DocumentationTableModel() {
        try (InputStream is = this.getClass().getResourceAsStream("/messages/mXparserHelp.md")) {
            if (is != null) {
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                String[] helpMd = result.toString(StandardCharsets.UTF_8).split("\n");
                if (helpMd.length > 0) {
                    String regex = "(?<!\\\\)\\|";
                    String[] headers = helpMd[0].split(regex);
                    columnNames.add("No.");
                    for (int i = 1; i < headers.length; i++) {
                        columnNames.add(headers[i].trim());
                    }
                    for (int i = 2; i < helpMd.length; i++) {
                        String[] rows = helpMd[i].split(regex);
                        DocumentationRow row = new DocumentationRow(
                                String.valueOf(i - 1), rows[1], rows[2], rows[3], rows[4], rows[5]);
                        data.add(row);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        return INSTANCE.data.size();
    }

    @Override
    public int getColumnCount() {
        return INSTANCE.columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return INSTANCE.data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return INSTANCE.columnNames.get(column);
    }

    public String getExpressionString(int rowIndex) {
        DocumentationRow row = INSTANCE.data.get(rowIndex);
        if (CATEGORY_SET.contains(row.category)) {
            return row.keyWord;
        } else {
            return row.example;
        }
    }
}
