package com.nvpiao.entity.object;

import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.enums.DocumentTypes;

/**
 * com.nvpiao.entity.object.Poem.java
 * <p>
 * A simple class for com.nvpiao.entity.object.Poem
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public class Poem extends BaseDocument {
    /**
     * number of verses
     */
    private int verses;

    public Poem() {
        super(DocumentTypes.POEM);
    }

    public int getVerses() {
        return verses;
    }

    public void setVerses(int verses) {
        this.verses = verses;
    }

    public void setVerses(String verseStr) {
        this.verses = Integer.parseInt(verseStr);
    }

    @Override
    public String toString() {
        return "Document type: com.nvpiao.entity.object.Poem\n"
                + "Title: " + getTitle() + "\n"
                + "Author: " + getAuthor() + "\n"
                + "Number of verses: " + getVerses() + "\n"
                + "Year: " + getYear();
    }
}
