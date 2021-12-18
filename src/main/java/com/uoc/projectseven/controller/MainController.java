package com.uoc.projectseven.controller;

import com.uoc.projectseven.dto.InitObject;
import com.uoc.projectseven.model.Game;
import com.uoc.projectseven.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String shouldRedirect() {
        return "t";
    }

    @GetMapping("/welcome")
    public String getWelcome(Model model) {
        Map<String, Long> ranking = gameService.getRanking();
        model.addAttribute("ranking", ranking);
        model.mergeAttributes(ranking);
        return "welcome";
    }

    @GetMapping("/game")
    public String getGame() {
        return "game";
    }

    @PostMapping("/initGame")
    public String initGame(@RequestParam("difficulty") int difficulty, Model model) {
        Game newGame = gameService.initGame(difficulty);
        model.addAttribute("gameId", newGame.getId());
        return "game";
    }

}
