package com.nvpiao.enums;

import com.nvpiao.constants.DocumentConstants;

/**
 * com.nvpiao.enums.DocumentTypes.java
 * <p>
 * A simple enum for com.nvpiao.enums.DocumentTypes
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public enum DocumentTypes {
    // type of poem
    POEM(DocumentConstants.POEM_NAME, DocumentConstants.POEM_CODE),
    // type of play
    PLAY(DocumentConstants.PLAY_NAME, DocumentConstants.PLAY_CODE),
    // type of novel
    NOVEL(DocumentConstants.NOVEL_NAME, DocumentConstants.NOVEL_CODE);

    /**
     * name of enum
     */
    private String name;

    /**
     * code of enum
     */
    private int code;

    DocumentTypes(String name, int code) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
