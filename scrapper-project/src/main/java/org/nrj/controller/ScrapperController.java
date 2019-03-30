package org.nrj.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nrj.entities.Article;
import org.nrj.service.ScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for News Scrapper API
 * 
 * @author Neeraj Suthar
 *
 */
@RestController
@RequestMapping("/thehindu/articles")
public class ScrapperController {
	
	@Autowired
	private ScrapperService scrapperService; 

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(path="/authors", method=RequestMethod.GET)
	public Map<String, String> getAllAuthors() {
		
		return scrapperService.getAllAuthors();
	}
	
	@RequestMapping(path="/author", method=RequestMethod.GET)
	public List<Article> getAuthorArticles(@RequestParam(name="author")String author) {
		
		return scrapperService.getArticlesByAuthor(author);
	}
	
	@RequestMapping(path="/titles", method=RequestMethod.GET)
	public Map<String, String> getAllTitles() {

		return scrapperService.getAllTitles();
	}
	
	@RequestMapping(path="/title", method=RequestMethod.GET)
	public List<Article> getAtriclesByTitle(@RequestParam(name="title") String title) {
		
		return scrapperService.getArticlesByTitle(title);
	}
	
	@RequestMapping(path="/generate", method=RequestMethod.GET)
	public String generateArticles(@RequestParam(name="apikey")String key) {
		
		//Read and Save Articles in case the articles are not present.
		
		return scrapperService.saveArticles(key, null);
	}
	
	@RequestMapping(path="/save", method=RequestMethod.POST)
	public String saveArticles(@RequestBody List<Article> articles) {
		
		return scrapperService.saveArticles(null, articles);
	}
	
	@RequestMapping(path="/all", method=RequestMethod.GET)
	public List<Article> getAllArticles() {
		
		return scrapperService.getArticles();
		
	}
}
