

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class four {

	private static int fNum, sNum, tNum, result;

	private static String firstOperator, secondOperator;

	private final static String[] OPERATOR = { "+", "-", "*", "/" };

	private static int ac=1;
	public static void main(String[] args) {
		System.out.println("请输入产生题目数量：");
		Scanner s = new Scanner(System.in);
		int a=s.nextInt();
		for (int i = 0; i < a; i++) 
		 {
			ys();
				
		 }
	}

	public static boolean ys() {

		while (true) {
			fNum = generateRandomNum(1, 99);

			sNum = generateRandomNum(1, 99);

			tNum = generateRandomNum(1, 99);

			firstOperator = OPERATOR[generateRandomNum(0, 3)];

			secondOperator = OPERATOR[generateRandomNum(0, 3)];

			if (firstOperator.equals(secondOperator)) {
				continue;
			}
			
			try {
				if (secondOperator.equals(Const.Operator.multiplication)
						|| secondOperator.equals(Const.Operator.division)) {
					int preResult = ys(sNum, tNum, secondOperator);
					if (preResult < 0) {
						continue;
					}
					result = ys(fNum, preResult, firstOperator);
				} else {
					int preResult = ys(fNum, sNum, firstOperator);
					if (preResult < 0) {
						continue;
					}
					result = ys(preResult, tNum, secondOperator);
				}
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			

			if (result < 0) {
				continue;
			} else {

				StringBuffer buffer = new StringBuffer();

				buffer.append(fNum).append(firstOperator).append(sNum).append(secondOperator).append(tNum)
						.append("=").append(result).append("\n");
				
				System.out.println(buffer);

				if (writeToFile(buffer.toString())) {

					return true;

				} else {

					return false;

				}

			}
		}
	}

	public static int generateRandomNum(int min, int max) {

		Random random = new Random();

		return random.nextInt(max - min + 1) + min;

	}

	public static boolean writeToFile(String result) {
		
		try {

			 File f1 = new File("result.txt");
		     FileWriter fw = new FileWriter(f1, true);
		     PrintWriter pw = new PrintWriter(fw);
		     if(ac==1)
		     {
		    	 pw.println("201571030121");
		    	 ac=0;
		     }
		    	  pw.println(result);
		    	  fw.flush();
		    	  fw.close();

			return true;

		} catch (IOException e) {

			e.printStackTrace();

			return false;

		}

	}

	public static Integer ys(int firstNum, int secNum, String operator) throws Exception{

		switch (operator) {

		case Const.Operator.add: {

			return firstNum + secNum;

		}

		case Const.Operator.subtraction: {

			return firstNum - secNum;

		}

		case Const.Operator.multiplication: {

			return firstNum * secNum;

		}

		case Const.Operator.division: {
			if (firstNum % secNum == 0) {
				return firstNum / secNum;
			}
			else {
				throw new Exception("");
			}

			

		}

		default: {

			return null;

		}

		}

	}

}
