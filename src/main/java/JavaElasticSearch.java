import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class JavaElasticSearch {

	public static void main(String[] args) {
		
		//Conectando com o Elastic
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		//Buscando o índice "catálago"
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("catalago");
	    
		//Adiciona a pesquisa dentro da query
	    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	    
	    //Adiciona o match_all ao SearchSourceBuilder
	    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
	    
	    //Adiciona o SearchSourceBuilder ao SearchRequest
	    searchRequest.source(searchSourceBuilder);
	    
	    Map<String, Object> map = null;
	     
	    try {
	    	//O cliente espera que o SearchResponse seja retornado antes de continuar com a execução do código
	        SearchResponse searchResponse = null;
	        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
	        
	        if (searchResponse.getHits().getTotalHits().value > 0) {
	            SearchHit[] searchHit = searchResponse.getHits().getHits();
	            
	            for (SearchHit hit : searchHit) {
	                map = hit.getSourceAsMap();
	                  System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));
	                    
	                
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    


	}

}
