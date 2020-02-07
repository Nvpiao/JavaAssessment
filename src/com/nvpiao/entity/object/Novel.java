package com.nvpiao.entity.object;

import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.enums.DocumentTypes;

/**
 * com.nvpiao.entity.object.Novel.java
 * <p>
 * A simple class for com.nvpiao.entity.object.Novel
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public class Novel extends BaseDocument {
    /**
     * number of chapters
     */
    private int chapters;

    public Novel() {
        super(DocumentTypes.NOVEL);
    }

    public Novel(int chapters) {
        this();
        this.chapters = chapters;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public void setChapters(String chapterStr) {
        this.chapters = Integer.parseInt(chapterStr);
    }

    @Override
    public String toString() {
        return "Document type: com.nvpiao.entity.object.Novel\n"
                + "Title: " + getTitle() + "\n"
                + "Author: " + getAuthor() + "\n"
                + "Number of chapters: " + getChapters() + "\n"
                + "Year: " + getYear();
    }
}
