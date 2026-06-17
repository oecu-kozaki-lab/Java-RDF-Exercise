# JavaによるLLM処理サンプルプログラム演習

## 目的

JavaからLLM APIを呼び出し，テキスト，PDF，画像などを入力として処理する基本的な方法を体験する。

使用するサンプル：

[https://github.com/oecu-kozaki-lab/java-llm-samples](https://github.com/oecu-kozaki-lab/java-llm-samples)

このリポジトリには，JavaによるLLM処理のサンプルプログラムが含まれている。([GitHub][1])

---

# 演習課題5-1．サンプルプログラムの実行

## 1. レポジトリのダウンロード

GitHubのページを開く。

[https://github.com/oecu-kozaki-lab/java-llm-samples](https://github.com/oecu-kozaki-lab/java-llm-samples)

右上の **Code** ボタンから **Download ZIP** を選び，ZIPファイルをダウンロードする。

ダウンロードしたZIPファイルを展開する。

展開したフォルダを VSCode で開く。  
※ファイルの展開方法によっては、余分なフォルダが１つ上にできる場合があるので、開くフォルダの選択を注意する。

---

## 2. API KEYの設定

リポジトリ直下にある

```text
config_ex.properties
```

をコピーまたは名前変更して，

```text
config.properties
```

にする。

`config.properties` を開き，API KEYを記入して保存する。

例：

```properties
OPENAI_API_KEY=自分のAPIキー
```

注意：

```text
config.properties
```

にはAPI KEYが含まれるため，GitHubなどに公開しないこと。

---

## 3. サンプルプログラムの場所

サンプルプログラムは以下のフォルダにある。

```text
langchain4j-samples/src/main/java/jp/kozaki/lab
```

このフォルダには，以下のJavaプログラムが含まれている。([GitHub][2])

```text
App.java
langchain4j_Chat.java
langchain4j_TextFile.java
langchain4j_PdfText.java
langchain4j_LocalImage.java
```

---

## 4. サンプルプログラムの実行

VSCodeで各Javaファイルを開き，`main` メソッドの上に表示される **Run** をクリックして実行する。

または，右上の実行ボタンから実行する。  
※実行に「ファイルが見つからない」といったエラーが発生した際は、VS Codeで開くフォルダが間違ていないかを確認する。

---

## 5. 実行するサンプル

### App.java

最も基本的な動作確認用プログラム。

確認すること：

```text
プログラムが正しく実行できるか
```

---

### langchain4j_Chat.java

LLMに質問文を送り，応答を取得するサンプル。

確認すること：

```text
JavaプログラムからLLM APIを呼び出せるか
```

課題：

質問文を変更して，応答がどのように変わるか確認する。

---

### langchain4j_TextFile.java

`input.txt` を読み込み，LLMで処理するサンプル。

確認すること：

```text
テキストファイルの内容をLLMに入力できるか
```

課題：

`input.txt` の内容を変更し，要約結果や応答の変化を確認する。

---

### langchain4j_PdfText.java

`sample.pdf` の内容を読み込み，LLMで処理するサンプル。

確認すること：

```text
PDFファイルのテキストをLLMに入力できるか
```

課題：

PDFの内容について質問するプロンプトに変更してみる。

---

### langchain4j_LocalImage.java

`image.png` を読み込み，画像についてLLMに質問するサンプル。

確認すること：

```text
画像を入力としてLLMに渡せるか
```

課題：

画像について説明させるプロンプトや，画像中の情報を質問するプロンプトに変更してみる。

---

# 演習課題5-2：サンプルプログラムの修正

### (1)：プロンプトの変更

いずれかのサンプルプログラムについて，LLMに送る質問文を変更し，実行結果を確認する。

### (2)：入力ファイルの変更

`input.txt` または `image.png` を別の内容に変更し，結果を確認する。

---

# 演習課題5-3：JenaとLLMの連携

## 課題の目的

この課題では、**Apache Jena** を用いて **Wikidata** にSPARQLクエリを発行し、取得した検索結果を **LLM** に渡して自然な文章を生成するプログラムを作成する。

これにより、

* 知識グラフの検索
* JavaによるSPARQL実行
* LLMとの連携

という一連の処理を体験する。

---

## 課題内容

**大阪電気通信大学を主語とするトリプルをWikidataから取得し、その検索結果をLLMで説明文に変換するプログラムを作成しなさい。**

---

## プログラムの処理

### 1. WikidataにSPARQLクエリを送信する

Jenaを用いて、Wikidata Query ServiceにSPARQLクエリを送信する。

今回は、

> **大阪電気通信大学を主語とするトリプル**

を取得する。

取得したURIについては、日本語ラベルを取得すること。

---

### 使用するSPARQLクエリ

```sparql
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX bd: <http://www.bigdata.com/rdf#>

SELECT ?propertyLabel ?valueLabel
WHERE {

  wd:Q7105556 ?p ?value .

  ?property wikibase:directClaim ?p .

  SERVICE wikibase:label {
      bd:serviceParam wikibase:language "ja,en".
  }
}
ORDER BY ?propertyLabel
```

このクエリでは、

**大阪電気通信大学（Q7105556）**

について、

```
大阪電気通信大学
    ├── 所在地 → 大阪府
    ├── 設立年 → 1941
    ├── 公式Webサイト → …
    ├── 学生数 → …
```

のような情報を取得できる。

---

### 2. 検索結果を文字列にまとめる

取得した検索結果を、LLMへ入力しやすいように整形する。

例

```
所在地：大阪府
設立：1941年
公式ウェブサイト：https://...
学生数：...
...
```

---

### 3. LLMへ入力する

検索結果をそのまま表示するのではなく、LLMに次のような指示を与える。

#### プロンプト例

```
以下はWikidataから取得した大阪電気通信大学の情報です。

この情報を基に、
高校生向けに大阪電気通信大学を紹介する文章を200文字程度で作成してください。

【検索結果】
...
```

---

### 4. 出力結果を表示する

LLMが生成した紹介文をコンソールへ表示する。

---

# ヒント1：pom.xmlにJenaを追加する

`pom.xml` の `<dependencies>` にApache Jenaを追加する。

```xml
<dependency>
    <groupId>org.apache.jena</groupId>
    <artifactId>apache-jena-libs</artifactId>
    <version>6.1.0</version>
    <type>pom</type>
</dependency>
```

---

# ヒント2：検索結果をLLMへ渡す

取得した結果は `StringBuilder` を使って1つの文字列にまとめる。

例

```
所在地：大阪府
設立：1941年
学生数：...
```

この文字列を、これまでのサンプルと同様にLLMへ入力する。

---

## 注意事項

API KEYは他人に見せない。

`config.properties` をGitHubにアップロードしない。

LLMの出力は必ずしも正しいとは限らないため，内容を確認する。

個人情報や未公開の研究データは入力しない。

---

## トラブルシューティング

### API KEYに関するエラーが出る場合

確認すること：

```text
config_ex.properties を config.properties に変更したか
API KEYを正しく記入したか
config.properties を保存したか
```

### Javaファイルを実行できない場合

確認すること：

```text
VSCodeでフォルダ全体を開いているか
Java Extension Pack が入っているか
JDKが正しく設定されているか
```

### ファイルが見つからないというエラーが出る場合

確認すること：

```text
input.txt
sample.pdf
image.png
config.properties
```

がリポジトリ直下にあるか確認する。

[1]: https://github.com/oecu-kozaki-lab/java-llm-samples "GitHub - oecu-kozaki-lab/java-llm-samples: JavaによるLLM処理のサンプルプログラム · GitHub"
[2]: https://github.com/oecu-kozaki-lab/java-llm-samples/tree/main/langchain4j-samples/src/main/java/jp/kozaki/lab "java-llm-samples/langchain4j-samples/src/main/java/jp/kozaki/lab at main · oecu-kozaki-lab/java-llm-samples · GitHub"
