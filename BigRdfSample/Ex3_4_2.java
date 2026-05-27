import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Ex3_4_2 {

    static public void main(String[] args) throws FileNotFoundException {
        // RDFを操作する為のModelを作成
        Model model = ModelFactory.createDefaultModel();

        // RDFの形式を指定して読み込む
        // model.read(file.getAbsolutePath(), "RDF") ;
        model.read("Q1490.nt", "N-TRIPLE");
        // model.read(file.getAbsolutePath(), "TURTLE") ;

        /*
         * 以下のコードは，
         * https://github.com/KnowledgeGraphJapan/Apache-Jena-Sample-Programs/tree/
         * master/src/main/java/jp/riken/accc/lod/symposium/sample
         * を参考にして実装
         * 
         */

        // クエリの作成
        String queryStr = """
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                PREFIX skos: <http://www.w3.org/2004/02/skos/core#>

                SELECT ?s ?p ?label
                WHERE {
                  ?s ?p ?label .
                  FILTER((?p=rdfs:label)||(?p=skos:altLabel))
                  FILTER(LANG(?label) = "ja")
                }
                						""";
        Query query = QueryFactory.create(queryStr);

        // クエリの実行
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        try {

            FileOutputStream out;
            out = new FileOutputStream("SPARQL-output2.txt");

            // クエリの実行.
            ResultSet rs = qexec.execSelect();

            // 結果の出力 ※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
            // ResultSetFormatter.out(System.out, rs, query); //表形式で，標準出力に
            // ResultSetFormatter.out(out, rs, query); //表形式で，ファイルに
            // ResultSetFormatter.outputAsCSV(System.out, rs); //CSV形式で，標準出力に
            ResultSetFormatter.outputAsCSV(out, rs); // CSV形式で，ファイルに

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}