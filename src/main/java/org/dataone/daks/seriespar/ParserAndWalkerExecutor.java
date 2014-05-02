
package org.dataone.daks.seriespar;


import java.io.*;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;


public class ParserAndWalkerExecutor {
	
	
	public ParserAndWalkerExecutor() {
		
	}  
	
	public static void main(String args[]) {
		ParserAndWalkerExecutor exec = new ParserAndWalkerExecutor();
		String text = exec.readFile(args[0]);
		System.out.println(text);
		exec.processASM(text);
	}
	
	
	public void processASM(String inputStr) {
    	
    	//Create a CharStream that reads from the input stream provided as a parameter
    	ANTLRStringStream input = new ANTLRStringStream(inputStr);
    	//Create a lexer that feeds-off of the input CharStream
    	ASMSimpleLexer lexer = new ASMSimpleLexer(input);
    	//Create a buffer of tokens pulled from the lexer
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	//Create a parser that feeds off the tokens buffer
    	ASMSimpleParser parser = new ASMSimpleParser(tokens);
    	//Begin parsing at rule query
    	ASMSimpleParser.asm_return result = null;
		try {
			result = parser.asm();
		} catch (RecognitionException e1) {
			e1.printStackTrace();
		}
    	//Pull out the tree and cast it
    	Tree t = (Tree)result.getTree();    	
    	//CommonTree t = (CommonTree)result.getTree(); // extract the AST generated as a CommonTree
        System.out.println(t.toStringTree());  // print out the tree
        /*
        //Execute the walker over the AST generated by the parser
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
        nodes.setTokenStream(tokens);
        ASMAsaselVis walker = new ASMAsaselVis(nodes);
        walker.setWFBuilder(this.wfBuilder);
        //Execute the walker begining at the query rule
        try {
			walker.asm();
			TreeToJGraph presenter = walker.treeToJGraph;
			presenter.createJGraph();
			presenter.drawJGraph();
			this.graph = presenter.getJGraph();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		*/
	}

	
	private String readFile(String filename) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			String line = null;
			String NEWLINE = System.getProperty("line.separator");
			reader = new BufferedReader(new FileReader(filename));
			while( (line = reader.readLine()) != null ) {
				builder.append(line + NEWLINE);
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

		
}

	


