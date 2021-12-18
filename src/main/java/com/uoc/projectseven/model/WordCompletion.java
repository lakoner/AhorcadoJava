package com.uoc.projectseven.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;

@Embeddable
public class WordCompletion {

    private int index;
    private String character;
    @ColumnDefault("false")
    private boolean marked;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
