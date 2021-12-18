package com.uoc.projectseven.model;

import javax.persistence.*;

@Embeddable
public class DifficultyLevel {

    private int level;
    private String levelName;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
