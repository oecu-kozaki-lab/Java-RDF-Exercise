
public class Ex1_2_1a {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String data = "A LONG TIME AGO IN A GALAXY FAR, FAR AWAY . . . .";
		
		int i =  data.indexOf("A");
		int c = 0;
		
		while(i >= 0) {
			c++;
			i = data.indexOf("A",i+1);
			System.out.println(i);
		}
		
		System.out.println("Aの出現数は："+c);
		

	}

}
