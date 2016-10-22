/**
 * 
 */
package dev.interview.indix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Ruthreshwar Gajendiran
 *
 */
public class TautologyVerifier {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in); 
		System.out.printf("Please specify how many expression you want to evaluate: ");
		int count = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter your expressions:");
		
		String[] expressionArray = new String[count];
		for(int j=0; j<count; j++){
			expressionArray[j] = sc.nextLine();
		}
		for(int i=0; i<count; i++){
			tautologyVerifier(expressionArray[i]);
		}
	}

	private static void tautologyVerifier(String expression) {
		TreeSet<Character> expressionSet = (TreeSet<Character>) getVariableSet(expression);
		int variableSize = expressionSet.size();

		boolean tautologyCheck = true;

		for (int i = 0; i < Math.pow(2, variableSize); i++) {
			String[] scriptExpression = scriptExpressionBuilder(Integer.toBinaryString(i), expressionSet);
			Integer value = expressionEvaluation(expression, scriptExpression);
			if(value == 0){
				System.out.println(false);
				tautologyCheck = false;
				break;
			} 
		}
		
		if(tautologyCheck){
			System.out.println(true);
		}
	}
	
	public static Set getVariableSet(String expression) {
		TreeSet<Character> expressionSet = new TreeSet<Character>();
		for(int i=0 ; i<expression.length(); i++){
			char variable = expression.charAt(i);
			if((variable >= 65 && variable <= 90 )||(variable >= 97 && variable <=122)) {
				if(!expressionSet.contains(variable)){
					expressionSet.add(variable);
				}
			}
		}
		return expressionSet;
	}

	public static String[] scriptExpressionBuilder(String binary, TreeSet expressionSet) {
		List<Character> list = new ArrayList<Character>(expressionSet);
		String[] variableArray = new String[expressionSet.size()];
		
		int binaryIndex = binary.length() - 1;

		for (int i = expressionSet.size() - 1; i >= 0; i--) {
			if (binaryIndex < 0) {
				variableArray[i] = list.get(i)+" = "+0;
			} else {
				variableArray[i] = list.get(i)+" = "+ (binary.charAt(binaryIndex) - '0');
			}
			binaryIndex--;
		}
		return variableArray;
	}

	public static Integer expressionEvaluation(String expr, String[] userVar) {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
		try {
			for (String s : userVar) {
				engine.eval(s);
			}
			return (Integer)engine.eval(expr);
		} catch (ScriptException e) {
			System.out.println("Expression Invalid");
		}
		return 0;
	}

}
