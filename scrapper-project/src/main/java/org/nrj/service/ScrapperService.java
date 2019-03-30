package org.nrj.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.nrj.entities.Article;
import org.nrj.entities.News;
import org.nrj.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class to serve the requests and generate the news articles according to the request received.
 * 
 * @author Neeraj Suthar
 *
 */
@Service
public class ScrapperService {

	@Autowired
	private ArticleRepository articleRepo;

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Method to save Articles.
	 * 
	 * @param apiKey
	 * @param articleList
	 * @return
	 */
	public String saveArticles(String apiKey, List<Article> articleList) {
		log.info("Request received to save news articles.");
		
		long initCount = articleRepo.count();
		
		if(initCount!=0) {

			log.debug("Data already present and cannot be saved.");

			return "Data already present and cannot be saved.";
		}

		log.debug(initCount + " articles present as of now.");
		
		if (null==articleList) {
			
			String url = "https://newsapi.org/v2/everything?"
					+ "sources=the-hindu"
					+ "&language=en&"
					+ "apiKey="+apiKey;

			log.debug("Scrapping news articles from The Hindu using this link: " + url);

			ResponseEntity<News> news = restTemplate.getForEntity(url, News.class);
			
			articleList = news.getBody().getArticles();
		} 
		
		log.debug("Saving news articles.");
		
		for (Article article : articleList) {
			if(null!=article.getAuthor() || article.getAuthor()!="") {
				articleRepo.save(article);
			}
		}

		long finalCount = articleRepo.count();
		
		log.debug(finalCount + " articles inserted.");

		if(finalCount<=initCount) {
			
			log.debug("Request failed.");
			
			return "Failed to Save.";
		}
		return "Success";
	}

	/**
	 * Method to get all the news articles.
	 * 
	 * @return
	 */
	public List<Article> getArticles() {

		log.info("Request received get all news articles.");

		return (List<Article>) articleRepo.findAll();
	}
	
	/**
	 * Method to get all the articles for a particular authors.
	 * 
	 * @param author
	 * @return
	 */
	public List<Article> getArticlesByAuthor(String author) {
		
		log.info("Request received get all news articles for the author "+ author.replace("\"", "") +".");

		List<Article> authorArticles = articleRepo
				.findAll()
				.stream()
				.filter(a->a.getAuthor().toLowerCase()
						.contains(author.replace("\"", "").toLowerCase()))
				.collect(Collectors.toList());

		return authorArticles;
	}

	/**
	 * Method to get all the articles for a particular title.
	 * 
	 * @param title
	 * @return
	 */
	public List<Article> getArticlesByTitle(String title) {

		log.info("Request received get all news articles matching the title "+ title.replace("\"", "") +".");

		List<Article> titleArticles = articleRepo
				.findAll()
				.stream()
				.filter(a->a.getTitle().toLowerCase()
						//removing quotes inserted in request parameter as title contains spaces
						.contains(title.replace("\"", "").toLowerCase()))
				.collect(Collectors.toList());
	
		return titleArticles;
	}

	/**
	 * Method to get all the titles.
	 * 
	 * @return
	 */
	public Map<String, String> getAllTitles() {
		
		log.info("Request received get all the news titles.");

		return generateMapFromSet("title",articleRepo
				.findAll()
				.stream()
				.map(e->e.getTitle()).collect(Collectors.toSet()) );
	}

	/**
	 * Method to get all the authors.
	 * 
	 * @return
	 */
	public Map<String, String> getAllAuthors() {
		
		log.info("Request received get all the authors.");
		
		return generateMapFromSet("author", articleRepo
				.findAll().
				stream().
				map(e->e.getAuthor()).collect(Collectors.toSet()));
	}
	
	/**
	 * Method to generate Map using Set.
	 * 
	 * @param keyStr
	 * @param set
	 * @return
	 */
	private Map<String, String> generateMapFromSet(String keyStr, Set<String> set){
		
		log.debug("Generating Map using the Set of " + keyStr + ".");
		
		Map<String, String> map = new LinkedHashMap<>();
		int i=0;
		for (String key : set) {
			map.put(keyStr+"_"+(i++), key);
		}
		return map;
	}



}
