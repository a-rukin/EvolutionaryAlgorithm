grammar Formula;

@header {
	package com.rukin.laboratory4.antlr;
	import com.rukin.laboratory4.entity.*;
	import com.rukin.laboratory4.entity.operator.*;
    import java.util.*;
}

expression returns [Node expr]
    @init {List<Node> args = null;}
    : primary
    {$expr = $primary.expr;}
    | e=expression '(' (expressionList{args = $expressionList.args;})? ')'
    {$expr = Node.getNode($e.text, args.toArray(new Node[args.size()]));}
    | e1=expression '^' e2=expression
    {$expr = Node.getNode("^", $e1.expr, $e2.expr);}
    | e1=expression sign=('*'|'/') e2=expression
    {$expr = Node.getNode($sign.text, $e1.expr, $e2.expr);}
    | e1=expression sign=('+'|'-') e2=expression
    {$expr = Node.getNode($sign.text, $e1.expr, $e2.expr);}
    ;

expressionList returns [List<Node> args = new ArrayList<>()]
    : expression{$args.add($expression.expr);} (',' expression{$args.add($expression.expr);})*
    ;

primary returns [Node expr]
    : '(' expression ')'
    {$expr = $expression.expr;}
    | literal
    {$expr = new Node(new Constant(Double.parseDouble($literal.text)));}
    | Identifier
    {$expr = Node.getNode($Identifier.text);}
    ;

literal
    : IntegerLiteral
    | FloatLiteral
    ;

Identifier
    : Letter LetterOrDigit*
    ;

IntegerLiteral : Number ;
FloatLiteral : FloatNumber ;

fragment Number : '0' | NonZeroDigit Digit* ;
fragment Digit : '0' | NonZeroDigit ;
fragment NonZeroDigit : [1-9] ;
fragment FloatNumber :   Number '.' Number+ ;

fragment Letter : [a-zA-Z] ;
fragment LetterOrDigit : [a-zA-Z0-9_] ;

WS : [ \t\u000C\r\n]+ -> skip ;