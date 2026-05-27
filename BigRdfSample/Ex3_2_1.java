import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;

public class Ex3_2_1 {
	public static void main(String[] args) {

		String queryStr = """
				PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
				PREFIX wd: <http://www.wikidata.org/entity/>
				PREFIX wdt: <http://www.wikidata.org/prop/direct/>
				SELECT ?label WHERE {
				  ?s wdt:P31 wd:Q6256 .
				  ?s rdfs:label ?label.
				  FILTER(lang(?label)="ja")
				}
				""";
		String ua = "Example-Agent/1.0 (https://www.example.com; xxx@example.com)";

		// String endpoint = "https://query.wikidata.org/sparql";
		// Wkidataのエンドポイントでは、プログラムからのアクセス制限が厳しい場合があるので、
		// 下記のWikidataのコピーエンドポイントを使う
		String endpoint = "http://ie-expt.kzlab.osakac.ac.jp:8890/sparql/";

		// Fileの入出力など「エラーが発生する可能性」がある場合，
		// try{．．．．}catch(．．){．．．}というエラー処理の構文を使う必要がある
		try {
			Query query = QueryFactory.create(queryStr);

			try (QueryExecution qExec = QueryExecutionHTTP.create()
					.endpoint(endpoint)
					.query(query)
					.httpHeader("User-Agent", ua)
					.param("timeout", "100000")
					.build()) {

				// Execute.
				ResultSet rs = qExec.execSelect();

				// 出力用のファイルの作成
				FileOutputStream out = new FileOutputStream("contry.txt");

				// 結果の出力 ※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
				// ResultSetFormatter.out(out, rs, query); //表形式で，ファイルに
				ResultSetFormatter.outputAsCSV(out, rs); // CSV形式で，ファイルに

				out.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}