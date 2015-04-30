/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

// Generated from /home/dbborens/IdeaProjects/protoverse/src/Nanosyntax.g4 by ANTLR 4.5
package compiler.pipeline.interpret.nanosyntax;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NanosyntaxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, ID=5, STRING=6, FLOAT=7, INTEGER=8, WS=9, 
		COMMENT=10, LINE_COMMENT=11;
	public static final int
		RULE_root = 0, RULE_statement = 1, RULE_assignment = 2, RULE_block = 3, 
		RULE_singleton = 4, RULE_id = 5, RULE_primitive = 6, RULE_stringPrimitive = 7, 
		RULE_floatPrimitive = 8, RULE_intPrimitive = 9;
	public static final String[] ruleNames = {
		"root", "statement", "assignment", "block", "singleton", "id", "primitive", 
		"stringPrimitive", "floatPrimitive", "intPrimitive"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "ID", "STRING", "FLOAT", "INTEGER", "WS", 
		"COMMENT", "LINE_COMMENT"
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
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Nanosyntax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NanosyntaxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(20); 
				statement();
				}
				}
				setState(25);
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

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); 
			assignment();
			setState(27); 
			match(T__0);
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

	public static class AssignmentContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SingletonContext singleton() {
			return getRuleContext(SingletonContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			setState(36);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29); 
				id();
				setState(30); 
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32); 
				id();
				setState(33); 
				match(T__1);
				setState(34); 
				singleton();
				}
				break;
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

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); 
			match(T__2);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(39); 
				statement();
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45); 
			match(T__3);
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

	public static class SingletonContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public PrimitiveContext primitive() {
			return getRuleContext(PrimitiveContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public SingletonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleton; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitSingleton(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingletonContext singleton() throws RecognitionException {
		SingletonContext _localctx = new SingletonContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_singleton);
		try {
			setState(50);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47); 
				id();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48); 
				primitive();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(49); 
				assignment();
				}
				break;
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

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NanosyntaxParser.ID, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); 
			match(ID);
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

	public static class PrimitiveContext extends ParserRuleContext {
		public StringPrimitiveContext stringPrimitive() {
			return getRuleContext(StringPrimitiveContext.class,0);
		}
		public FloatPrimitiveContext floatPrimitive() {
			return getRuleContext(FloatPrimitiveContext.class,0);
		}
		public IntPrimitiveContext intPrimitive() {
			return getRuleContext(IntPrimitiveContext.class,0);
		}
		public PrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveContext primitive() throws RecognitionException {
		PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primitive);
		try {
			setState(57);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(54); 
				stringPrimitive();
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(55); 
				floatPrimitive();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(56); 
				intPrimitive();
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

	public static class StringPrimitiveContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(NanosyntaxParser.STRING, 0); }
		public StringPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringPrimitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitStringPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringPrimitiveContext stringPrimitive() throws RecognitionException {
		StringPrimitiveContext _localctx = new StringPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stringPrimitive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); 
			match(STRING);
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

	public static class FloatPrimitiveContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(NanosyntaxParser.FLOAT, 0); }
		public FloatPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatPrimitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitFloatPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatPrimitiveContext floatPrimitive() throws RecognitionException {
		FloatPrimitiveContext _localctx = new FloatPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_floatPrimitive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); 
			match(FLOAT);
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

	public static class IntPrimitiveContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NanosyntaxParser.INTEGER, 0); }
		public IntPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intPrimitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitIntPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntPrimitiveContext intPrimitive() throws RecognitionException {
		IntPrimitiveContext _localctx = new IntPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_intPrimitive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); 
			match(INTEGER);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\rD\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\'\n\4\3\5\3\5\7\5+\n\5\f\5\16\5.\13\5\3\5\3\5\3\6\3\6\3\6\5\6\65"+
		"\n\6\3\7\3\7\3\b\3\b\3\b\5\b<\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\2\2\f"+
		"\2\4\6\b\n\f\16\20\22\24\2\2@\2\31\3\2\2\2\4\34\3\2\2\2\6&\3\2\2\2\b("+
		"\3\2\2\2\n\64\3\2\2\2\f\66\3\2\2\2\16;\3\2\2\2\20=\3\2\2\2\22?\3\2\2\2"+
		"\24A\3\2\2\2\26\30\5\4\3\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31"+
		"\32\3\2\2\2\32\3\3\2\2\2\33\31\3\2\2\2\34\35\5\6\4\2\35\36\7\3\2\2\36"+
		"\5\3\2\2\2\37 \5\f\7\2 !\5\b\5\2!\'\3\2\2\2\"#\5\f\7\2#$\7\4\2\2$%\5\n"+
		"\6\2%\'\3\2\2\2&\37\3\2\2\2&\"\3\2\2\2\'\7\3\2\2\2(,\7\5\2\2)+\5\4\3\2"+
		"*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\6\2"+
		"\2\60\t\3\2\2\2\61\65\5\f\7\2\62\65\5\16\b\2\63\65\5\6\4\2\64\61\3\2\2"+
		"\2\64\62\3\2\2\2\64\63\3\2\2\2\65\13\3\2\2\2\66\67\7\7\2\2\67\r\3\2\2"+
		"\28<\5\20\t\29<\5\22\n\2:<\5\24\13\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\17"+
		"\3\2\2\2=>\7\b\2\2>\21\3\2\2\2?@\7\t\2\2@\23\3\2\2\2AB\7\n\2\2B\25\3\2"+
		"\2\2\7\31&,\64;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}