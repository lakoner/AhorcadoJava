package com.uoc.projectseven.repositories;

import com.uoc.projectseven.model.GameWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameWordRepository extends JpaRepository<GameWord, Integer> {

}
