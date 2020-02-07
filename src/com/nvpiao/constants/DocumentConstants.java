package com.nvpiao.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * com.nvpiao.constants.DocumentConstants.java
 * <p>
 * Constant values
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public class DocumentConstants {

    // type name of enum
    public static final String POEM_NAME = "com.nvpiao.entity.object.Poem";
    public static final String PLAY_NAME = "com.nvpiao.entity.object.Play";
    public static final String NOVEL_NAME = "com.nvpiao.entity.object.Novel";

    // code of enum
    public static final int POEM_CODE = 1;
    public static final int PLAY_CODE = 2;
    public static final int NOVEL_CODE = 3;

    // font size
    public static final int MAXIMUM_FONT_SIZE = 30;
    public static final int MINIMUM_FONT_SIZE = 10;

    //page
    public static final int HOME_PAGE_NUM = 1;
    public static final int HOME_PAGE_NUM_FLAG = -2;
    public static final int END_PAGE_NUM_FLAG = 2;
    public static final int PREV_PAGE_NUM_FLAG = -1;
    public static final int NEXT_PAGE_NUM_FLAG = 1;

    // handle file
    public static final int EMPTY_LINE_LEN = 0;
    public static final int INITIAL_PROGRESS_ZERO = 0;
    public static final int MAXIMUM_PROGRESS_ONE_HUNDRED = 100;
    public static final int NOVEL_PROGRESS_STEPS = 20;
    public static final int TEMP_MAXIMUM_PROGRESS_NINETY = 90;

    // top 10 limit
    public static final long TOP_TEN_LIMIT = 10;

    // file
    public static final String DEF_FILE_SEARCH_PATH = "./";
    public static final String SEARCH_REGEX_PREFIX_SUFFIX = ".*";
    public static final String DOC_LOADED_SUFFIX = "(loaded)";
    public static final String FILE_HEADER_SEPARATOR = ":";
    public static final String TEXT_START = "Text:";
    public static final String PROPERTY_CHANGE_PROGRESS = "progress";
    public static final String PROGRESS_LOADING_MSG_INFO = "Loading Document";
    public static final String PROGRESS_LOADING_NOTE_INFO = "";
    public static final String FILE_RETURN = "\n";
    public static final String END_OF_ACT = "ACT DROP\n";
    public static final String POEM_CHAPTERS_SUFFIX = "com.nvpiao.entity.object.Poem";
    public static final String NOVEL_CHAPTERS_REGEX = "^\\s+Chapter\\s[0-9]+\n$";

    // option panel
    public static final String WARNINGS = "Warnings";
    public static final String ERROR = "Errors";
    public static final String INFO = "Infos";
    public static final String ABOUT = "About";
    public static final String STATUS = "Status";
    public static final String TOP_TEN = "Top10 Frequency";

    //top 10
    public static final String TOP_TEN_WORDS = "word: ";
    public static final String TOP_TEN_FREQUENCY = " - frequency: ";

    // about info
    public static final String ABOUT_INFO = "DocumentViewer\n\n"
            + "Version: 1.0\n"
            + "Build on: 28 December 2019\n"
            + "Runtime version: 1.8.0_231-b11\n"
            + "VM: Java HotSpot(TM) 64-Bit Server by Oracle\n"
            + "\nCopyright \u00a9 2019 Ming Liu";

    // method
    public static final Map<String, String> BASE_FIELDS =
            Collections.unmodifiableMap(new HashMap<String, String>() {{
                put("Document type", "setDocType");
                put("Title", "setTitle");
                put("Author", "setAuthor");
                put("Year", "setYear");
            }});

    public static final Map<String, String> POEM_FIELDS =
            Collections.unmodifiableMap(new HashMap<String, String>() {{
                put("Number of verses", "setVerses");
            }});

    public static final Map<String, String> PLAY_FIELDS =
            Collections.unmodifiableMap(new HashMap<String, String>() {{
                put("Number of acts", "setActs");
                put("Number of actors", "setActors");
            }});

    public static final Map<String, String> NOVEL_FIELDS =
            Collections.unmodifiableMap(new HashMap<String, String>() {{
                put("Number of chapters", "setChapters");
            }});
}
