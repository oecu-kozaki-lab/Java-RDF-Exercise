# Jenaプログラミングの基礎
サンプルプログラムは，こちらのレポジトリ[JavaRDFsample2022](https://github.com/oecu-kozaki-lab/JavaRDFsample2022)を参照する．  

## 演習課題2-1.　Jenaのセットアップ
### (1) Apache Jenaのダウンロード・利用のための設定
1. [https://jena.apache.org/](https://jena.apache.org/) の *Download>Apache Jena Binary Distributions>
Apache Jena Commands* から **apache-jena-5.X.X.zip** (X.Xはバージョン番号)をダウンロード
2. ダウンロードしたZIPファイルを解凍するとできる **「libフォルダ」** を，Eclipseのプロジェクトの下にコピーする  
※Eclipse上のプロジェクトに「ドラッグ＆ドロップ」するとコピーできる（ダイアログで「ファイルおよびフォルダをコピー」を選択）
3. プロジェクトを *右クリック＞ビルド・パス＞ビルド・パス* の構成を実行  
表示されるダイアログで「ライブラリ」を選択し，「クラスパス」を選択した状態で【JARの追加】
新しく表示されるダイアログから「編集中のプロジェクト名の階層」の下にある「libフォルダ」を選択し，そこの表示される「xxx.jar」というファイルをすべて選択（SHIFTキーを押しながら一番上と下を選択）して【OK】
残ったダイアログを【適用して閉じる】

### (2) サンプルプログラム「readRDF.java」の実行
サンプルプログラム「[readRDF.java](jena_sample/src/readRDF.java)」の実行して動作を確かめなさい．
1. [readRDF.java](jena_sample/src/readRDF.java)をWebブラウザで開き（別タブで開くとよい），「Download raw file」でソースコードをダウンロードする
2. ダウンロードしたファイルを，プロジェクトの「srcフォルダ」にコピー（Eclipseへのドラッグ＆ドロップでコピー可）
3. プロジェクトを *右クリック＞新規* で２つのフォルダ「input」と「output」を追加
4. 「[DancingMen.ttl](jena_sample/input/DancingMen.ttl)」をダウンロードし，プロジェクトの「inputフォルダ」にコピー
5. プロジェクトにコピーした「readRDF.java」を実行し，「outputフォルダ」に処理結果のファイルが出力されることを確認する
6. 「readRDF.java」のソースコードを確認し，一部分を修正して再度実行するなど，いろいろ試してみる

## 演習課題2-2.　RDFの検索【読み込んだファイルを対象にする】
(1) [searchRDF.java](jena_sample/src/searchRDF.java)を実行して，JenaのAPIを使った検索の方法を確認しなさい．

(2) [searchRDFusingSPARQL.java](jena_sample/src/searchRDFusingSPARQL.java)を実行して，JenaにおけるSPARQLを使った検索の方法を確認しなさい．

(3) (1),(2)を修正して，自分が作ったクエリでの検索を実行しなさい．

## 演習課題2-3.　RDFの検索【SPARQLエンドポイントを対象にする】
(1) [searchRDFfromSPARQLendpoint.java](jena_sample/src/searchRDFfromSPARQLendpoint.java)を実行して，外部のSPARQLエンドポイント（この例ではWikidata）を使った検索の方法を確認しなさい．

(2) (1)を修正して，自分が作ったクエリでの検索を実行しなさい．

