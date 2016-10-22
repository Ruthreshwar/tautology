/**
 * 
 */
package dev.interview.indix;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
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
		TreeSet<Character> expression = new TreeSet<Character>();
		expression.add('a');
		expression.add('b');

		int n = 2; // Count of hashset
		boolean tautologyCheck = true;
		for (int i = 0; i < Math.pow(2, n); i++) {
			String[] test = assignVariables(Integer.toBinaryString(i), expression);
			Integer value = expressionEvaluation("(!a | (a & a)) ", test);
			if(value == 0){
				System.out.println("Not a tautology");
				tautologyCheck = false;
				break;
			} 
		}
		if(tautologyCheck){
			System.out.println("Tautology!!!!");
		}
	}

	public static String[] assignVariables(String binary, TreeSet expressionSet) {
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
			e.printStackTrace();
		}
		return 0;
	}

}
