package com.nvpiao.frame;

import com.nvpiao.constants.DocViewerConstants;
import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.listener.ChapterSelectionListener;
import com.nvpiao.listener.DocListSelectionListener;
import com.nvpiao.listener.ListEmptyClickListener;
import com.nvpiao.listener.LoadAndDisActionListener;
import com.nvpiao.listener.LoadNameChangeListener;
import com.nvpiao.listener.PageHelperMouseListener;
import com.nvpiao.listener.SettingsActionListener;
import com.nvpiao.listener.StatisticActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * com.nvpiao.frame.DocViewerFrame.java
 * <p>
 * A simple class for com.nvpiao.frame.DocViewerFrame
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   21 December 2019
 */
public class DocViewerFrame extends JFrame {

    /**
     * document list
     */
    private List<BaseDocument> documents;

    /**
     * Default contentPanel
     **/
    private Container contentPane;
    /**
     * Default font of frame
     */
    private Font defFrameFont;

    /**
     * Default color of frame
     */
    private Color defFrameColor;

    /**
     * Default white color
     */
    private Color defWhiteColor;

    /**
     * all doc files
     */
    private Vector<String> docFileList;

    /**
     * displayed document
     */
    private BaseDocument currentDoc;

    /**
     * Settings listener
     */
    private SettingsActionListener settingsActionListener;

    /**
     * doc list selected listener
     */
    private DocListSelectionListener docListSelectionListener;

    /**
     * load name change listener
     */
    private LoadNameChangeListener loadNameChangeListener;

    /**
     * load and display listener
     */
    private LoadAndDisActionListener loadAndDisActionListener;

    /**
     * chapter selection listener
     */
    private ChapterSelectionListener chapterSelectionListener;

    /**
     * page helper mouse listener
     */
    private PageHelperMouseListener pageHelperMouseListener;

    /**
     * statistic action listener
     */
    private StatisticActionListener statisticActionListener;

    /**
     * list empty click listener
     */
    private ListEmptyClickListener listEmptyClickListener;

    // default
    private JTabbedPane topTabbedPane;
    private JButton statsButton;
    private JButton topTenButton;
    private JButton aboutButton;
    private JButton fontMinusButton;
    private JButton fontPlusButton;
    private JTabbedPane midLeftTabbedPane;
    private JPanel bottomControlPane;
    private JPanel pagePanel;
    private JPanel fontSizeChoicePanel;
    private JPanel midAreaPanel;
    private JLabel pageLabel;
    private JLabel disNameInfoLabel;
    private JLabel disNameLabel;
    private JPanel viewNamePanel;

    // white
    private JPanel statisticsPanel;
    private JPanel settingsPanel;
    private JPanel backColorPanel;
    private JPanel backColorLabelPanel;
    private JRadioButton backColorWhiteButton;
    private JRadioButton backColorGrayButton;
    private JRadioButton backColorYellowButton;
    private JRadioButton backColorGreenButton;
    private JPanel fontSizePanel;
    private JPanel fontSizeLabelPanel;
    private JPanel briDarPanel;
    private JPanel briDarLabelPanel;
    private JRadioButton brightButton;
    private JRadioButton darkButton;
    private JPanel midLeftPanel;
    private JTextArea viewerArea;
    private JTextField searchField;
    private JButton loadButton;
    private JList<String> chapterList;
    private JList<String> docList;
    private JTextField selectedFiled;
    private JButton disButton;
    private JPanel viewShowPanel;
    private JPanel wordsPane;
    private JLabel wordsLabel;
    private JPanel midLeftLoadPanel;
    private JPanel disNamePanel;
    private JLabel briDarLabel;
    private JLabel fontSizeLabel;
    private JLabel backColorLabel;
    private JLabel pagesLabel;
    private JLabel lLeftLabel;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JLabel rRightLabel;
    /**
     * model label
     */
    private JLabel modelLabel;

    /**
     * number of words
     */
    private JLabel wordsNumLabel;


    /**
     * total page of doc
     */
    private JLabel pagesNumLabel;

    /**
     * button group
     */
    private ButtonGroup backColorButtonGroup;

    /**
     * Constructor with docList
     *
     * @param docFileList list of documents
     */
    public DocViewerFrame(Vector<String> docFileList) {
        this.contentPane = getContentPane();
        this.docFileList = docFileList;
        this.documents = new ArrayList<>();

        // setup listener
        this.settingsActionListener = new SettingsActionListener(this);
        this.docListSelectionListener = new DocListSelectionListener(this);
        this.loadNameChangeListener = new LoadNameChangeListener(this);
        this.loadAndDisActionListener = new LoadAndDisActionListener(this);
        this.chapterSelectionListener = new ChapterSelectionListener(this);
        this.pageHelperMouseListener = new PageHelperMouseListener(this);
        this.statisticActionListener = new StatisticActionListener(this);
        this.listEmptyClickListener = new ListEmptyClickListener();

        initFrame();
    }

