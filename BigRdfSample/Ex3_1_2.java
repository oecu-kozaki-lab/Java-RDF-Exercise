package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;

public class Ex3_1_2 {
	public static void main(String[] args) {

		String queryStr = """
				PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
				PREFIX wdt: <http://www.wikidata.org/prop/direct/>
				select ?s
				where{
				?s rdfs:label "#PREF#"@ja.
				?s wdt:P429 ?code.
				}
				""";
		String ua = "Example-Agent/1.0 (https://www.example.com; xxx@example.com)";
		
		//String endpoint = "https://query.wikidata.org/sparql";
		// Wkidataのエンドポイントでは、プログラムからのアクセス制限が厳しい場合があるので、
		// 下記のWikidataのコピーエンドポイントを使う
		String endpoint = "http://ie-expt.kzlab.osakac.ac.jp:8890/sparql/";
		
		// Fileの入出力など「エラーが発生する可能性」がある場合，
		// try{．．．．}catch(．．){．．．}というエラー処理の構文を使う必要がある
		try {
			// 入力するファイルの設定
			File file = new File("pref-ja.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));

			// 出力するファイルの設定
			File file_out = new File("output.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file_out));

			// ここにreadLine()を使った処理を書く
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);

				Query query = QueryFactory.create(queryStr.replace("#PREF#", str));

				try (QueryExecution qExec = QueryExecutionHTTP.create()
						.endpoint(endpoint)
						.query(query)
						.httpHeader("User-Agent", ua)
						.param("timeout", "100000")
						.build()) {

					// Execute.
					ResultSet rs = qExec.execSelect();

					// 結果の出力 ※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
					// ResultSetFormatter.out(System.out, rs, query); //表形式で，標準出力に
					ResultSetFormatter.outputAsCSV(System.out, rs); // CSV形式で，標準出力に
				}
			}

			// close()を忘れると正しく出力が終わらない
			br.close();
			bw.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}