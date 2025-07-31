# Javaプログラミングの基礎
## 演習課題1-1.　開発環境Eclipseのセットアップ
(0) Eclipseのセットアップを行いなさい．  

(1) 演習用のプロジェクトを作成し，以下のサンプルプログラム（Ex1_1_1.java）を作成し，実行しなさい．  
```
public class Ex1_1_1 {

	public static void main(String[] args) {

		System.out.println("Hello World!");
	}
}
```

(2) 標準出力に「A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .」と出力するプログラムを書きなさい．  
ヒント：**System.out.println("．．．")** を使うと良い．


## 演習課題1-2.　Stringクラスを用いた文字列処理
(1) 「A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .」という文字列に，
「A」の文字が何回出現するかを数え，その数を出力するプログラムを書きなさい．  
ヒント：**String.indexOf("...")** を使うと良い．

(2)「A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .」という文字列を，
半角スペース「 」で区切り，「1行に1単語ずつ」標準出力に出力するプログラムを書きなさい．  
ヒント：**String.spilit(" ")** を使うと良い．

## 演習課題1-3.　ファイルの入出力
(1)　[StarWars.txt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/StarWars.txt) を
読み込み，行番号を付与したファイルを出力するプログラムを書きなさい．  
ヒント１：ファイル読み込みには**BufferedReaderクラス** の**readLine()メソッド** を使うと良い．  
ヒント２：ファイルへの書き込みには**BufferedWriterクラス** を使うと良い．

(2)　[StarWars.txt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/StarWars.txt) を
読み込み，「単語の出現回数」を数え，その結果を出力するプログラムを書きなさい．  
ヒント：単語の出現回数を管理するには**HaspMapクラス**を使うとよい．
