package com.uoc.projectseven.controller;

import com.uoc.projectseven.dto.CharacterTrialResponse;
import com.uoc.projectseven.dto.CharacterTrialResponseDto;
import com.uoc.projectseven.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    GameService gameService;

    @PostMapping("/letterTry/{gameId}/{character}")
    public CharacterTrialResponseDto letterTry(@PathVariable int gameId, @PathVariable String character) {
        String trialResponse = gameService.tryLetter(gameId, character);
        String solvedWord = trialResponse.equals(CharacterTrialResponse.YOU_SURVIVED.name()) ? gameService.getSolvedWord(gameId) : "";
        String elapsedTime = trialResponse.equals(CharacterTrialResponse.YOU_SURVIVED.name()) ? gameService.getDuration(gameId) : "";
        return new CharacterTrialResponseDto(
                trialResponse,
                gameService.getMistakesForGameId(gameId),
                gameService.getNoOfCharactersForGameId(gameId),
                solvedWord,
                elapsedTime
        );
    }


    @PostMapping("/gameInfo/{gameId}")
    public CharacterTrialResponseDto letterTry(@PathVariable int gameId) {

        return new CharacterTrialResponseDto(
                null,
                0,
                gameService.getNoOfCharactersForGameId(gameId),
                null,
                null
        );
    }

}
