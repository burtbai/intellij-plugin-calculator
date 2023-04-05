package com.github.burtbai.calculator.ui.action.DocPanel;

/**
 * @author burtbai
 */
public class DocumentationRow {
    // |KeyWord|Category|Description|Example|Since(mXparser)|
    String no;
    String keyWord;
    String category;
    String description;
    String example;
    String since;

    public DocumentationRow(String no, String keyWord, String category, String description, String example, String since) {
        this.no = no;
        this.keyWord = keyWord.trim().replace("\\|", "|");
        this.category = category.trim();
        this.description = description.trim();
        this.example = example.trim().replace("\\|", "|");
        this.since = since.trim();
    }

    public String get(int index) {
        switch (index) {
            case 0:
                return this.no;
            case 1:
                return this.keyWord;
            case 2:
                return this.category;
            case 3:
                return this.description;
            case 4:
                return this.example;
            case 5:
                return this.since;
            default:
                return "";
        }
    }
}
