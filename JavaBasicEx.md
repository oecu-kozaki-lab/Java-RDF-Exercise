# Javaプログラミングの基礎
参考サイト
- [Java入門](https://www.javadrive.jp/start/)
- [【2025】エクリプス（Eclipse）を使ったJava開発の始め方！](https://ai-kenkyujo.com/programming/language/eclipse-java/)
  
## 演習課題1-1.　Javaの開発環境と設定
### (0) VS Code(Visual Studio Code)のJava拡張機能のセットアップ  
VS Codeの **Extension Pack for Java** を下記の手順で設定しなさい
1. VS Codeを起動し，左にある **拡張機能** を選択
2.  **Extension Pack for Java** を検索し，インストールする（Microsoftが開発元のものを選ぶ）
3. Java（JDK）がインストールされていないときは，インストールする．

  
### (1) プロジェクト・サンプルプログラムの作成・実行
演習用のプロジェクトを作成し，以下のサンプルプログラム（Ex1_1_1.java）を作成し，実行しなさい．  
1. エクスプローラーで任意の場所に **「サンプルプロジェクトに使用するフォルダ」** を作成
2. VS Codeを起動し，メニューから **ファイル＞フォルダを開く** で，「1.で作成したフォルダ」を選択して開く．  
※「信頼性の確認」のダイアログが表示された場合は，内容を確認して「信頼する」を選択する．
3. VS Codeのメニューから **ファイル＞ファイルを作成** を選択し，ファイル名として「Ex1_1_1.java」を入力して作成．  
4. 作成された「Ex1_1_1.java」が表示されるので，下記のソースコードをコピー＆ペーストして **「保存」** する．  
5. VS Codeの右上に表示される「Run Java」ボタン（▷マーク）をクリックして，プログラムを実行する  
※上記の手順で実行した場合，コンパイルされたJavaの実行ファイル（.class）は，ソースコードとは別のフォルダに生成される．  
　（現時点では気にしなくてもよい）
```
public class Ex1_1_1 {

	public static void main(String[] args) {

		System.out.println("Hello World!");
	}
}
```

(2) サンプルプログラムの修正例
標準出力に「A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .」と出力するプログラムを書きなさい．  
1. (1)で作成した「Ex1_1_1.java」を選択し，メニューから *ファイル＞別名保存* で「Ex1_1_2.java」というファイル名で保存
2. `public class Ex1_1_1`のクラス名を`public class Ex1_1_2 `に変更  
  ※Javaでは，メインクラス(mainメソッドがある)クラスの名前と，ファイル名が一致する必要がある  
3. 下記のヒントを参考にプログラムを修正して，保存→実行  

ヒント：**System.out.println("．．．")** を使うと良い．


## 演習課題1-2.　Stringクラスを用いた文字列処理
### (1) 文字列の検索
「A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .」という文字列に，
「A」の文字が何回出現するかを数え，その数を出力するプログラムを書きなさい．  
1. 「Ex1_2_1」というクラスを新規で追加し「Ex1_2_1.java」というファイルを作成
2. 下記のヒントを参考にプログラムを作成する

ヒント１：処理対象の文字列は`String data = "A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .";`といった変数で定義する  
ヒント２：**String.indexOf("A")** を使うと，"A"が何文字目に出現するかを得られる．  
ヒント３：**String.indexOf("A", n)** とすると， **n 文字目以降** で，為"A"が何文字目に出現するかを得られる．  
（[参考サイト](https://www.javadrive.jp/start/string/index18.html)）

### (2) 文字列の分割
「A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .」という文字列を，
半角スペース「 」で区切り，「1行に1単語ずつ」標準出力に出力するプログラムを書きなさい．
1. 「Ex1_2_2」というクラスを新規で追加し「Ex1_2_2.java」というファイルを作成  
2. 下記のヒントを参考にプログラムを作成する

ヒント：**String.split(" ")** を使うと良い．（[参考サイト](https://www.javadrive.jp/start/string/index20.html))

## 演習課題1-3.　ファイルの入出力
### (1) ファイルの読み込み，行番号を付与して保存
[StarWars.txt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/StarWars.txt) を
読み込み，行番号を付与したファイルを出力するプログラムを書きなさい．

ヒント１：ファイル読み込みには**BufferedReaderクラス** の**readLine()メソッド** を使うと良い．（[参考サイト](https://www.javadrive.jp/start/stream/index3.html)）  
ヒント２：ファイルへの書き込みには**BufferedWriterクラス** を使うと良い．（[参考サイト](https://www.javadrive.jp/start/stream/index6.html)）　　

```
//Fileの入出力など「エラーが発生する可能性」がある場合，
//try{．．．．}catch(．．){．．．}というエラー処理の構文を使う必要がある	
try{
	  //入力するファイルの設定
	  File file = new File("StarWars.txt");
	  BufferedReader br = new BufferedReader(new FileReader(file));

	  //出力するファイルの設定
	  File file_out = new File("output.txt");
	  BufferedWriter bw = new BufferedWriter(new FileWriter(file_out));

	//ここにreadLine()を使った処理を書く

	  //close()を忘れると正しく出力が終わらない
	  br.close();
	  bw.close();

	}catch(FileNotFoundException e){
	  System.out.println(e);
	}catch(IOException e){
	  System.out.println(e);
	}
}
```

### (2) 読み込んだファイルの解析
[StarWars.txt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/StarWars.txt) を
読み込み，「単語の出現回数」を数え，その結果を出力するプログラムを書きなさい．  

ヒント：単語の出現回数を管理するには**HaspMapクラス**を使うとよい．（[参考サイト](https://www.javadrive.jp/start/collection/index3.html))
