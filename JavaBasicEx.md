# Javaプログラミングの基礎
参考サイト
- [Java入門](https://www.javadrive.jp/start/)
- [【2025】エクリプス（Eclipse）を使ったJava開発の始め方！](https://ai-kenkyujo.com/programming/language/eclipse-java/)
  
## 演習課題1-1.　開発環境Eclipseのセットアップ
### (0) Eclipseのセットアップ  
日本語化パッケージ適用済みの **Pleiades All in One** を下記の手順でインストールしなさい
1. https://willbrains.jp/ から **Eclipse 2025（最新版）** を選択
2. Full Editonの「Java」をダウンロード
3. ダウンロードしたファイルを実行し，インストール先のフォルダ（ドライブの直下を推奨）指定してインストール

  
### (1) プロジェクト・サンプルプログラムの作成・実行
演習用のプロジェクトを作成し，以下のサンプルプログラム（Ex1_1_1.java）を作成し，実行しなさい．  
1. インストールしたフォルダ下の **eclipseというサブフォルダ** にある **eclipse.exe** を実行
2. メニューから *ファイル＞新規＞Javaプロジェクト* を選択して実行し  
表示されたダイアログで「プロジェクト名（例：java_ex）」を入力して【完了】（※他の設定はデフォルトで）
4. 作成されたプロジェクトを選択し， *右クリック＞新規＞クラス* を選択し  
表示されたダイアログで「名前：Ex1_1_1」を入力して【完了】
5. 作成された「Ex1_1_1.java」をクリックして開き，下記のソースコードをコピー＆ペーストして「保存」  
※1行目に「package java_ex;」といった記述があるときは残して，2行目以降に貼り付ける
6. Ex1_1_1.javaを *右クリック＞実行＞Javaアプリケーション* を選択しプログラムを実行する

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
3. 下記のヒントを参考にプログラムを修正して，保存→実行　
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
