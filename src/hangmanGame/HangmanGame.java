package hangmanGame;

import java.util.*;

public class HangmanGame {
	private static final int HIDDENCHAR = 2;
	private StringBuffer hiddenWord;
	private Scanner sc;
	private String newWord;
	private int failCount;
	
	public HangmanGame() {
		sc = new Scanner(System.in);
	}
	
	public void run() {
		System.out.println("지금부터 행맨 게임을 시작합니다.");
		Words words = new Words("words.txt");
		while(true) {
			newWord = words.randomWord();
			if(newWord==null)	break;
			makeHidden();
			go();
			System.out.print("Next(y/n)?");
			String answer = sc.next();
			if(answer.equals("n"))	break;
		}
		System.out.println("행맨 게임을 종료합니다.");
	}
	
	void makeHidden() {
		hiddenWord = new StringBuffer(newWord);
		Random r = new Random();
		
		for(int i = 0; i< HIDDENCHAR; i++) {
			int index = r.nextInt(newWord.length());
			char c = newWord.charAt(index);
			for(int j=0;j<newWord.length();j++) {
				if(hiddenWord.charAt(j)==c)
					hiddenWord.setCharAt(j, '-');
			}
		}
	}
	
	void go() {
		failCount = 0;
		char key;
		do {
			if(failCount==5) {
				System.out.println("5번 실패했습니다.");
				break;
			}
			System.out.println(hiddenWord);
			System.out.print(">>");
			String text = sc.next();
			key = text.charAt(0);
		}while(!check(key));
		System.out.println("정답 : " + newWord);
	}
	
	// 숨긴 글자와 일치하는지 검사. 일치 시 '-'를 key값으로 변경
	boolean check(char key){
		boolean tmp = false;
		for(int i=0;i<newWord.length();i++) {
			if(hiddenWord.charAt(i) == '-'
					&& newWord.charAt(i) == key) {
				hiddenWord.setCharAt(i,key);
				tmp = true;
			}
		}
		if(!tmp)
			failCount++;
		for(int i = 0 ; i<newWord.length();i++) {
			if(hiddenWord.charAt(i) == '-')
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		HangmanGame game = new HangmanGame();
		game.run();
	}
}
