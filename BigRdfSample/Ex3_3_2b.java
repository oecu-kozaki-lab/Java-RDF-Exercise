import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex3_3_2b {

	public static void main(String args[]) {
		//Fileの入出力など「エラーが発生する可能性」がある場合，
		//try{．．．．}catch(．．){．．．}というエラー処理の構文を使う必要がある
		try{
			  //入力するファイルの設定
			  File file = new File("Q1490.nt");
			  BufferedReader br = new BufferedReader(new FileReader(file));

			  //出力するファイルの設定
			  File file_out = new File("Q1490-label-alt.txt");
			  BufferedWriter bw = new BufferedWriter(new FileWriter(file_out));


			  String str;

			  while((str = br.readLine()) != null){
			    System.out.println(str);
			    
			    String[] data = str.split(" ");
			    if(data.length>3) {
			    	if(data[1].equals("<http://www.w3.org/2000/01/rdf-schema#label>")) {
			    		bw.write(data[0].replace("<http://www.wikidata.org/entity/","wd:").replace(">","")+"\t");
			    		bw.write("ラベル\t"+decodeFromUnicode(data[2])+"\n");
			    	}
			    	else if(data[1].equals("<http://www.w3.org/2004/02/skos/core#altLabel>")) {
			    		bw.write(data[0].replace("<http://www.wikidata.org/entity/","wd:").replace(">","")+"\t");
			    		bw.write("別名\t"+decodeFromUnicode(data[2])+"\n");
			    	}
			    	
			    }
				//bw.newLine();  //"\n"を出力する代わりにこちらのメソッドを使っても良い
			  }

			  br.close();
			  bw.close();//close()を忘れると正しく出力が終わらない

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}
//		catch(){}によるエラー処理は，エラーの種類毎に書く以外に，
//		下記のように「すべてのエラー」をまとめて書くことも可能
//			catch(Exception e){
//				  System.out.println(e);
//			}
	}
	
	
    public static String decodeFromUnicode(String input) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            if (input.startsWith("\\u", i)) {
                String hex = input.substring(i + 2, i + 6);
                char c = (char) Integer.parseInt(hex, 16);
                sb.append(c);
                i += 6;
            } else {
                sb.append(input.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }


}
