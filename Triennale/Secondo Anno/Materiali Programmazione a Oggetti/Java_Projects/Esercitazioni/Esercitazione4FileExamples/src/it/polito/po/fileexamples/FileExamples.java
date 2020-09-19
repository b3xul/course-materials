package it.polito.po.fileexamples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileExamples {
	
	private static final int EOF = -1;

	public static void main(String[] args) 
			throws IOException {
		Reader r = new FileReader("input.txt");
		
		while(true) {
			int ch = r.read();
			if (ch == EOF) {
				break;
			}
			char unicode = (char)ch;
			System.out.println(unicode);
		}
		r.close();
		
		r = new FileReader("input.txt");
		char[] buffer = new char[2048];
		
		while(true) {
			int ch = r.read(buffer);
			if (ch == EOF) {
				break;
			}
			char unicode = (char)ch;
			String s = new String(buffer, 0, unicode);
			System.out.println("lenght=" + (int)unicode);
			System.out.println(s);
		}
		r.close();
		
		BufferedReader br = new BufferedReader(
				new FileReader("input.txt"));
		
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		
		br.lines().forEach(System.out::println);

		//righe non vuote
		br.lines().filter(l -> l.length() > 0)
			.forEach(System.out::println);
		
		
		br.close();
		
		line = "aaa";
		Writer w = new FileWriter("output.txt");
		w.write(line);
		w.flush();
		w.close();
		
		
		
		
		
	}
	
}
