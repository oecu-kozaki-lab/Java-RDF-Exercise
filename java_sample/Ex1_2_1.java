public class Ex1_2_1 {
    public static void main(String[] args) {
        //検索対象の文字列の定義
        String data = "A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .";

        int index = 0;  //何文字目に見つかったか？
        int count = 0;  //見つかった回数
        
        //何文字目から調べるか（=i）をずらしながら末尾まで調べる
        for (int i = 0; i < data.length(); i++) {
            index = data.indexOf("A", i);
            if (index >= 0) {
                System.out.println(index);
                i = index;  //見つかったら，iを見つかった箇所までずらす（このあと，i++でさらに1追加される）
                count++;
            }
        }
        System.out.println("出現回数："+count);
    }
}