    public ButtonGroup getBackColorButtonGroup() {
        return backColorButtonGroup;
    }

    public JRadioButton getBackColorWhiteButton() {
        return backColorWhiteButton;
    }

    public BaseDocument getCurrentDoc() {
        return currentDoc;
    }

    public void setCurrentDoc(BaseDocument currentDoc) {
        this.currentDoc = currentDoc;
    }

    public JLabel getDisNameLabel() {
        return disNameLabel;
    }

    public JTextArea getViewerArea() {
        return viewerArea;
    }

    public JList<String> getChapterList() {
        return chapterList;
    }

    public JLabel getPageLabel() {
        return pageLabel;
    }

    public JLabel getPagesNumLabel() {
        return pagesNumLabel;
    }

    public JLabel getWordsNumLabel() {
        return wordsNumLabel;
    }

    public void setModelLabel(String modelLabel) {
        this.modelLabel.setText(modelLabel);
    }

    public void setDefFrameColor(Color defFrameColor) {
        this.defFrameColor = defFrameColor;
        resetFrameColor(defFrameColor);
    }

    public void setDefWhiteColor(Color defWhiteColor) {
        this.defWhiteColor = defWhiteColor;
        resetWhiteColor(defWhiteColor);
    }

    public JList<String> getDocList() {
        return docList;
    }

    public JTextField getSelectedFiled() {
        return selectedFiled;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public List<BaseDocument> getDocuments() {
        return documents;
    }

    public Vector<String> getDocFileList() {
        return docFileList;
    }

    public LoadNameChangeListener getLoadNameChangeListener() {
        return loadNameChangeListener;
    }

    public ChapterSelectionListener getChapterSelectionListener() {
        return chapterSelectionListener;
    }

    /**
     * set color
     *
     * @param color color
     */
    private void resetWhiteColor(Color color) {
        this.statisticsPanel.setBackground(color);
        this.settingsPanel.setBackground(color);
        this.backColorPanel.setBackground(color);
        this.backColorLabelPanel.setBackground(color);
        this.backColorWhiteButton.setBackground(color);
        this.backColorGrayButton.setBackground(color);
        this.backColorYellowButton.setBackground(color);
        this.backColorGreenButton.setBackground(color);
        this.fontSizePanel.setBackground(color);
        this.fontSizeLabelPanel.setBackground(color);
        this.briDarPanel.setBackground(color);
        this.briDarLabelPanel.setBackground(color);
        this.brightButton.setBackground(color);
        this.darkButton.setBackground(color);
        this.midLeftPanel.setBackground(color);
        this.viewerArea.setBackground(color);
        this.searchField.setBackground(color);
        this.loadButton.setBackground(color);
        this.chapterList.setBackground(color);
        this.docList.setBackground(color);
        this.midLeftLoadPanel.setBackground(color);

        // set margin
        fontMinusButton.setBorder(BorderFactory.createMatteBorder(
                DocViewerConstants.TOP_FONT_SIZE_MARGIN,
                DocViewerConstants.LEFT_FONT_SIZE_MARGIN,
                DocViewerConstants.BUTTON_FONT_SIZE_MARGIN,
                DocViewerConstants.RIGHT_FONT_SIZE_MARGIN,
                color));
        // set margin
        fontPlusButton.setBorder(BorderFactory.createMatteBorder(
                DocViewerConstants.TOP_FONT_SIZE_MARGIN,
                DocViewerConstants.LEFT_FONT_SIZE_MARGIN,
                DocViewerConstants.BUTTON_FONT_SIZE_MARGIN,
                DocViewerConstants.RIGHT_FONT_SIZE_MARGIN,
                color));
    }

    /**
     * reset color
     *
     * @param color color
     */
    private void resetFrameColor(Color color) {
        this.topTabbedPane.setBackground(color);
        this.statsButton.setBackground(color);
        this.topTenButton.setBackground(color);
        this.aboutButton.setBackground(color);
        this.fontMinusButton.setBackground(color);
        this.fontPlusButton.setBackground(color);
        this.midLeftTabbedPane.setBackground(color);
        this.bottomControlPane.setBackground(color);
        this.pagePanel.setBackground(color);
        this.contentPane.setBackground(color);
        this.fontSizeChoicePanel.setBackground(color);
        this.midAreaPanel.setBackground(color);
        this.selectedFiled.setBackground(color);
        this.disButton.setBackground(color);
        this.viewShowPanel.setBackground(color);
        this.wordsPane.setBackground(color);
        this.wordsLabel.setBackground(color);
        this.pageLabel.setBackground(color);
        this.disNameInfoLabel.setBackground(color);
        this.disNameLabel.setBackground(color);
        this.viewNamePanel.setBackground(color);
        this.disNamePanel.setBackground(color);
    }

    /**
     * reset font color to gray white or black
     *
     * @param color black or gray color
     */
    public void setFontColor(Color color) {
        this.statsButton.setForeground(color);
        this.topTenButton.setForeground(color);
        this.aboutButton.setForeground(color);
        this.fontMinusButton.setForeground(color);
        this.fontPlusButton.setForeground(color);
        this.selectedFiled.setForeground(color);
        this.disButton.setForeground(color);
        this.wordsLabel.setForeground(color);
        this.pageLabel.setForeground(color);
        this.disNameInfoLabel.setForeground(color);
        this.disNameLabel.setForeground(color);
        this.briDarLabel.setForeground(color);
        this.brightButton.setForeground(color);
        this.darkButton.setForeground(color);
        this.fontSizeLabel.setForeground(color);
        this.backColorLabel.setForeground(color);
        this.backColorGrayButton.setForeground(color);
        this.backColorGreenButton.setForeground(color);
        this.backColorWhiteButton.setForeground(color);
        this.backColorYellowButton.setForeground(color);
        this.settingsPanel.setForeground(color);
        this.statisticsPanel.setForeground(color);
        this.docList.setForeground(color);
        this.chapterList.setForeground(color);
        this.loadButton.setForeground(color);
        this.searchField.setForeground(color);
        this.pagesNumLabel.setForeground(color);
        this.pageLabel.setForeground(color);
        this.modelLabel.setForeground(color);
        this.pagesLabel.setForeground(color);
        this.lLeftLabel.setForeground(color);
        this.leftLabel.setForeground(color);
        this.rRightLabel.setForeground(color);
        this.rightLabel.setForeground(color);
        this.wordsNumLabel.setForeground(color);
    }

    /**
     * Initializes frame
     */
    private void initFrame() {
        initFontAndColor();
        setFrameSize();

        // set msg dialog font
        UIManager.put(DocViewerConstants.OPTION_PANE_FONT, defFrameFont);
        // set background color
        contentPane.setBackground(defFrameColor);

        addTopNavigator();
        addMiddleArea();
        addBottomControlPane();
    }

    /**
     * initializes font and color
     */
    private void initFontAndColor() {
        defFrameFont = new Font(Font.SANS_SERIF, Font.PLAIN, DocViewerConstants.DEFAULT_FONT_SIZE);
        defFrameColor = new Color(DocViewerConstants.GRAY_WHITE,
                DocViewerConstants.GRAY_WHITE,
                DocViewerConstants.GRAY_WHITE);
        defWhiteColor = Color.WHITE;
    }

    /**
     * Initializes window size
     */
    private void setFrameSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds(((int) (width / DocViewerConstants.PROPORTION_BASE)),
                ((int) (height / DocViewerConstants.PROPORTION_BASE)),
                ((int) (width * DocViewerConstants.PROPORTION_HOLD / DocViewerConstants.PROPORTION_BASE)),
                ((int) ((height * DocViewerConstants.PROPORTION_HOLD) / DocViewerConstants.PROPORTION_BASE)));
    }

