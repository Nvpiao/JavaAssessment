package com.nvpiao.listener;

import com.nvpiao.constants.DocViewerConstants;
import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.frame.DocViewerFrame;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * com.nvpiao.listener.SettingsActionListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.SettingsActionListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public class SettingsActionListener implements ActionListener {
    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    /**
     * Default color of frame
     */
    private Color defFrameColor;

    /**
     * Default white color
     */
    private Color defWhiteColor;

    /**
     * Reverse default color of frame
     */
    private Color defFrameReverseColor;

    /**
     * Reverse default white color
     */
    private Color defWhiteReverseColor;

    public SettingsActionListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;

        defFrameColor = new Color(DocViewerConstants.GRAY_WHITE,
                DocViewerConstants.GRAY_WHITE,
                DocViewerConstants.GRAY_WHITE);
        defWhiteColor = Color.WHITE;

        defFrameReverseColor = new Color(DocViewerConstants.GRAY_BLACK,
                DocViewerConstants.GRAY_BLACK,
                DocViewerConstants.GRAY_BLACK);
        defWhiteReverseColor = Color.BLACK;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case DocViewerConstants.BRI_DAR_BUTTON_BRIGHT_LABEL:
                changeBrightTheme();
                break;
            case DocViewerConstants.BRI_DAR_BUTTON_DARK_LABEL:
                changeDarkTheme();
                break;
            case DocViewerConstants.FONT_MINUS_LABEL:
                setViewerFontSize(Boolean.FALSE);
                break;
            case DocViewerConstants.FONT_PLUS_LABEL:
                setViewerFontSize(Boolean.TRUE);
                break;
            case DocViewerConstants.BACK_COLOR_WHITE_LABEL:
            case DocViewerConstants.BACK_COLOR_GRAY_LABEL:
            case DocViewerConstants.BACK_COLOR_YELLOW_LABEL:
            case DocViewerConstants.BACK_COLOR_GREEN_LABEL:
                setViewerBackColor(command);
                break;
            default:
        }
    }

    /**
     * set viewer background color
     *
     * @param colorKey color key
     */
    private void setViewerBackColor(String colorKey) {
        Color color = DocViewerConstants.COLORS_MAP.get(colorKey);
        JTextArea viewerArea = docViewerFrame.getViewerArea();
        viewerArea.setForeground(Color.BLACK);
        viewerArea.setBackground(color);
    }

    /**
     * reset font size at textarea
     *
     * @param fontPlus if font need to be increased
     */
    private void setViewerFontSize(Boolean fontPlus) {
        // return when no file is displayed
        BaseDocument currentDoc = docViewerFrame.getCurrentDoc();
        if (null == currentDoc) {
            return;
        }

        // new font
        Font newFont;
        // old font
        Font oldFont = docViewerFrame.getViewerArea().getFont();
        int oldFontSize = oldFont.getSize();
        if (fontPlus) {
            // increase font size
            if (oldFontSize == DocumentConstants.MAXIMUM_FONT_SIZE) {
                // exceed maximum font size
                return;
            }
            newFont = new Font(oldFont.getName(), oldFont.getStyle(), oldFontSize + 1);
        } else {
            // decrease font size
            if (oldFontSize == DocumentConstants.MINIMUM_FONT_SIZE) {
                //exceed minimum font size
                return;
            }
            newFont = new Font(oldFont.getName(), oldFont.getStyle(), oldFontSize - 1);
        }

        docViewerFrame.getViewerArea().setFont(newFont);
    }

    /**
     * change into dark model
     */
    private void changeDarkTheme() {
        //TODO change color of font
        docViewerFrame.setFontColor(Color.LIGHT_GRAY);

        // change background
        docViewerFrame.setDefFrameColor(defFrameReverseColor);
        docViewerFrame.setDefWhiteColor(defWhiteReverseColor);
        // set label
        docViewerFrame.setModelLabel(DocViewerConstants.DARK_MODEL);
        // clear selection
        docViewerFrame.getBackColorButtonGroup().clearSelection();
        // refresh text area
        JTextArea viewerArea = docViewerFrame.getViewerArea();
        viewerArea.setForeground(Color.LIGHT_GRAY);
        viewerArea.paintImmediately(viewerArea.getBounds());
        // repaint
        docViewerFrame.repaint();
    }

    /**
     * change into bright model
     */
    private void changeBrightTheme() {
        //change font color
        docViewerFrame.setFontColor(Color.BLACK);

        // change background
        docViewerFrame.setDefFrameColor(defFrameColor);
        docViewerFrame.setDefWhiteColor(defWhiteColor);
        // set label
        docViewerFrame.setModelLabel(DocViewerConstants.BRIGHT_MODEL);
        // reset selection of color button
        docViewerFrame.getBackColorButtonGroup()
                .setSelected(docViewerFrame.getBackColorWhiteButton().getModel(),
                        Boolean.TRUE);
        // refresh text area
        JTextArea viewerArea = docViewerFrame.getViewerArea();
        viewerArea.setForeground(Color.BLACK);
        viewerArea.paintImmediately(viewerArea.getBounds());
        // repaint
        docViewerFrame.repaint();
    }
}
