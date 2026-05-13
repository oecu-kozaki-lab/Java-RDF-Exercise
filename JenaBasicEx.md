# Jenaプログラミングの基礎

## 演習課題2-1.　Jenaのセットアップ
### (1) Apache Jenaのダウンロード・利用のための設定
ここでは，VSCodeでJavaの開発環境が設定済みであるものとして，Apache Jenaのライブラリをプロジェクトに追加する．  
Mavenは使用せず，必要なJARファイル一式をプロジェクト内の `lib` フォルダに置く方法で設定する．

1. Apache Jenaのダウンロードページを開く  
   [https://jena.apache.org/download/](https://jena.apache.org/download/)

2. 「Apache Jena Binary Distributions」にある **apache-jena-X.Y.Z.zip** をダウンロードする  
   `X.Y.Z` はバージョン番号である．  
   ※Jenaのバージョンによって必要なJavaのバージョンが異なるが，基本的に，最新バージョンのJenaを利用すること．

3. ダウンロードしたZIPファイルを展開する

4. 展開してできたフォルダの中にある **libフォルダ** を開き，その中の `*.jar` ファイルをすべてコピーする

5. VSCodeで使用するJavaプロジェクトを作成する  
   ここでは，Java用のフォルダを1つ作成し，そのフォルダをVSCodeで開いて作業する．

   1. 任意の場所に，演習用のフォルダを作成する  
      例：`JenaBasicEx`
   2. VSCodeを起動し，メニューから「ファイル」＞「フォルダーを開く...」を選択する
   3. 作成した `JenaBasicEx` フォルダを選択して開く
   4. VSCodeのエクスプローラーで，プロジェクト直下に `src` フォルダを作成する
   5. `src` フォルダの中に，Javaプログラムを保存する  
      例：`Main.java`

   Java拡張機能のコマンドを使う場合は，コマンドパレットから `Java: Create Java Project...` を実行し，ビルドツールを使わないプロジェクトを作成してもよい．  
   Mavenは使用しないため，Mavenプロジェクトは選択しないこと．

6. Javaプロジェクトの直下に **libフォルダ** を作成し，コピーしたJARファイルをすべて貼り付ける  
   プロジェクトの構成例：

   ```text
   JenaBasicEx/
   ├─ .vscode/
   │  └─ settings.json
   ├─ lib/
   │  ├─ jena-core-....jar
   │  ├─ jena-arq-....jar
   │  ├─ ...
   │  └─ その他のjarファイル
   └─ src/
      └─ Main.java
   ```

7. プロジェクト内に `.vscode/settings.json` を作成し，次の内容を記述する  
   すでに `settings.json` がある場合は，既存の設定に `java.project.referencedLibraries` を追加する．

   ```json
   {
     "java.project.referencedLibraries": [
       "lib/**/*.jar"
     ]
   }
   ```

   この設定により，`lib` フォルダ内のすべてのJARファイルがJavaのクラスパスに追加される．

8. VSCodeを再読み込みする  
   設定後，次のいずれかを行う．

   - VSCodeを再起動する
   - コマンドパレットから `Java: Clean Java Language Server Workspace` を実行する

9. 次のようなプログラムを作成し，エラーが出ずに実行できれば設定は完了である

   ```java
   import org.apache.jena.rdf.model.Model;
   import org.apache.jena.rdf.model.ModelFactory;

   public class Main {
       public static void main(String[] args) {
           Model model = ModelFactory.createDefaultModel();
           System.out.println("Jena model was created.");
       }
   }
   ```

   実行結果として次のように表示されればよい．

   ```text
   Jena model was created.
   ```

### (2) サンプルプログラム「readRDF.java」の実行
サンプルプログラム「[readRDF.java](jena_sample/src/readRDF.java)」の実行して動作を確かめなさい．
1. [readRDF.java](jena_sample/src/readRDF.java)をWebブラウザで開き（別タブで開くとよい），「Download raw file」でソースコードをダウンロードする
2. ダウンロードしたファイルを，プロジェクトの「srcフォルダ」にコピーする
3. VSCodeのエクスプローラーでプロジェクト直下に２つのフォルダ「input」と「output」を追加する
4. 「[DancingMen.ttl](jena_sample/input/DancingMen.ttl)」をダウンロードし，プロジェクトの「inputフォルダ」にコピーする
5. プロジェクトにコピーした「readRDF.java」を実行し，「outputフォルダ」に処理結果のファイルが出力されることを確認する
6. 「readRDF.java」のソースコードを確認し，一部分を修正して再度実行するなど，いろいろ試してみる

## 演習課題2-2.　RDFの検索【読み込んだファイルを対象にする】
(1) [searchRDF.java](jena_sample/src/searchRDF.java)を実行して，JenaのAPIを使った検索の方法を確認しなさい．

(2) [searchRDFusingSPARQL.java](jena_sample/src/searchRDFusingSPARQL.java)を実行して，JenaにおけるSPARQLを使った検索の方法を確認しなさい．

(3) (1),(2)を修正して，自分が作ったクエリでの検索を実行しなさい．

## 演習課題2-3.　RDFの検索【SPARQLエンドポイントを対象にする】
(1) [searchRDFfromSPARQLendpoint.java](jena_sample/src/searchRDFfromSPARQLendpoint.java)※を実行して，外部のSPARQLエンドポイント（この例ではWikidata）を使った検索の方法を確認しなさい．  
また，以下の設定を切り替えて動作を確認しなさい.  
- 出力形式の切替（Table/CSV）
- 出力先の切替（標準出力/ファイル保存）  

※このサンプルプログラムで使用しているWikidataのSPARQLエンドポイントを使用すると，403エラーが発生する現象が見られたため（現時点では解消済み），プログラムに対処方法のコメントを追記しました．  
（エラー原因の詳細と対応方法については，[こちらの記事](https://qiita.com/koujikozaki/items/fba35bf469dc0331128b)を参照してください．）  

※有線LANでネットワークに接続している場合は，VSCodeのJavaプログラム実行時のプロキシ設定（JVM引数など）を行うために，以下の内容の`.vscode/launch.json` を作成する．

内容：

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Main",
            "request": "launch",
            "mainClass": "Main",

            "vmArgs": "-DproxySet=true -DproxyHost=wwwproxy.osakac.ac.jp -DproxyPort=8080"
        }
    ]
}
```

- `"version"` は launch.json の設定形式バージョンを表す．
- `"mainClass"` には実行するクラス名を指定する．
- JVM引数を追加する場合は `"vmArgs"` を追加する．

(2) (1)を修正して，自分が作ったクエリでの検索を実行しなさい．
