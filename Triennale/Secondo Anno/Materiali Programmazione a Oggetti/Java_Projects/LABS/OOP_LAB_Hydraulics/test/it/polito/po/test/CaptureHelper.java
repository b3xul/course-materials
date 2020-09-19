package it.polito.po.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CaptureHelper {
	private PrintStream prevOut;
	private ByteArrayOutputStream realOut;
	private String str;
	/**
	 * starts capturing output into buffer
	 *
	 */
    void startCapture() {
		prevOut = System.out;
		realOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(realOut));
	}
	/**
	 * stop capturing output, reset normal output
	 * @return the captured output
	 */
   void stopCapture() {
		System.setOut(prevOut);
		this.str = realOut.toString();
	}

	/**
	 * Finds the the n-th occurrence of a number
	 * after the appearence of the name within
	 * the string str.
	 * 
	 * Es.Given <pre>
	 * 		str = "... Pi 3.14 and 6.28 ..."
	 * 		name = "Pi"
	 * </pre>
	 * For n=1 it returns 3.14.
	 * For n=2 it returns 6.28
	 * 
	 * @param name	The name that marks the part to search for numbers
	 * @param str	The string
	 * @param n		The number of the occurrence to search for
	 * @return		The value of the n-th occurrence of a number
	 * 				after the appearence of the name within
	 * 				the string str.
	 */
	double findNNum(String name, int n) {
		int posName = str.indexOf(name);
		int startNum;
		for(startNum=posName+name.length();!Character.isDigit(str.charAt(startNum))
							&& str.charAt(startNum)!='.';startNum++);
		int endNum;
		for(endNum=startNum+1;Character.isDigit(str.charAt(endNum))
			|| str.charAt(endNum)=='.';endNum++);
		// first number
		for(int i=1; i<n; ++i){
			for(startNum=endNum+1;!Character.isDigit(str.charAt(startNum))
				&& str.charAt(startNum)!='.';startNum++);
			for(endNum=startNum+1;Character.isDigit(str.charAt(endNum))
				|| str.charAt(endNum)=='.';endNum++);
		}
		String num = str.substring(startNum,endNum);
		return Double.parseDouble(num);
	}

}
