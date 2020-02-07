package com.nvpiao.entity.base;

import com.nvpiao.enums.DocumentTypes;
import com.nvpiao.utils.CalculateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.nvpiao.entity.base.BaseDocument.java
 * <p>
 * A simple abstract class of for com.nvpiao.entity.base.BaseDocument
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public abstract class BaseDocument {

    /**
     * name of file
     */
    private String fileName;

    /**
     * type of documents
     */
    private DocumentTypes docType;

    /**
     * title of documents
     */
    private String title;

    /**
     * author of documents
     */
    private String author;

    /**
     * year of documents
     */
    private int year;

    /**
     * total words of documents
     */
    private int totalWords;

    /**
     * list of page
     */
    private List<String> docPages;

    /**
     * top 10 frequency words
     */
    private Map<String, Integer> topTenWords;

    /**
     * total page
     */
    private Integer totalPage;

    /**
     * current pages of poems
     */
    private int currentPages = 1;

    /**
     * chapter to page
     */
    private Map<String, Integer> chapterToPage;

    public BaseDocument() {
        chapterToPage = new HashMap<>();
    }

    public BaseDocument(DocumentTypes docType) {
        this();
        this.docType = docType;
    }

    public BaseDocument(String docTypeStr) {
        this();
        this.docType = DocumentTypes.valueOf(docTypeStr.toUpperCase());
    }

    public Map<String, Integer> getTopTenWords() {
        return topTenWords;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public List<String> getDocPages() {
        return docPages;
    }

    public void setDocPages(List<String> docPages) {
        this.docPages = docPages;
        this.totalPage = docPages.size();
        this.totalWords = CalculateUtils.totalWords(docPages);
        this.topTenWords = CalculateUtils.topTenWords(docPages);
    }

    public Map<String, Integer> getChapterToPage() {
        return chapterToPage;
    }

    public void setChapterToPage(Map<String, Integer> chapterToPage) {
        this.chapterToPage = chapterToPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPages() {
        return currentPages;
    }

    public void setCurrentPages(int currentPages) {
        this.currentPages = currentPages;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DocumentTypes getDocType() {
        return docType;
    }

    public void setDocType(DocumentTypes docType) {
        this.docType = docType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setYear(String yearStr) {
        this.year = Integer.parseInt(yearStr);
    }
}
