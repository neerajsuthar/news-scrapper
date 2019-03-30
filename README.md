# news-scrapper instructions

To run the application, follow the steps below serially(One after the other).
Make sure you use a Rest client (for the convenience). I used Postman for this.

Step - 1:
Import the project from Git as a Maven Project and Build/Install the project
using Maven (mvn clean install).

Step - 2:
Run the project as Java Application/Spring Boot Application. The Spring Tool Suite
IDE Helps in running the application as Spring Boot Application. In case of Java
Application, Select NewsScrapperRunner.java as the main class.

Step - 3:
Please note, you can either follow 3.1, or 3.2. You can try both but application will generate error message.

Step - 3.1:
As I have used the newsapi.org and generated the news records for the source "The
Hindu", you can generate your API key from the link : https://newsapi.org/register.
Once generated, you can hit the URL to load the news articles from The Hindu.
The type of below request is GET.

http://localhost:2019/thehindu/articles/generate?apikey=<YOUR API KEY>

Step - 3.2:
In case you don't want to generate the API key, there is a sample JSON file present
in the resources folder with the name "news_data.json".
It contains the list of News Articles, which can be used as a test data for the
project. Copy the whole JSON List and paste in the Body textbox of Postman or any
other Rest Client. Make sure the content-type of Body is set as "application/json".
The type of below request is POST.

http://localhost:2019/thehindu/articles/save

Step - 4:
By following either of the steps(3.1 or 3.2), we have the news data now. We can
now perform the GET operations to get the required details:

Step - 4.1:
To get all the news articles.
http://localhost:2019/thehindu/articles/all

Step - 4.2:
To get all the news articles' titles.
http://localhost:2019/thehindu/articles/titles

Step - 4.3:
To get all the news articles' authors.
http://localhost:2019/thehindu/articles/authors

Step - 4.4:
To get all the news articles for a particular author.
http://localhost:2019/thehindu/articles/author?author=<AUTHOR NAME>

For Example : http://localhost:2019/thehindu/articles/author?author=Neeraj

or : http://localhost:2019/thehindu/articles/author?author="Neeraj Suthar"

Step - 4.5:
To get all the news articles for a particular title.
http://localhost:2019/thehindu/articles/title?title=<TITLE>

For Example : http://localhost:2019/thehindu/articles/title?title="Young hearts make art at"

or : http://localhost:2019/thehindu/articles/title?title=Young%20hearts%20make%20art%20at

