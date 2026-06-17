# JavaによるLLM処理サンプルプログラム演習

## 目的

JavaからLLM APIを呼び出し，テキスト，PDF，画像などを入力として処理する基本的な方法を体験する。

使用するサンプル：

[https://github.com/oecu-kozaki-lab/java-llm-samples](https://github.com/oecu-kozaki-lab/java-llm-samples)

このリポジトリには，JavaによるLLM処理のサンプルプログラムが含まれている。([GitHub][1])

---

## 1. レポジトリのダウンロード

GitHubのページを開く。

[https://github.com/oecu-kozaki-lab/java-llm-samples](https://github.com/oecu-kozaki-lab/java-llm-samples)

右上の **Code** ボタンから **Download ZIP** を選び，ZIPファイルをダウンロードする。

ダウンロードしたZIPファイルを展開する。

展開したフォルダを VSCode で開く。

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

---

## 5. 実行するサンプル

### 5.1 App.java

最も基本的な動作確認用プログラム。

確認すること：

```text
プログラムが正しく実行できるか
```

---

### 5.2 langchain4j_Chat.java

LLMに質問文を送り，応答を取得するサンプル。

確認すること：

```text
JavaプログラムからLLM APIを呼び出せるか
```

課題：

質問文を変更して，応答がどのように変わるか確認する。

---

### 5.3 langchain4j_TextFile.java

`input.txt` を読み込み，LLMで処理するサンプル。

確認すること：

```text
テキストファイルの内容をLLMに入力できるか
```

課題：

`input.txt` の内容を変更し，要約結果や応答の変化を確認する。

---

### 5.4 langchain4j_PdfText.java

`sample.pdf` の内容を読み込み，LLMで処理するサンプル。

確認すること：

```text
PDFファイルのテキストをLLMに入力できるか
```

課題：

PDFの内容について質問するプロンプトに変更してみる。

---

### 5.5 langchain4j_LocalImage.java

`image.png` を読み込み，画像についてLLMに質問するサンプル。

確認すること：

```text
画像を入力としてLLMに渡せるか
```

課題：

画像について説明させるプロンプトや，画像中の情報を質問するプロンプトに変更してみる。

---

## 6. 演習課題

次のいずれかを行う。

### 演習課題5-1：プロンプトの変更

いずれかのサンプルプログラムについて，LLMに送る質問文を変更し，実行結果を確認する。

### 演習課題5-2：入力ファイルの変更

`input.txt` または `image.png` を別の内容に変更し，結果を確認する。

### 演習課題5：JenaとLLMの連携

自分の研究テーマに関連する文章を `input.txt` に入れ，LLMに要約またはキーワード抽出をさせる。


---

## 7. 注意事項

API KEYは他人に見せない。

`config.properties` をGitHubにアップロードしない。

LLMの出力は必ずしも正しいとは限らないため，内容を確認する。

個人情報や未公開の研究データは入力しない。

---

## 8. トラブルシューティング

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
