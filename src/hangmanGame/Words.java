package hangmanGame;

import java.io.*;
import java.util.*;

class Words {
	Vector<String> wv = new Vector<String>();
	
	// Read File
	public Words(String fileName) {
		try {
			Scanner sc = new Scanner(new FileReader("src/hangmanGame/words.txt"));
			while(sc.hasNext()) {
				String word = sc.nextLine();
				wv.add(word);
			}
			sc.close();
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없음");
			System.exit(0);
		}
	}
	
	// 무작위로 단어 추출
	public String randomWord() {
		int WordMax = wv.size();
		int index = (int)(Math.random()*WordMax);
		return wv.get(index);
	}
	
}
