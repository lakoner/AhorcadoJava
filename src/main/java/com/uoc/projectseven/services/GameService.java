package com.uoc.projectseven.services;


import com.uoc.projectseven.dto.CharacterTrialResponse;
import com.uoc.projectseven.model.*;
import com.uoc.projectseven.repositories.GameRepository;
import com.uoc.projectseven.repositories.GameWordRepository;
import com.uoc.projectseven.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    GameWordRepository gameWordRepository;

    public int getMistakesForGameId(int gameId) {
        return gameRepository.findById(gameId).getGameWord().getMistakes();
    }

    public int getNoOfCharactersForGameId(int gameId) {
        return gameRepository.findById(gameId).getGameWord().getWord().length();
    }

    public String getSolvedWord(int gameId) {
        return gameRepository.findById(gameId).getGameWord().getWord();
    }

    public String getDuration(int gameId) {
        Game game = gameRepository.findById(gameId);
        Duration duration = Duration.between(game.getGameStartTime(), game.getGameEndTime());
        long minutesElapsed = duration.getSeconds() ;
        return String.format("%02d:%02d", Math.round(minutesElapsed/60), (minutesElapsed % 60));
    }

    public Game initGame(int difficultyLevel) {
        Game game = new Game();
        game.setUid(((LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        List<Word> allWordsByDifficultyLevel = wordRepository.findAllByDifficultyLevel_Level(difficultyLevel);
        Word randomWord = allWordsByDifficultyLevel.get(new Random().nextInt(allWordsByDifficultyLevel.size()));
        game.setGameWord(castWordToGameWord(randomWord, game));
        game.setGameStartTime(LocalDateTime.now());
        DifficultyLevel difficultyLeveEntity = new DifficultyLevel();
        difficultyLeveEntity.setLevel(difficultyLevel);
        String difLevelName = "";
        switch (difficultyLevel) {
            case 1:
                difLevelName = "EASY";
                break;
            case 2:
                difLevelName = "INTERMEDIATE";
                break;
            case 3:
                difLevelName = "HARD";
                break;
        }
        difficultyLeveEntity.setLevelName(difLevelName);
        game.setDifficultyLevel(difficultyLeveEntity);
        return gameRepository.save(game);
    }

    public boolean checkIfSolved(List<WordCompletion> completion) {
        for (WordCompletion wordCompletion : completion) {
            if (!wordCompletion.isMarked()) {
                return false;
            }
        }
        return true;
    }

    public String tryLetter(int gameId, String character) {
        Game game = gameRepository.findById(gameId);
        GameWord gameWord = game.getGameWord();
        List<WordCompletion> wordCompletion = gameWord.getWordCompletion();
        boolean notFound = true;
        for (WordCompletion completion : wordCompletion) {
            if (completion.getCharacter().equals(character)) {
                notFound = false;
                if (completion.isMarked()) {
                    return CharacterTrialResponse.ALREADY_EXISTS.name();
                }
                completion.setMarked(true);
            }
        }
        if (notFound) {
            if (gameWord.getMistakes() == gameWord.getMistakeThreshold()) {
                return CharacterTrialResponse.YOU_ARE_DEAD.name();
            }
            gameWord.setMistakes(gameWord.getMistakes() + 1);
            gameRepository.save(game);
            return CharacterTrialResponse.DOES_NOT_EXIST.name();
        }
        String output = "";
        for (WordCompletion completion : wordCompletion) {
            String characterOutput = completion.isMarked() ? completion.getCharacter() : "_";
            output += characterOutput;
        }

        if (checkIfSolved(wordCompletion)) {
            game.setGameEndTime(LocalDateTime.now());
            gameRepository.save(game);
            return CharacterTrialResponse.YOU_SURVIVED.name();
        }

        gameRepository.save(game);
        return output;
    }

    public GameWord castWordToGameWord(Word word, Game game) {
        GameWord gameWord = new GameWord();
        gameWord.setWord(word.getWord());
        gameWord.setWordCompletion(new ArrayList<>());
        List<String> characters = Arrays.asList(gameWord.getWord().split(""));
        for (String character : characters) {
            WordCompletion wordCompletion = new WordCompletion();
            wordCompletion.setCharacter(character);
            wordCompletion.setIndex(characters.indexOf(character));
            gameWord.getWordCompletion().add(wordCompletion);
        }
        gameWord.setMistakeThreshold(6);
        gameWord.setGame(game);
        return gameWord;
    }

    public Map<String, Long> getRanking() {
        HashMap<String, Long> values = new HashMap<>();
        List<Game> gamesUnordered = gameRepository.findAllByGameEndTimeIsNotNull();
        for (Game game : gamesUnordered) {
            Duration duration = Duration.between(game.getGameStartTime(), game.getGameEndTime());
            if (values.containsKey(game.getUid())) {
                if (values.get(game.getUid()) > duration.getSeconds()) {
                    values.put(game.getUid(), duration.getSeconds());
                }
            } else {
                values.put(game.getUid(), duration.getSeconds());
            }
        }

        Map<String, Long> sortedMap =
                values.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
        return sortedMap;
    }

}
