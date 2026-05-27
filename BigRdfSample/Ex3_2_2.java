import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;

public class Ex3_2_2 {
	public static void main(String[] args) {

		String queryStr = """
				PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
				select ?s ?p ?o
				where{
				?s rdfs:label "#COUNTRY#"@ja.
				?s ?p ?o.
				}
				""";
		String ua = "Example-Agent/1.0 (https://www.example.com; xxx@example.com)";

		String endpoint = "http://kozaki-lab.osakac.ac.jp/agraph/DBpedia2016_RDF";

		// Fileの入出力など「エラーが発生する可能性」がある場合，
		// try{．．．．}catch(．．){．．．}というエラー処理の構文を使う必要がある
		try {
			// 入力するファイルの設定
			File file = new File("COUNTRY.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));

			// 出力用のファイルの作成
			FileOutputStream out = new FileOutputStream("dbp-output.txt");

			// ここにreadLine()を使った処理を書く
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);

				Query query = QueryFactory.create(queryStr.replace("#COUNTRY#", str));
				System.out.println(queryStr.replace("#COUNTRY#", str));

				try (QueryExecution qExec = QueryExecutionHTTP.create()
						.endpoint(endpoint)
						.query(query)
						.httpHeader("User-Agent", ua)
						.param("timeout", "100000")
						.build()) {

					// Execute.
					ResultSet rs = qExec.execSelect();

					// 結果の出力 ※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
					// ResultSetFormatter.out(out, rs, query); //表形式で，ファイルに
					ResultSetFormatter.outputAsCSV(out, rs); // CSV形式で，ファイルに
				}
			}

			// close()を忘れると正しく出力が終わらない
			br.close();
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}