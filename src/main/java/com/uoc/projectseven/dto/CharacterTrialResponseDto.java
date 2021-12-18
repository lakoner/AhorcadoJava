package com.uoc.projectseven.dto;

public class CharacterTrialResponseDto {

    public CharacterTrialResponseDto(String markedCharactersResponse, int mistakes, int noOfCharacters, String solvedWord, String elapsedTime) {
        this.markedCharactersResponse = markedCharactersResponse;
        this.mistakes = mistakes;
        this.noOfCharacters = noOfCharacters;
        this.solvedWord = solvedWord;
        this.elapsedTime = elapsedTime;
    }


    private String markedCharactersResponse;
    private int mistakes;
    private int noOfCharacters;
    private String solvedWord;
    private String elapsedTime;

    public String getMarkedCharactersResponse() {
        return markedCharactersResponse;
    }

    public void setMarkedCharactersResponse(String markedCharactersResponse) {
        this.markedCharactersResponse = markedCharactersResponse;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public int getNoOfCharacters() {
        return noOfCharacters;
    }

    public void setNoOfCharacters(int noOfCharacters) {
        this.noOfCharacters = noOfCharacters;
    }

    public String getSolvedWord() {
        return solvedWord;
    }

    public void setSolvedWord(String solvedWord) {
        this.solvedWord = solvedWord;
    }


    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
