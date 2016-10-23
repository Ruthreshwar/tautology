/**
 * 
 */
package dev.interview.indix;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author Ruthreshwar Gajendiran
 *
 */
public class TautologyVerifierTest {
	
	@Test
	public void testSuccessCase(){
		TautologyVerifier tautoVerifier = new TautologyVerifier();
		
		String expression = "(a & (!b | b)) | (!a & (!b | b))";
		
		boolean result = tautoVerifier.tautologyVerifier(expression);
		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testFailureCase(){
		TautologyVerifier tautoVerifier = new TautologyVerifier();
		
		String expression = "(a & (!b | b))";
		
		boolean result = tautoVerifier.tautologyVerifier(expression);
		
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testMultipleVariablesFailureCase(){
		TautologyVerifier tautoVerifier = new TautologyVerifier();
		
		String expression = "(a & (!b | b))|(c & !d)| (!e & !f) | (!g & h) | (!i | !j)";
		
		boolean result = tautoVerifier.tautologyVerifier(expression);
		
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testMultipleVariablesSuccessCase(){
		TautologyVerifier tautoVerifier = new TautologyVerifier();
		
		String expression = "(a | (!b | b))|(c | d)| (e | f) | (g | h) | (i | j)";
		
		boolean result = tautoVerifier.tautologyVerifier(expression);
		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testMultipleSpacesSuccessCase(){
		TautologyVerifier tautoVerifier = new TautologyVerifier();
		
		String expression = "(!a      |       (a & a)) ";
		
		boolean result = tautoVerifier.tautologyVerifier(expression);
		
		Assert.assertEquals(true, result);
	}
	
}
