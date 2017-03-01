// Generated from D:/Java/EvolutionaryAlgorithm/LaboratoryWork4\Formula.g4 by ANTLR 4.5.1

	package com.rukin.laboratory4.antlr;
	import com.rukin.laboratory4.entity.Node;
	import com.rukin.laboratory4.entity.operator.Constant;

	import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
	import org.antlr.v4.runtime.tree.*;
import java.util.List;
	import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, Identifier=9, 
		IntegerLiteral=10, FloatLiteral=11, WS=12;
	public static final int
		RULE_expression = 0, RULE_expressionList = 1, RULE_primary = 2, RULE_literal = 3;
	public static final String[] ruleNames = {
		"expression", "expressionList", "primary", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'^'", "'*'", "'/'", "'+'", "'-'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "Identifier", "IntegerLiteral", 
		"FloatLiteral", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Formula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public Node expr;
		public ExpressionContext e;
		public ExpressionContext e1;
		public PrimaryContext primary;
		public ExpressionContext e2;
		public Token sign;
		public ExpressionListContext expressionList;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		List<Node> args = null;
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(9);
			((ExpressionContext)_localctx).primary = primary();
			((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).primary.expr;
			}
			_ctx.stop = _input.LT(-1);
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(36);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(12);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(13);
						match(T__2);
						setState(14);
						((ExpressionContext)_localctx).e2 = expression(4);
						((ExpressionContext)_localctx).expr =  Node.getNode("^", ((ExpressionContext)_localctx).e1.expr, ((ExpressionContext)_localctx).e2.expr);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(17);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(18);
						((ExpressionContext)_localctx).sign = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
							((ExpressionContext)_localctx).sign = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(19);
						((ExpressionContext)_localctx).e2 = expression(3);
						((ExpressionContext)_localctx).expr =  Node.getNode((((ExpressionContext)_localctx).sign!=null?((ExpressionContext)_localctx).sign.getText():null), ((ExpressionContext)_localctx).e1.expr, ((ExpressionContext)_localctx).e2.expr);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(22);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(23);
						((ExpressionContext)_localctx).sign = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((ExpressionContext)_localctx).sign = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(24);
						((ExpressionContext)_localctx).e2 = expression(2);
						((ExpressionContext)_localctx).expr =  Node.getNode((((ExpressionContext)_localctx).sign!=null?((ExpressionContext)_localctx).sign.getText():null), ((ExpressionContext)_localctx).e1.expr, ((ExpressionContext)_localctx).e2.expr);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(27);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(28);
						match(T__0);
						setState(32);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Identifier) | (1L << IntegerLiteral) | (1L << FloatLiteral))) != 0)) {
							{
							setState(29);
							((ExpressionContext)_localctx).expressionList = expressionList();
							args = ((ExpressionContext)_localctx).expressionList.args;
							}
						}

						setState(34);
						match(T__1);
						((ExpressionContext)_localctx).expr =  Node.getNode((((ExpressionContext)_localctx).e!=null?_input.getText(((ExpressionContext)_localctx).e.start,((ExpressionContext)_localctx).e.stop):null), args.toArray(new Node[args.size()]));
						}
						break;
					}
					} 
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<Node> args =  new ArrayList<>();
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			((ExpressionListContext)_localctx).expression = expression(0);
			_localctx.args.add(((ExpressionListContext)_localctx).expression.expr);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(43);
				match(T__7);
				setState(44);
				((ExpressionListContext)_localctx).expression = expression(0);
				_localctx.args.add(((ExpressionListContext)_localctx).expression.expr);
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public Node expr;
		public ExpressionContext expression;
		public LiteralContext literal;
		public Token Identifier;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(FormulaParser.Identifier, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_primary);
		try {
			setState(62);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__0);
				setState(53);
				((PrimaryContext)_localctx).expression = expression(0);
				setState(54);
				match(T__1);
				((PrimaryContext)_localctx).expr =  ((PrimaryContext)_localctx).expression.expr;
				}
				break;
			case IntegerLiteral:
			case FloatLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				((PrimaryContext)_localctx).literal = literal();
				((PrimaryContext)_localctx).expr =  new Node(new Constant(Double.parseDouble((((PrimaryContext)_localctx).literal!=null?_input.getText(((PrimaryContext)_localctx).literal.start,((PrimaryContext)_localctx).literal.stop):null))));
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				((PrimaryContext)_localctx).Identifier = match(Identifier);
				((PrimaryContext)_localctx).expr =  Node.getNode((((PrimaryContext)_localctx).Identifier!=null?((PrimaryContext)_localctx).Identifier.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(FormulaParser.IntegerLiteral, 0); }
		public TerminalNode FloatLiteral() { return getToken(FormulaParser.FloatLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==IntegerLiteral || _la==FloatLiteral) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		case 3:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16E\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2#\n\2\3\2\3\2\7\2\'\n"+
		"\2\f\2\16\2*\13\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\62\n\3\f\3\16\3\65\13\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\5\3\5\3\5\2\3\2\6"+
		"\2\4\6\b\2\5\3\2\6\7\3\2\b\t\3\2\f\rH\2\n\3\2\2\2\4+\3\2\2\2\6@\3\2\2"+
		"\2\bB\3\2\2\2\n\13\b\2\1\2\13\f\5\6\4\2\f\r\b\2\1\2\r(\3\2\2\2\16\17\f"+
		"\5\2\2\17\20\7\5\2\2\20\21\5\2\2\6\21\22\b\2\1\2\22\'\3\2\2\2\23\24\f"+
		"\4\2\2\24\25\t\2\2\2\25\26\5\2\2\5\26\27\b\2\1\2\27\'\3\2\2\2\30\31\f"+
		"\3\2\2\31\32\t\3\2\2\32\33\5\2\2\4\33\34\b\2\1\2\34\'\3\2\2\2\35\36\f"+
		"\6\2\2\36\"\7\3\2\2\37 \5\4\3\2 !\b\2\1\2!#\3\2\2\2\"\37\3\2\2\2\"#\3"+
		"\2\2\2#$\3\2\2\2$%\7\4\2\2%\'\b\2\1\2&\16\3\2\2\2&\23\3\2\2\2&\30\3\2"+
		"\2\2&\35\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\3\3\2\2\2*(\3\2\2\2+"+
		",\5\2\2\2,\63\b\3\1\2-.\7\n\2\2./\5\2\2\2/\60\b\3\1\2\60\62\3\2\2\2\61"+
		"-\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\5\3\2\2\2\65\63"+
		"\3\2\2\2\66\67\7\3\2\2\678\5\2\2\289\7\4\2\29:\b\4\1\2:A\3\2\2\2;<\5\b"+
		"\5\2<=\b\4\1\2=A\3\2\2\2>?\7\13\2\2?A\b\4\1\2@\66\3\2\2\2@;\3\2\2\2@>"+
		"\3\2\2\2A\7\3\2\2\2BC\t\4\2\2C\t\3\2\2\2\7\"&(\63@";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}