package com.uoc.projectseven.repositories;

import com.uoc.projectseven.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface GameRepository extends JpaRepository<Game, Integer> {

    Game findById(int id);

    List<Game> findAllByGameEndTimeIsNotNull();

}
