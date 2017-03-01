// Generated from D:/Java/EvolutionaryAlgorithm/LaboratoryWork4\Formula.g4 by ANTLR 4.5.1

	package com.rukin.laboratory4.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulaParser}.
 */
public interface FormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(FormulaParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(FormulaParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(FormulaParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(FormulaParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FormulaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FormulaParser.LiteralContext ctx);
}