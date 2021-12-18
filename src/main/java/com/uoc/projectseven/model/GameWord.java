package com.uoc.projectseven.model;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
public class GameWord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String word;
    @ElementCollection
    private List<WordCompletion> wordCompletion;
    private int mistakes;
    @ColumnDefault("6")
    private int mistakeThreshold;
    @OneToOne
    private Game game;


    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<WordCompletion> getWordCompletion() {
        return wordCompletion;
    }

    public void setWordCompletion(List<WordCompletion> wordCompletion) {
        this.wordCompletion = wordCompletion;
    }

    public int getMistakeThreshold() {
        return mistakeThreshold;
    }

    public void setMistakeThreshold(int mistakeThreshold) {
        this.mistakeThreshold = mistakeThreshold;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
