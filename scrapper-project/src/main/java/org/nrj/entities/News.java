package org.nrj.entities;

import java.util.List;

/**
 * 
 * @author Neeraj Suthar
 *
 */
public class News {
    
    private Integer totalResults;
    private List<Article> articles;
    
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

    

}
