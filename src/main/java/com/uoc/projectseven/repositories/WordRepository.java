package com.uoc.projectseven.repositories;

import com.uoc.projectseven.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {

    Word findById(int id);

    List<Word> findAllByDifficultyLevel_Level(int level);
}
