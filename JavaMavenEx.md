# 演習4-1：VS Codeを用いたMavenプロジェクトの作成とApache Jenaの利用

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

## 1. Javaの設定を
### バージョンを確認する

開いているファイルやフォルダがあれば，一度，閉じておく．

VS Codeのターミナルで次のコマンドを実行します。

```bash
java -version
```

Javaのバージョンが表示されることを確認してください。

### JAVA_HOMEの確認

JavaプログラムやMavenを利用するには、環境変数 **`JAVA_HOME`** が正しく設定されている必要があります。

Windows の **コマンドプロンプト** を開き、次のコマンドを実行します。
```cmd
echo %JAVA_HOME%
```
正しく設定されている場合は、次のようにJDKのインストール先が表示されます。

```text
C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot
```

何も表示されない場合は、`JAVA_HOME` が設定されていませんので、[こちらの手順](#付録java_home-の設定を確認する)に沿って設定してください。

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

以下、コマンドパレットで順次，選択します．

### Project Type

```text
Maven create from archetype
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

保存先を指定すると、VS CodeのコンソールでMavenプロジェクトの作成コマンドが実行されるので，
```
Define value for property 'version' 1.0-SNAPSHOT: :
```
や
```
Confirm properties configuration:
groupId: jp.kozaki.lab
artifactId: nena-demo
version: 1.0-SNAPSHOT
package: jp.kozaki.lab
 Y: :
```
といった質問が表示されたら「Enter」を押す．

**BUILD SUCCESS** と表示されたらするとプロジェクト作成が完了です．

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

import java.io.FileNotFoundException;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.exec.http.QueryExecutionHTTP;

public class searchRDFfromSPARQLendpoint {

	static public void main(String[] args) throws FileNotFoundException {

		// クエリの作成
		String queryStr = """
				PREFIX wd: <http://www.wikidata.org/entity/>

				select *
				where{
					wd:Q7105556 ?p ?o.
				}LIMIT 100
				""";
		Query query = QueryFactory.create(queryStr);

		try (QueryExecution qExec = QueryExecutionHTTP.create()
				.endpoint("https://query.wikidata.org/sparql")
				.query(query)
				.param("timeout", "10000")
				.httpHeader("User-Agent", "Example-Agent/1.0 (https://www.example.com; xxx@example.com)")
				.build()) {

			ResultSet rs = qExec.execSelect();

			// 結果の出力 ※以下のどれか「１つ」を選ぶ（複数選ぶと，2つ目以降の結果が「空」になる）
			// ResultSetFormatter.out(System.out, rs, query); //表形式で，標準出力に
			ResultSetFormatter.outputAsCSV(System.out, rs); // CSV形式で，標準出力に

		}
	}
}
```

---

## 実行

`App.java` を実行します。

次のような結果が表示されれば成功です（VS Codeのターミナルでは文字化けが発生する）。

```text
p,o
http://schema.org/version,2474201553
http://schema.org/dateModified,2026-03-21T13:12:34Z
http://schema.org/description,Universitﾃ､t in Japan
http://schema.org/description,ﾗ碩勉ﾗ燮泰ｨﾗ｡ﾗ燮俎・ﾗ泰燮､ﾗ
http://schema.org/description,universitas di Jepang
http://schema.org/description,universiteit in Japan
...
```


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

以下のような説明にすると、学生向けに分かりやすくまとまります。

---

# 付録：JAVA_HOME の設定を確認する

JavaプログラムやMavenを利用するには、環境変数 **`JAVA_HOME`** が正しく設定されている必要があります。

## 1. JAVA_HOME の設定を確認する

Windows の **コマンドプロンプト** または **PowerShell** を開き、次のコマンドを実行します。

### コマンドプロンプト

```cmd
echo %JAVA_HOME%
```

### PowerShell

```powershell
echo $env:JAVA_HOME
```

設定されている場合は、次のようにJDKのインストール先が表示されます。

```text
C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot
```

何も表示されない場合は、`JAVA_HOME` が設定されていません。

---

##　2.JAVA_HOME を設定する

### (1) システムの設定を開く

1. スタートメニューで **「環境変数」** と検索する。
2. **「システム環境変数の編集」** を開く。
3. **「環境変数(N)...」** をクリックする。

---

### (2) JAVA_HOME を追加する

「ユーザー環境変数」または「システム環境変数」で **[新規]** をクリックし、以下を設定します。

| 項目  | 設定値            |
| --- | -------------- |
| 変数名 | `JAVA_HOME`    |
| 変数値 | JDKのインストールフォルダ |

例

```text
C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot
```

---

### (3) Path に追加する

環境変数 **Path** を編集し、次の項目を追加します。

```text
%JAVA_HOME%\bin
```

すでに登録されている場合は追加する必要はありません。

---

### (4) 設定を反映する

設定後は、

* コマンドプロンプト
* PowerShell
* VS Code

を一度終了し、再度起動してください。

---

## 3. 設定を確認する

以下のコマンドを実行します。

```cmd
java -version
```

および

```cmd
javac -version
```

JDKのバージョンが表示されれば設定は完了です。

---

## 参考：Eclipse Temurin のインストール先

Eclipse Temurin を標準設定でインストールした場合、JDKは通常次のフォルダにインストールされます。

```text
C:\Program Files\Eclipse Adoptium\
```

その中に、インストールしたJDKのバージョンごとのフォルダがあります。

例

```text
C:\Program Files\Eclipse Adoptium\jdk-21.0.7.6-hotspot
```

```text
C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot
```

`JAVA_HOME` には、この **`jdk-...-hotspot` フォルダ** を指定してください（`bin` フォルダではありません）。

---

### 注意

* `JAVA_HOME` は **JDKのフォルダ** を指定します。
* `Path` には **`%JAVA_HOME%\bin`** を登録します。
* JDKを複数インストールしている場合は、演習で使用するバージョンを指定してください。例えば、本演習では **JDK 25 (Eclipse Temurin)** を使用します。

