package org.nrj.repo;

import org.nrj.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Neeraj Suthar
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}
 