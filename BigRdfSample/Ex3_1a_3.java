import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;


/* SPARQL Endpoint に対するクエリ例
 * 注）Proxyの設定が必要な環境で実行するときは，実行時のJVMのオプションとして
 *      -DproxySet=true -DproxyHost=wwwproxy.osakac.ac.jp -DproxyPort=8080
 *     を追加する，
 *     Eclipseの場合「実行の構成＞引数」で設定可能
 * /
 */

public class Ex3_1a_3 {

	static public void main(String[] args) throws FileNotFoundException{

		//クエリの作成
		String queryStr = """
				PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
				PREFIX wd: <http://www.wikidata.org/entity/>
				PREFIX wdt: <http://www.wikidata.org/prop/direct/>
				select ?s ?p ?o
				where{
					?s rdfs:label "大阪府"@ja.
					?s wdt:P31 wd:Q50337.
					?s ?p ?o.
				}
				""";
		
		try{
			  //入力するファイルの設定
			  File file = new File("pref-ja.txt");
			  BufferedReader br = new BufferedReader(new FileReader(file));

			//出力するファイルの設定
			  File file_out = new File("output3-1_pref3.txt");
			  FileOutputStream out = new FileOutputStream(file_out);

			  String str;
			  
			  while((str = br.readLine()) != null){
				  
				  	System.out.println(str);
					Query query = QueryFactory.create(queryStr.replace("大阪府",str));

					 // Remote execution.
			        try ( QueryExecution qExec = QueryExecutionHTTP.create()
			                    .endpoint("https://query.wikidata.org/sparql")
			                    .query(query)
			                    .param("timeout", "10000")
			                    .build() ) {
			        		
			            // Execute.
			            ResultSet rs = qExec.execSelect();
			            
			            //ResultSetFormatter.out(System.out, rs, query);		//表形式で，
			            ResultSetFormatter.outputAsCSV(out, rs);
			        }
				  
			  }
			  
			  br.close();
			  out.close();

		  }catch(Exception e){
			  System.out.println(e);
		  }
        
        }
     }
