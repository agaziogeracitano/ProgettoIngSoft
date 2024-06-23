package interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
/*
AnalizzatoreLessicale serve a trasformare una sequenza di caratteri in una sequenza di token
 */

public class AnalizzatoreLessicale {

	private StreamTokenizer input;
	private Simboli simbolo;

	public AnalizzatoreLessicale(Reader in) {

		input = new StreamTokenizer(in);
		input.resetSyntax();
		input.eolIsSignificant(false);//indica che i caratteri di fine linea non sono significativi
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('0', '9'); 
		input.whitespaceChars('\u0000', ' ');//caratteri di spazio
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar(',');
		input.ordinaryChar('\\'); //( ) , \\ sono caratteri ordinari, non fanno parte delle parole
		input.quoteChar('"');
		input.ordinaryChar('.');
	}
	
	public String getString() {
		return input.sval;
	}
	
	public Simboli prossimoSimbolo() {//legge il prossimo token e lo mappa su un valore dell'enum SIMBOLI
		try {
			switch( input.nextToken() ) {
			case StreamTokenizer.TT_EOF:
				simbolo = Simboli.EOF;
				break;
			case StreamTokenizer.TT_WORD:
				if( input.sval.equals("new") )
					simbolo = Simboli.NEW;
				else if( input.sval.equals("del") )
					simbolo = Simboli.DEL;
				else if( input.sval.equals("mv") )
					simbolo = Simboli.MV;
				else if( input.sval.equals("mvoff") )
					simbolo = Simboli.MVOFF;
				else if( input.sval.equals("scale") )
					simbolo = Simboli.SCALE;
				else if( input.sval.equals("ls") )
					simbolo = Simboli.LS;
				else if( input.sval.equals("all") )
					simbolo = Simboli.ALL;
				else if( input.sval.equals("groups") )
					simbolo = Simboli.GROUPS;
				else if( input.sval.equals("grp") )
					simbolo = Simboli.GRP;
				else if( input.sval.equals("ungrp") )
					simbolo = Simboli.UNGRP;
				else if( input.sval.equals("area") )
					simbolo = Simboli.AREA;
				else if( input.sval.equals("perimeter") )
					simbolo = Simboli.PERIMETER;
				else if( input.sval.equals("circle") )
					simbolo = Simboli.CIRCLE;
				else if( input.sval.equals("rectangle") )
					simbolo = Simboli.RECTANGLE;
				else if( input.sval.equals("img") )
					simbolo = Simboli.IMG;
				break;
			case '(':
				simbolo = Simboli.TONDA_APERTA;
				break;
				
			case ')':
				simbolo = Simboli.TONDA_CHIUSA;
				break;
			case '.':
				simbolo = Simboli.PUNTO;
				break;
			case ',':
				simbolo = Simboli.VIRGOLA;
				break;
			default:
					simbolo = Simboli.CHAR_INVALIDO;
			}
		} catch( IOException e ) {
			simbolo = Simboli.EOF;
		}
		return simbolo;
	}
}