# 大規模なRDFの扱い
## 演習課題3-1.　プログラムを用いたSPARQLクエリ生成
(1) [pref.txt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/pref.txt) を読み込み，
Wikidataから，各キーワード（都道府県名）とラベルが一致するリソースのIRIを取得するプログラムを作成しなさい．
  
ヒント：例えば「大阪」の場合は下記のようなクエリで取得できる．このようなクエリを自動生成してJenaで結果を取得するプログラムを作ればよい．
```PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
select ?s
where{
?s rdfs:label "大阪"@ja.
}
```
(2)(1)のプログラムを改良し，都道府県に相当するリソースのIRIのみを取得するプログラムを作りなさい．  
  
ヒント２：上記のクエリでは，同名の別リソースも結果に含まれる．それらを除外するにはクエリの工夫（何らかの条件を追加する）が必要．  
  
(3)(2)のプログラムを改良し，各リソースのIRIに加えて，日本語および英語のラベルを取得するプログラムを作りなさい．

## 演習課題3-2.　N-Triple形式のRDFの処理
(1)N-Triple形式のRDFファイル[NEDO_pj_sample.nt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/NEDO_pj_sample.zip) を読み込み，
述語が<http://www.w3.org/2000/01/rdf-schema#label>のトリプルのみを取り出して出力するプログラムを作りなさい．  
  
(2)N-Triple形式のRDFファイル[NEDO_pj_sample.nt](https://github.com/oecu-kozaki-lab/Java-RDF-Exercise/blob/main/NEDO_pj_sample.nt) のトリプルにおいて「主語」に現れるリソースの一覧を
出力するプログラムを作りなさい．  
  
(3)問題作成中... 更新テスト
