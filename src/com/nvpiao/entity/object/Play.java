package com.nvpiao.entity.object;

import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.enums.DocumentTypes;

/**
 * com.nvpiao.entity.object.Play.java
 * <p>
 * A simple class for com.nvpiao.entity.object.Play
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public class Play extends BaseDocument {
    /**
     * number of acts
     */
    private int acts;

    /**
     * number of actors
     */
    private int actors;

    public Play() {
        super(DocumentTypes.PLAY);
    }

    public Play(int acts, int actors) {
        this();
        this.acts = acts;
        this.actors = actors;
    }

    public int getActs() {
        return acts;
    }

    public void setActs(int acts) {
        this.acts = acts;
    }

    public void setActs(String actStr) {
        this.acts = Integer.parseInt(actStr);
    }

    public int getActors() {
        return actors;
    }

    public void setActors(int actors) {
        this.actors = actors;
    }

    public void setActors(String actorStr) {
        this.actors = Integer.parseInt(actorStr);
    }

    @Override
    public String toString() {
        return "Document type: com.nvpiao.entity.object.Play\n"
                + "Title: " + getTitle() + "\n"
                + "Author: " + getAuthor() + "\n"
                + "Number of acts: " + getActs() + "\n"
                + "Number of actors: " + getActors() + "\n"
                + "Year: " + getYear();
    }
}
