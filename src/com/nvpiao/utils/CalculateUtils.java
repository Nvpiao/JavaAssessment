package com.nvpiao.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.nvpiao.utils.CalculateUtils.java
 * <p>
 * A simple statistical class of for com.nvpiao.utils.CalculateUtils
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   25 December 2019
 */
public class CalculateUtils {
    /**
     * delimiter
     */
    private static final String DELIMITER = "\n";

    /**
     * regex
     * language=RegExp
     */
    private static final String WORDS_REGEX = "\\w+";

    /**
     * regex for words
     */
    private static Pattern wordReg;

    static {
        wordReg = Pattern.compile(WORDS_REGEX);
    }

    /**
     * calculate counts of word
     *
     * @param docText list of text
     * @return counts of word
     */
    public static int totalWords(List<String> docText) {
        return totalWords(String.join(DELIMITER, docText));
    }

    /**
     * calculate counts of word
     *
     * @param docText text
     * @return counts of word
     */
    public static int totalWords(String docText) {
        Matcher matcher = wordReg.matcher(docText);
        int wordCounts = 0;
        while (matcher.find()) {
            String word = matcher.group();
            ++wordCounts;
        }
        return wordCounts;
    }

    /**
     * calculate top10 frequency words
     *
     * @param docPages doc file
     * @return a map contains top10 words and frequency
     */
    public static Map<String, Integer> topTenWords(List<String> docPages) {
        return topTenWords(String.join(DELIMITER, docPages));
    }

    private static Map<String, Integer> topTenWords(String docPages) {
        HashMap<String, Integer> topTenWordsMap = new HashMap<>();

        // find words
        Matcher matcher = wordReg.matcher(docPages);
        while (matcher.find()) {
            String word = matcher.group();
            if (topTenWordsMap.containsKey(word)) {
                // add one if exist
                topTenWordsMap.put(word, topTenWordsMap.get(word) + 1);
            } else {
                // set new one
                topTenWordsMap.put(word, 1);
            }
        }

        return topTenWordsMap;
    }
}
