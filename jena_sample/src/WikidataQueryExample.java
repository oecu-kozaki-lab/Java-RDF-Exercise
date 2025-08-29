/*
 * Jenaを用いた「Wikipediaクエリサービス」へのSPARQLクエリ実行のサンプル
 * 
 * 　注：User-Agentの設定をしないと、403エラーが出る
 * 
 */

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;

public class WikidataQueryExample
{
    static public void main(String[] args)
    {
    	//User-Agentの情報を設定（自分のシステムの情報に書き換える）
        String ua = "Example-Agent/1.0 (https://www.example.com; xxx@example.com)";

        //SPARQL Endpointの設定
    	String endpoint = "https://query.wikidata.org/sparql";
    	
    	//SPARQLクエリの記述
    	String queryStr = """
        					SELECT * 
        					where {
        						<http://www.wikidata.org/entity/Q17> ?p ?o .
        					} LIMIT 100
        				""";
        Query query = QueryFactory.create(queryStr);
        
                
        try ( QueryExecution qExec = QueryExecutionHTTP.create()
              .endpoint(endpoint)
                    .query(query)
                    .httpHeader("User-Agent", ua) 
                    .param("timeout", "10000")
                    .build() ) {
            
        	// クエリの実行
            ResultSet rs = qExec.execSelect();
            ResultSetFormatter.out(System.out, rs, query);
        }
    }
}
