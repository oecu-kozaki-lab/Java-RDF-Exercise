# 演習：VS Codeを用いたMavenプロジェクトの作成とApache Jenaの利用

## 1. 演習の目的

これまでの演習では、Apache Jenaのjarファイルを手動でダウンロードし、Javaプロジェクトに追加して利用していました。

本演習では、Javaの標準的なビルドツールである **Maven** を利用してプロジェクトを作成し、ライブラリを自動的に管理する方法を学びます。

また、MavenでApache Jenaを導入し、WikidataのSPARQLエンドポイントへアクセスするプログラムを作成・実行します。

---

# 2. 前提条件

本演習では、以下の環境が整っていることを前提とします。

* VS Code
* Extension Pack for Java
* JDK（Java 21以上を推奨）
* Javaの基本文法を理解していること
* Apache Jenaを利用した簡単なプログラムを作成した経験があること

---

# 演習1　Mavenプロジェクトの作成

## 1. Javaのバージョンを確認する

VS Codeのターミナルで次のコマンドを実行します。

```bash
java -version
```

Javaのバージョンが表示されることを確認してください。

---

## 2. Mavenプロジェクトを作成する

コマンドパレットを開きます。

```text
Ctrl + Shift + P
```

次のコマンドを実行します。

```text
Java: Create Java Project
```

### Project Type

```text
Maven
```

### Archetype

```text
maven-archetype-quickstart
```

### Version

```text
1.4
```

### Group Id

```text
jp.kozaki.lab
```

### Artifact Id

例

```text
jena-demo
```

保存先を指定すると、Mavenプロジェクトが作成されます。

---

## 3. プロジェクト構成を確認する

作成後、次のような構成になります。

```text
jena-demo
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── jp
    │           └── kozaki
    │               └── lab
    │                   └── App.java
    └── test
```

---

# 演習2　pom.xml の設定

## 1. Javaバージョンを設定する

まず、`pom.xml` にJavaのバージョン設定を追加します。

`java -version` の結果に合わせて設定してください。

### Java 25 の場合

```xml
<properties>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

### Java 21 の場合

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

---

## 2. Apache Jenaの依存関係を追加する

`pom.xml` の `<dependencies>` の中に、次の依存関係を追加します。

```xml
<dependency>
    <groupId>org.apache.jena</groupId>
    <artifactId>apache-jena-libs</artifactId>
    <version>6.1.0</version>
    <type>pom</type>
</dependency>
```

**注意**

`<dependency>` は必ず

```xml
<dependencies>

    ...

</dependencies>
```

の中に追加してください。

---
## 3. Mavenプロジェクトを更新する

`pom.xml` を保存します。

保存すると、VS Codeが自動的にMavenプロジェクトを更新し、必要なライブラリをダウンロードします。

更新されない場合は、VS Codeの**エクスプローラー**で

```text
Maven Dependencies
```

を確認してください。

Apache Jenaが正しく読み込まれると、**Maven Dependencies** の中に

```text
org.apache.jena
```

を含むライブラリが表示されます。

表示されない場合は、

1. `pom.xml` を保存する
2. プロジェクトを右クリックして **Java: Clean Java Language Server Workspace** を実行する
3. VS Codeを再起動する

を試してください。

---

### 確認事項

エクスプローラーに次のように表示されれば成功です。

```text
jena-demo
├── src
├── JRE System Library
└── Maven Dependencies
      ├── org.apache.jena...
      ├── commons-...
      ├── slf4j...
      └── ...
```
---

# 演習3　プロジェクトの動作確認

生成された `App.java` をそのまま実行します。

```java
package jp.kozaki.lab;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

実行結果

```text
Hello World!
```

が表示されることを確認してください。

---

# 演習4　Apache Jenaを利用したWikidata検索

`App.java` を次のプログラムに書き換えます。

```java
package jp.kozaki.lab;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionHTTP;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class App {

    public static void main(String[] args) {

        String endpoint =
                "https://query.wikidata.org/sparql";

        String sparql = """
            SELECT ?person ?personLabel
            WHERE {
              ?person wdt:P31 wd:Q5 ;
                      wdt:P106 wd:Q36180 .

              SERVICE wikibase:label {
                bd:serviceParam wikibase:language "ja,en".
              }
            }
            LIMIT 10
            """;

        Query query = QueryFactory.create(sparql);

        try (QueryExecution qexec =
                 QueryExecutionHTTP.service(endpoint, query)) {

            ResultSet results = qexec.execSelect();

            while (results.hasNext()) {

                QuerySolution sol = results.next();

                System.out.println(
                    sol.get("personLabel")
                    + " : "
                    + sol.get("person")
                );
            }
        }
    }
}
```

---

## 実行

`App.java` を実行します。

次のような結果が表示されれば成功です。

```text
夏目漱石 : http://www.wikidata.org/entity/...
森鷗外 : http://www.wikidata.org/entity/...
...
```

---

# 確認課題

## 課題1

取得件数を変更してみましょう。

```sparql
LIMIT 10
```

↓

```sparql
LIMIT 20
```

---

## 課題2

作家以外の職業を検索してみましょう。

例

* 学者
* 政治家
* スポーツ選手

---

## 課題3

日本人のみ取得する条件を追加してみましょう。

ヒント

```sparql
wdt:P27
```

は国籍を表します。

---

## 課題4

興味のある人物や組織について検索するSPARQLクエリを作成してみましょう。

---

# よくあるトラブル

## Apache Jenaのクラスが見つからない

`pom.xml` を保存した後、

```text
Maven: Reload Project
```

を実行してください。

---

## Malformed POM

次のようなエラーが表示された場合

```text
Malformed POM
Unrecognised tag: 'dependency'
```

`<dependency>` が `</dependencies>` の外側に記述されています。

正しくは次のようになります。

```xml
<dependencies>

    <dependency>
        ...
    </dependency>

</dependencies>
```

---

## Javaのバージョンエラー

Javaのバージョンと

```xml
<maven.compiler.source>
<maven.compiler.target>
```

の設定を一致させてください。

---

## Maven executable not found

Extension Pack for Javaだけでは、ターミナルで利用するApache Mavenはインストールされません。

本演習では、VS Codeのコマンドパレットから

```text
Maven: Reload Project
```

を利用すれば実行できます。

---

# まとめ

本演習では、

* VS CodeでMavenプロジェクトを作成する方法
* `pom.xml` の基本的な編集方法
* Mavenによるライブラリ管理
* Apache Jenaの導入
* WikidataへのSPARQLアクセス

を学びました。

Mavenを利用することで、ライブラリを手動で管理する必要がなくなり、Javaプロジェクトを効率的に開発できるようになります。

---

# 発展課題

* DBpediaへのSPARQLアクセス
* Apache Fusekiへの接続
* RDFファイルの読み込み・保存
* SPARQL結果のCSV出力
* OpenAI APIやLangChain4jと組み合わせた知識グラフアプリケーションの開発
