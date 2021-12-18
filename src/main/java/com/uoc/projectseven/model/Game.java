package com.uoc.projectseven.model;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Embedded
    private DifficultyLevel difficultyLevel;
    private LocalDateTime gameStartTime;
    private LocalDateTime gameEndTime;
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private GameWord gameWord;
    private String firstName;
    private String lastName;
    private String uid;

    public int getId() {
        return id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public LocalDateTime getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(LocalDateTime gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public LocalDateTime getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(LocalDateTime gameEndTime) {
        this.gameEndTime = gameEndTime;
    }

    public GameWord getGameWord() {
        return gameWord;
    }

    public void setGameWord(GameWord gameWord) {
        this.gameWord = gameWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