    /**
     * Add a navigator at top of frame
     */
    private void addTopNavigator() {
        topTabbedPane = new JTabbedPane();
        // set color
        topTabbedPane.setBackground(defFrameColor);
        // set height
        topTabbedPane.setPreferredSize(
                new Dimension(getWidth(), DocViewerConstants.TOP_NAVI_HEIGHT));
        // set font
        topTabbedPane.setFont(defFrameFont);

        // settings panel
        Component settingsPanel = getSettingsPanel();
        topTabbedPane.addTab(DocViewerConstants.TOP_SETTINGS, settingsPanel);

        // statistics panel
        Component statisticsPanel = getStatisticsPanel();
        topTabbedPane.addTab(DocViewerConstants.TOP_STATISTICS, statisticsPanel);

        // add to top of content panel
        contentPane.add(topTabbedPane, BorderLayout.NORTH);
    }

    /**
     * get a statistics tab
     *
     * @return a tab contains some statistics
     */
    private Component getStatisticsPanel() {
        statisticsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statisticsPanel.setBackground(defWhiteColor);
        statisticsPanel.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_STATISTICS_MARGIN,
                DocViewerConstants.LEFT_STATISTICS_MARGIN,
                DocViewerConstants.BOTTOM_STATISTICS_MARGIN,
                DocViewerConstants.RIGHT_STATISTICS_MARGIN));

        // show status button
        statsButton = new JButton(DocViewerConstants.SHOW_STATUS);
        // set color
        statsButton.setBackground(defFrameColor);
        // set font
        statsButton.setFont(defFrameFont);
        // set margin
        statsButton.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_STATUS_MARGIN,
                DocViewerConstants.LEFT_STATUS_MARGIN,
                DocViewerConstants.BOTTOM_STATUS_MARGIN,
                DocViewerConstants.RIGHT_STATUS_MARGIN));
        // add listener
        statsButton.addActionListener(statisticActionListener);
        statisticsPanel.add(statsButton);

        // top 10 frequency words
        topTenButton = new JButton(DocViewerConstants.TOP_TEN);
        // set color
        topTenButton.setBackground(defFrameColor);
        // set font
        topTenButton.setFont(defFrameFont);
        // set margin
        topTenButton.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_STATUS_MARGIN,
                DocViewerConstants.LEFT_STATUS_MARGIN,
                DocViewerConstants.BOTTOM_STATUS_MARGIN,
                DocViewerConstants.RIGHT_STATUS_MARGIN));
        // add listener
        topTenButton.addActionListener(statisticActionListener);
        statisticsPanel.add(topTenButton);

        // about
        aboutButton = new JButton(DocViewerConstants.ABOUT);
        // set color
        aboutButton.setBackground(defFrameColor);
        // set font
        aboutButton.setFont(defFrameFont);
        // set margin
        aboutButton.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_STATUS_MARGIN,
                DocViewerConstants.LEFT_STATUS_MARGIN,
                DocViewerConstants.BOTTOM_STATUS_MARGIN,
                DocViewerConstants.RIGHT_STATUS_MARGIN));
        aboutButton.addActionListener(statisticActionListener);
        statisticsPanel.add(aboutButton);

        return statisticsPanel;
    }

    /**
     * get settings panel
     *
     * @return a settings panel
     */
    private Component getSettingsPanel() {
        settingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        settingsPanel.setBackground(defWhiteColor);

        // bright and dark panel
        Component briDarPanel = getBriDarPanel();
        settingsPanel.add(briDarPanel);

        // font size panel
        Component fontSizePanel = getFontSizePanel();
        settingsPanel.add(fontSizePanel);

        // background panel
        Component backColorPanel = getBackColorPanel();
        settingsPanel.add(backColorPanel);

        return settingsPanel;
    }

    /**
     * get background panel
     *
     * @return a panel with background color label
     */
    private Component getBackColorPanel() {
        backColorPanel = new JPanel(new GridLayout(
                DocViewerConstants.BRI_DAR_LAYOUT_ROWS,
                DocViewerConstants.BLACK_COLOR_LAYOUT_COLS));
        // set color
        backColorPanel.setBackground(defWhiteColor);
        // set margin
        backColorPanel.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_BRI_DAR_MARGIN,
                DocViewerConstants.LEFT_MARGIN,
                DocViewerConstants.BOTTOM_BRI_DAR_MARGIN,
                DocViewerConstants.RIGHT_MARGIN));
        // label panel
        backColorLabelPanel = new JPanel(new BorderLayout());
        backColorLabelPanel.setBackground(defWhiteColor);
        backColorPanel.add(backColorLabelPanel);

        // color choice
        JPanel backColorChoicePanel = new JPanel(new GridLayout(
                DocViewerConstants.BRI_DAR_CHOICE_LAYOUT_ROWS,
                DocViewerConstants.BACK_COLOR_CHOICE_LAYOUT_COLS));
        backColorPanel.add(backColorChoicePanel);

        // background color label
        backColorLabel = new JLabel(DocViewerConstants.BACK_COLOR_LABEL);
        backColorLabel.setHorizontalAlignment(JLabel.CENTER);
        backColorLabel.setFont(defFrameFont);
        backColorLabelPanel.add(backColorLabel, BorderLayout.CENTER);

        backColorButtonGroup = new ButtonGroup();
        // color white
        backColorWhiteButton = new JRadioButton(DocViewerConstants.BACK_COLOR_WHITE_LABEL);
        // set font
        backColorWhiteButton.setFont(defFrameFont);
        // set color
        backColorWhiteButton.setBackground(defWhiteColor);
        // set selected
        backColorWhiteButton.setSelected(Boolean.TRUE);
        // add listener
        backColorWhiteButton.addActionListener(settingsActionListener);
        backColorButtonGroup.add(backColorWhiteButton);
        backColorChoicePanel.add(backColorWhiteButton);

        // color gray
        backColorGrayButton = new JRadioButton(DocViewerConstants.BACK_COLOR_GRAY_LABEL);
        // set font
        backColorGrayButton.setFont(defFrameFont);
        // set color
        backColorGrayButton.setBackground(defWhiteColor);
        // add listener
        backColorGrayButton.addActionListener(settingsActionListener);
        backColorButtonGroup.add(backColorGrayButton);
        backColorChoicePanel.add(backColorGrayButton);

        // color yellow
        backColorYellowButton = new JRadioButton(DocViewerConstants.BACK_COLOR_YELLOW_LABEL);
        // set font
        backColorYellowButton.setFont(defFrameFont);
        // set color
        backColorYellowButton.setBackground(defWhiteColor);
        // add listener
        backColorYellowButton.addActionListener(settingsActionListener);
        backColorButtonGroup.add(backColorYellowButton);
        backColorChoicePanel.add(backColorYellowButton);

        // color green
        backColorGreenButton = new JRadioButton(DocViewerConstants.BACK_COLOR_GREEN_LABEL);
        // set font
        backColorGreenButton.setFont(defFrameFont);
        // set color
        backColorGreenButton.setBackground(defWhiteColor);
        // add listener
        backColorGreenButton.addActionListener(settingsActionListener);
        backColorButtonGroup.add(backColorGreenButton);
        backColorChoicePanel.add(backColorGreenButton);

        return backColorPanel;
    }

    /**
     * get font size panel
     *
     * @return a panel with font size label
     */
    private Component getFontSizePanel() {
        fontSizePanel = new JPanel(new GridLayout(
                DocViewerConstants.BRI_DAR_LAYOUT_ROWS,
                DocViewerConstants.BRI_DAR_LAYOUT_COLS));
        // set color
        fontSizePanel.setBackground(defWhiteColor);
        // set margin
        fontSizePanel.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_BRI_DAR_MARGIN,
                DocViewerConstants.LEFT_MARGIN,
                DocViewerConstants.BOTTOM_BRI_DAR_MARGIN,
                DocViewerConstants.RIGHT_MARGIN));
        // label panel
        fontSizeLabelPanel = new JPanel(new BorderLayout());
        fontSizeLabelPanel.setBackground(defWhiteColor);

        // font size panel
        fontSizeChoicePanel = new JPanel(new GridLayout(
                DocViewerConstants.BRI_DAR_CHOICE_LAYOUT_ROWS,
                DocViewerConstants.BRI_DAR_CHOICE_LAYOUT_COLS));
        // set color
        fontSizeChoicePanel.setBackground(defFrameColor);
        fontSizePanel.add(fontSizeLabelPanel);
        fontSizePanel.add(fontSizeChoicePanel);

        // font Size label
        fontSizeLabel = new JLabel(DocViewerConstants.FONT_SIZE_LABEL);
        fontSizeLabel.setHorizontalAlignment(JLabel.CENTER);
        fontSizeLabel.setFont(defFrameFont);
        fontSizeLabelPanel.add(fontSizeLabel, BorderLayout.CENTER);

        // minus button
        fontMinusButton = new JButton(DocViewerConstants.FONT_MINUS_LABEL);
        // set margin
        fontMinusButton.setBorder(BorderFactory.createMatteBorder(
                DocViewerConstants.TOP_FONT_SIZE_MARGIN,
                DocViewerConstants.LEFT_FONT_SIZE_MARGIN,
                DocViewerConstants.BUTTON_FONT_SIZE_MARGIN,
                DocViewerConstants.RIGHT_FONT_SIZE_MARGIN,
                defWhiteColor));
        // set font
        fontMinusButton.setFont(defFrameFont);
        // set color
        fontMinusButton.setBackground(defFrameColor);
        // set align
        fontMinusButton.setHorizontalAlignment(JLabel.CENTER);
        // add listener
        fontMinusButton.addActionListener(settingsActionListener);
        fontSizeChoicePanel.add(fontMinusButton);

        // plus button
        fontPlusButton = new JButton(DocViewerConstants.FONT_PLUS_LABEL);
        // set margin
        fontPlusButton.setBorder(BorderFactory.createMatteBorder(
                DocViewerConstants.TOP_FONT_SIZE_MARGIN,
                DocViewerConstants.LEFT_FONT_SIZE_MARGIN,
                DocViewerConstants.BUTTON_FONT_SIZE_MARGIN,
                DocViewerConstants.RIGHT_FONT_SIZE_MARGIN,
                defWhiteColor));
        // set font
        fontPlusButton.setFont(defFrameFont);
        // set color
        fontPlusButton.setBackground(defFrameColor);
        // set align
        fontPlusButton.setHorizontalAlignment(JLabel.CENTER);
        // add listener
        fontPlusButton.addActionListener(settingsActionListener);
        fontSizeChoicePanel.add(fontPlusButton);

        return fontSizePanel;
    }

    /**
     * get bright and dark panel
     *
     * @return a panel with dark and bright radio button
     */
    private Component getBriDarPanel() {
        briDarPanel = new JPanel(new GridLayout(
                DocViewerConstants.BRI_DAR_LAYOUT_ROWS,
                DocViewerConstants.BRI_DAR_LAYOUT_COLS));
        // set color
        briDarPanel.setBackground(defWhiteColor);
        // set margin
        briDarPanel.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_BRI_DAR_MARGIN,
                DocViewerConstants.LEFT_MARGIN,
                DocViewerConstants.BOTTOM_BRI_DAR_MARGIN,
                DocViewerConstants.RIGHT_MARGIN));
        // label panel
        briDarLabelPanel = new JPanel(new BorderLayout());
        briDarLabelPanel.setBackground(defWhiteColor);

        // radio group
        JPanel briDarChoicePanel = new JPanel(new GridLayout(
                DocViewerConstants.BRI_DAR_CHOICE_LAYOUT_ROWS,
                DocViewerConstants.BRI_DAR_CHOICE_LAYOUT_COLS));
        briDarPanel.add(briDarLabelPanel);
        briDarPanel.add(briDarChoicePanel);

        // model label
        briDarLabel = new JLabel(DocViewerConstants.BRI_DAR_MODEL_LABEL);
        briDarLabel.setHorizontalAlignment(JLabel.CENTER);
        briDarLabel.setFont(defFrameFont);
        briDarLabelPanel.add(briDarLabel, BorderLayout.CENTER);

        // button group
        ButtonGroup briDarButtonGroup = new ButtonGroup();
        brightButton = new JRadioButton(DocViewerConstants.BRI_DAR_BUTTON_BRIGHT_LABEL);
        // set font
        brightButton.setFont(defFrameFont);
        // set color
        brightButton.setBackground(defWhiteColor);
        // set selected
        brightButton.setSelected(Boolean.TRUE);
        // set click listener
        brightButton.addActionListener(settingsActionListener);
        briDarButtonGroup.add(brightButton);
        briDarChoicePanel.add(brightButton);

        // dark button
        darkButton = new JRadioButton(DocViewerConstants.BRI_DAR_BUTTON_DARK_LABEL);
        // set font
        darkButton.setFont(defFrameFont);
        // set color
        darkButton.setBackground(defWhiteColor);
        // set click listener
        darkButton.addActionListener(settingsActionListener);

        briDarButtonGroup.add(darkButton);
        briDarChoicePanel.add(darkButton);

        return briDarPanel;
    }

    /**
     * Add area of middle area
     */
    private void addMiddleArea() {
        // main area
        midAreaPanel = new JPanel(new BorderLayout());
        midAreaPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        midAreaPanel.setBackground(defFrameColor);

        // control panel of mid left
        Component midLeftSwitcher = addMidLeftSwitcher();
        midAreaPanel.add(midLeftSwitcher, BorderLayout.WEST);

        // main viewer
        Component midRightViewer = addMidRightViewer();
        midAreaPanel.add(midRightViewer, BorderLayout.CENTER);

        contentPane.add(midAreaPanel, BorderLayout.CENTER);
    }

    /**
     * Add a controller and tabbed
     *
     * @return return a tabbed pane on left
     */
    private Component addMidLeftSwitcher() {
        midLeftTabbedPane = new JTabbedPane();
        // set color
        midLeftTabbedPane.setBackground(defFrameColor);
        // set height
        midLeftTabbedPane.setPreferredSize(
                new Dimension(DocViewerConstants.MID_LEFT_WIDTH, getHeight()));
        // set font
        midLeftTabbedPane.setFont(defFrameFont);
        // set tab to left
        midLeftTabbedPane.setTabPlacement(SwingConstants.LEFT);

        // load area of mid left
        Component midLeftLoadPanel = getMidLeftLoadArea();
        midLeftTabbedPane.addTab(DocViewerConstants.MID_LEFT_LOAD, midLeftLoadPanel);

        // chapters of documents
        Component midLeftChaptersPanel = getMidLeftChapters();
        midLeftTabbedPane.addTab(DocViewerConstants.MID_LEFT_CHAPTERS, midLeftChaptersPanel);

        return midLeftTabbedPane;
    }

    /**
     * get chapters of documents
     *
     * @return chapters panel
     */
    private Component getMidLeftChapters() {
        // chapter list
        // TODO bug: when you click empty place of this list, the last one will be selected
        chapterList = new JList<String>(new String[]{DocViewerConstants.NO_CHAPTERS}) {
            @Override
            public int locationToIndex(Point location) {
                int index = super.locationToIndex(location);
                if (index != -1 && !getCellBounds(index, index).contains(location)) {
                    return -1;
                } else {
                    return index;
                }
            }
        };
        // set font
        chapterList.setFont(defFrameFont);
        // set color
        chapterList.setBackground(defWhiteColor);
        // set margin
        chapterList.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_MARGIN,
                DocViewerConstants.LEFT_MARGIN,
                DocViewerConstants.BOTTOM_MARGIN,
                DocViewerConstants.RIGHT_MARGIN));

        // set listener
        chapterList.addMouseListener(listEmptyClickListener);
        chapterList.addListSelectionListener(chapterSelectionListener);
        return new JScrollPane(chapterList);
    }

    /**
     * get a panel of loading area
     *
     * @return a pane of loading area
     */
    private Component getMidLeftLoadArea() {
        midLeftPanel = new JPanel(new BorderLayout());
        // set color
        midLeftPanel.setBackground(defWhiteColor);
        // set margin
        midLeftPanel.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_MARGIN,
                DocViewerConstants.LEFT_MARGIN,
                DocViewerConstants.BOTTOM_MARGIN,
                DocViewerConstants.RIGHT_MARGIN));

        // load search and button
        JPanel midLeftLoadPanel = getMidLeftLoadPanel();
        midLeftLoadPanel.setBorder(BorderFactory.createEmptyBorder(
                DocViewerConstants.TOP_LOAD_MARGIN,
                DocViewerConstants.LEFT_LOAD_MARGIN,
                DocViewerConstants.BOTTOM_LOAD_MARGIN,
                DocViewerConstants.RIGHT_LOAD_MARGIN));
        midLeftPanel.add(midLeftLoadPanel, BorderLayout.NORTH);

        // document list
        docList = new JList<>(new String[]{DocViewerConstants.NO_DOCUMENTS});
        if (!docFileList.isEmpty()) {
            docList = new JList<String>(docFileList) {
                // TODO bug: when you click empty place of this list, the last one will sometime be selected
                //https://blog.csdn.net/zzy7075/article/details/84989830
                @Override
                public int locationToIndex(Point location) {
                    int index = super.locationToIndex(location);
                    if (index != -1 && !getCellBounds(index, index).contains(location)) {
                        return -1;
                    } else {
                        return index;
                    }
                }
            };
            docList.addMouseListener(listEmptyClickListener);
        }

        // set font
        docList.setFont(defFrameFont);
        docList.setBackground(defWhiteColor);
        // set Listener
        docList.addListSelectionListener(docListSelectionListener);
        midLeftPanel.add(new JScrollPane(docList), BorderLayout.CENTER);
        return midLeftPanel;
    }

    /**
     * get load panel
     *
     * @return a load panel
     */
    private JPanel getMidLeftLoadPanel() {
        midLeftLoadPanel = new JPanel(new GridBagLayout());
        midLeftLoadPanel.setBackground(defWhiteColor);
        // constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        // search field
        constraints.weightx = DocViewerConstants.WEIGHTX_ONE;
        searchField = new JTextField();
        searchField.setBackground(defWhiteColor);
        searchField.setFont(defFrameFont);
        // setListener
        searchField.getDocument().addDocumentListener(loadNameChangeListener);
        midLeftLoadPanel.add(searchField, constraints);

        // load button
        constraints.weightx = DocViewerConstants.WEIGHTX_ZERO;
        loadButton = new JButton(DocViewerConstants.LOAD_BUTTON);
        loadButton.setFont(defFrameFont);
        loadButton.setBackground(defWhiteColor);
        // set Listener
        loadButton.addActionListener(loadAndDisActionListener);
        midLeftLoadPanel.add(loadButton, constraints);
        return midLeftLoadPanel;
    }

    /**
     * Add main viewer area
     *
     * @return a viewer Panel
     */
    private Component addMidRightViewer() {
        JPanel viewAreaPanel = new JPanel(new BorderLayout());

        // view show panel
        Component viewShowPanel = getViewShowPanel();
        viewAreaPanel.add(viewShowPanel, BorderLayout.NORTH);

        // main viewer
        viewerArea = new JTextArea();
        viewerArea.setEditable(Boolean.FALSE);
        viewerArea.setBackground(defWhiteColor);
        viewerArea.setFont(defFrameFont);
        JScrollPane viewerScrollPane = new JScrollPane(viewerArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        viewAreaPanel.add(viewerScrollPane, BorderLayout.CENTER);

        return viewAreaPanel;
    }

    /**
     * get a panel with a label and button
     *
     * @return a view show panel
     */
    private Component getViewShowPanel() {
        viewNamePanel = new JPanel(new GridLayout(
                DocViewerConstants.VIEW_NAME_PANEL_ROWS,
                DocViewerConstants.VIEW_NAME_PANEL_COLS));
        // set color and font
        viewNamePanel.setBackground(defFrameColor);
        viewNamePanel.setFont(defFrameFont);

        viewShowPanel = new JPanel(new GridBagLayout());
        viewShowPanel.setBackground(defFrameColor);
        // constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        // selected doc name
        constraints.weightx = DocViewerConstants.WEIGHTX_ONE;
        selectedFiled = new JTextField(DocViewerConstants.NO_SELECTED_DOC);
        selectedFiled.setBackground(defFrameColor);
        selectedFiled.setFont(defFrameFont);
        selectedFiled.setEditable(Boolean.FALSE);
        selectedFiled.setEnabled(Boolean.FALSE);

        //set length
        selectedFiled.setPreferredSize(new Dimension(
                DocViewerConstants.MID_SELECTED_FIELD_WIDTH,
                DocViewerConstants.MID_SELECTED_FIELD_HEIGHT));
        viewShowPanel.add(selectedFiled, constraints);

        constraints.weightx = DocViewerConstants.WEIGHTX_ZERO;
        disButton = new JButton(DocViewerConstants.DISPLAY_TEXT);
        disButton.setFont(defFrameFont);
        disButton.setBackground(defFrameColor);
        // add listener
        disButton.addActionListener(loadAndDisActionListener);
        viewShowPanel.add(disButton, constraints);
        viewNamePanel.add(viewShowPanel);

        // add displayed name
        Component disNamePane = getDisNamePanel();
        viewNamePanel.add(disNamePane);

        return viewNamePanel;
    }

    /**
     * get displayed name panel
     *
     * @return a panel contains name and label
     */
    private Component getDisNamePanel() {
        disNamePanel = new JPanel();
        //set color and font
        disNamePanel.setBackground(defFrameColor);
        disNamePanel.setFont(defFrameFont);

        // add displayed name
        disNameInfoLabel = new JLabel(DocViewerConstants.DISPLAY_DOC_NAME_INFO);
        disNameInfoLabel.setBackground(defFrameColor);
        disNameInfoLabel.setFont(defFrameFont);
        disNamePanel.add(disNameInfoLabel);

        disNameLabel = new JLabel(DocViewerConstants.DISPLAY_DOC_NAME);
        disNameLabel.setBackground(defFrameColor);
        disNameLabel.setFont(defFrameFont);
        disNamePanel.add(disNameLabel);

        return disNamePanel;
    }

    /**
     * Add a control bar at bottom of frame
     */
    private void addBottomControlPane() {
        // control pane
        bottomControlPane = new JPanel(
                new GridLayout(DocViewerConstants.BOTTOM_LAYOUT_ROWS,
                        DocViewerConstants.BOTTOM_LAYOUT_COLS));
        // set color
        bottomControlPane.setBackground(defFrameColor);
        // set height
        bottomControlPane.setPreferredSize(
                new Dimension(getWidth(), DocViewerConstants.BOTTOM_CON_HEIGHT));
        // set font
        bottomControlPane.setFont(defFrameFont);

        // model
        modelLabel = new JLabel(DocViewerConstants.BRIGHT_MODEL);
        modelLabel.setFont(defFrameFont);
        modelLabel.setToolTipText(DocViewerConstants.MODEL_INFO);
        bottomControlPane.add(modelLabel);

        // Page
        JPanel pagePanel = getPagePanel();
        bottomControlPane.add(pagePanel);

        // Words
        wordsPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        wordsPane.setBackground(defFrameColor);
        wordsLabel = new JLabel(DocViewerConstants.WORDS_LABEL);
        wordsLabel.setFont(defFrameFont);
        wordsLabel.setBackground(defFrameColor);
        wordsNumLabel = new JLabel(DocViewerConstants.NUMBER_HOLDER);
        wordsNumLabel.setFont(defFrameFont);
        wordsPane.add(wordsLabel);
        wordsPane.add(wordsNumLabel);
        bottomControlPane.add(wordsPane);

        // add to bottom of content panel
        contentPane.add(bottomControlPane, BorderLayout.SOUTH);
    }

    /**
     * get panel of paging info
     *
     * @return a panel contains paging info
     */
    private JPanel getPagePanel() {
        // page Panel
        pagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // set color
        pagePanel.setBackground(defFrameColor);

        // home page of doc
        lLeftLabel = new JLabel(DocViewerConstants.HOME_PAGE_LABEL);
        lLeftLabel.setToolTipText(DocViewerConstants.HOME_OF_DOC);
        lLeftLabel.setFont(defFrameFont);
        // add listener
        lLeftLabel.addMouseListener(pageHelperMouseListener);

        // previous page
        leftLabel = new JLabel(DocViewerConstants.PREV_PAGE_LABEL);
        leftLabel.setFont(defFrameFont);
        leftLabel.setToolTipText(DocViewerConstants.PREV_PAGE);
        // add listener
        leftLabel.addMouseListener(pageHelperMouseListener);

        // page number
        pageLabel = new JLabel(DocViewerConstants.NUMBER_HOLDER);
        pageLabel.setBackground(defFrameColor);
        pageLabel.setFont(defFrameFont);

        // total page of document
        pagesLabel = new JLabel(DocViewerConstants.TOTAL_PAGES_INFO);
        pagesLabel.setFont(defFrameFont);
        pagesNumLabel = new JLabel(DocViewerConstants.NUMBER_HOLDER);
        pagesNumLabel.setFont(defFrameFont);

        // next page
        rightLabel = new JLabel(DocViewerConstants.NEXT_PAGE_LABEL);
        rightLabel.setFont(defFrameFont);
        rightLabel.setToolTipText(DocViewerConstants.NEXT_PAGE);
        // add listener
        rightLabel.addMouseListener(pageHelperMouseListener);

        // end of document
        rRightLabel = new JLabel(DocViewerConstants.END_OF_DOC_LABEL);
        rRightLabel.setFont(defFrameFont);
        rRightLabel.setToolTipText(DocViewerConstants.END_OF_DOC);
        // add listener
        rRightLabel.addMouseListener(pageHelperMouseListener);

        pagePanel.add(lLeftLabel);
        pagePanel.add(leftLabel);
        pagePanel.add(pageLabel);
        pagePanel.add(pagesLabel);
        pagePanel.add(pagesNumLabel);
        pagePanel.add(rightLabel);
        pagePanel.add(rRightLabel);
        return pagePanel;
    }
}
