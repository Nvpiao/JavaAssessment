package com.nvpiao.constants;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * com.nvpiao.constants.DocViewerConstants.java
 * <p>
 * Constant values
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   21 December 2019
 */
public class DocViewerConstants {
    // words
    public static final String DOCUMENT_VIEWER_TITLE = "DocumentViewer";

    //font
    public static final String OPTION_PANE_FONT = "OptionPane.messageFont";

    // top words
    public static final String TOP_SETTINGS = "Settings";
    public static final String TOP_STATISTICS = "Statistics";
    public static final String BRI_DAR_MODEL_LABEL = "Model: ";
    public static final String BRI_DAR_BUTTON_BRIGHT_LABEL = "Bright";
    public static final String BRI_DAR_BUTTON_DARK_LABEL = "Dark";
    public static final String FONT_SIZE_LABEL = "Font Size:";
    public static final String BACK_COLOR_LABEL = "Background Color:";
    public static final String BACK_COLOR_WHITE_LABEL = "White ";
    public static final String BACK_COLOR_GRAY_LABEL = "Gray  ";
    public static final String BACK_COLOR_YELLOW_LABEL = "Yellow";
    public static final String BACK_COLOR_GREEN_LABEL = "Green ";
    public static final String SHOW_STATUS = "Show Stats";
    public static final String TOP_TEN = "Top10 Words";
    public static final String ABOUT = "About";

    // color map
    public static final Map<String, Color> COLORS_MAP =
            Collections.unmodifiableMap(new HashMap<String, Color>() {
                {
                    put(BACK_COLOR_WHITE_LABEL, Color.WHITE);
                    put(BACK_COLOR_GRAY_LABEL, new Color(220, 220, 220));
                    put(BACK_COLOR_YELLOW_LABEL, new Color(240, 240, 80));
                    put(BACK_COLOR_GREEN_LABEL, new Color(190, 255, 190));
                }
            });

    // mid left words
    public static final String MID_LEFT_LOAD = "Load";
    public static final String LOAD_BUTTON = "Load";
    public static final String NO_DOCUMENTS = "No Documents";
    public static final String MID_LEFT_CHAPTERS = "Chapters";
    public static final String NO_CHAPTERS = "No Chapters";

    // mid right words
    public static final String NO_SELECTED_DOC = "No Selected Document";
    public static final String DISPLAY_TEXT = "Display Text";
    public static final String DISPLAY_DOC_NAME_INFO = "Displayed Name: ";
    public static final String DISPLAY_DOC_NAME = "-";

    // button words
    public static final String BRIGHT_MODEL = "Model: Bright";
    public static final String DARK_MODEL = "Model: Dark";
    public static final String END_OF_DOC = "End of Document";
    public static final String HOME_OF_DOC = "Home of Document";
    public static final String PREV_PAGE = "Previous page";
    public static final String NEXT_PAGE = "Next page";
    public static final String MODEL_INFO = "Different model type";
    public static final String TOTAL_PAGES_INFO = " of ";
    public static final String NUMBER_HOLDER = "-";
    public static final String HOME_PAGE_LABEL = "<|  ";
    public static final String PREV_PAGE_LABEL = "< ";
    public static final String NEXT_PAGE_LABEL = " >";
    public static final String END_OF_DOC_LABEL = "  |>";
    public static final String WORDS_LABEL = "Words: ";
    public static final String FONT_MINUS_LABEL = "A-";
    public static final String FONT_PLUS_LABEL = "A+";

    // window size
    public static final int PROPORTION_BASE = 7;
    public static final int PROPORTION_HOLD = 5;
    public static final int TOP_NAVI_HEIGHT = 118;
    public static final int BOTTOM_CON_HEIGHT = 35;
    public static final int MID_LEFT_WIDTH = 320;
    public static final int MID_SELECTED_FIELD_WIDTH = 150;
    public static final int MID_SELECTED_FIELD_HEIGHT = 40;

    // font size
    public static final int DEFAULT_FONT_SIZE = 18;

    // color
    public static final int GRAY_WHITE = 240;
    public static final int GRAY_BLACK = 60;

    // layout
    public static final int BOTTOM_LAYOUT_ROWS = 1;
    public static final int BOTTOM_LAYOUT_COLS = 3;
    public static final int BRI_DAR_LAYOUT_ROWS = 1;
    public static final int BRI_DAR_LAYOUT_COLS = 3;
    public static final int BRI_DAR_CHOICE_LAYOUT_ROWS = 2;
    public static final int BRI_DAR_CHOICE_LAYOUT_COLS = 1;
    public static final int BLACK_COLOR_LAYOUT_COLS = 3;
    public static final int BACK_COLOR_CHOICE_LAYOUT_COLS = 3;
    public static final int VIEW_NAME_PANEL_ROWS = 1;
    public static final int VIEW_NAME_PANEL_COLS = 2;
    public static final double WEIGHTX_ONE = 1.;
    public static final double WEIGHTX_ZERO = 0.;

    // margin
    public static final int TOP_MARGIN = 10;
    public static final int BOTTOM_MARGIN = 10;
    public static final int LEFT_MARGIN = 5;
    public static final int RIGHT_MARGIN = 5;
    public static final int TOP_LOAD_MARGIN = 0;
    public static final int LEFT_LOAD_MARGIN = 0;
    public static final int BOTTOM_LOAD_MARGIN = 5;
    public static final int RIGHT_LOAD_MARGIN = 0;
    public static final int TOP_BRI_DAR_MARGIN = 5;
    public static final int BOTTOM_BRI_DAR_MARGIN = 5;
    public static final int TOP_FONT_SIZE_MARGIN = 3;
    public static final int LEFT_FONT_SIZE_MARGIN = 3;
    public static final int BUTTON_FONT_SIZE_MARGIN = 3;
    public static final int RIGHT_FONT_SIZE_MARGIN = 3;
    public static final int TOP_STATISTICS_MARGIN = 8;
    public static final int LEFT_STATISTICS_MARGIN = 0;
    public static final int BOTTOM_STATISTICS_MARGIN = 0;
    public static final int RIGHT_STATISTICS_MARGIN = 0;
    public static final int TOP_STATUS_MARGIN = 16;
    public static final int LEFT_STATUS_MARGIN = 8;
    public static final int BOTTOM_STATUS_MARGIN = 16;
    public static final int RIGHT_STATUS_MARGIN = 8;
}
