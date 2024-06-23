package interpreter;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import shapes.controller.ObjectManager;
import shapes.model.GraphicObject;
import shapes.model.ImageObject;
import shapes.model.RectangleObject;
import javax.swing.*;

public class Parser {

	private AnalizzatoreLessicale lexer;
	private Simboli simbolo;
	private Comando comm;
	private ObjectManager objectManager;

	public Parser(ObjectManager objectManager,Reader in) throws IOException {
		lexer = new AnalizzatoreLessicale(in);
		this.objectManager=objectManager;
		comm = comando();
		atteso(Simboli.EOF);
	}

	public Comando getComando() {
		return comm;
	}


	private Comando comando() {
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.NEW ) {
			return creazione();
		}
		if( simbolo == Simboli.DEL) {
			return cancellazione();
		}
		if( simbolo == Simboli.MV ) {
			return spostamento();
		}
		if( simbolo == Simboli.MVOFF ) {
			return spostamentoOff();
		}
		if( simbolo == Simboli.SCALE ) {
			return ridimensiona();
		}
		if( simbolo == Simboli.LS ) {
			return lista();
		}
		if( simbolo == Simboli.GRP ) {
			return raggruppa();
		}
		if( simbolo == Simboli.UNGRP ) {
			return sciogli();
		}
		if( simbolo == Simboli.AREA ) {
			return area();
		}
		if( simbolo == Simboli.PERIMETER ) {
			perimetro();
		}
		return null;
	}

	private Comando spostamento(){
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		Point2D posizione = getPosition();
		return new MV(objectManager,objId, posizione);
	}

	private Comando lista(){
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.ALL ) {
			simbolo = lexer.prossimoSimbolo();
			return new LSAll(objectManager);
		}
		if( simbolo == Simboli.GROUPS ) {
			simbolo = lexer.prossimoSimbolo();
			return new LSGroup(objectManager);
		}
		if( simbolo == Simboli.CIRCLE || simbolo == Simboli.RECTANGLE || simbolo == Simboli.IMG ) {
			Simboli s = simbolo;
			simbolo = lexer.prossimoSimbolo();
			return new LsType(objectManager,s);
		}
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new LsId(objectManager,objId);
	}

	private Comando creazione(){
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.CIRCLE ) {
			GraphicObject go = objectManager.getPrototype("Circle");
			double radius = getRadius();
			go.scale(radius);
			Point2D posizione = getPosition();
			return new Creation(objectManager,go,posizione);
		}
		if( simbolo == Simboli.RECTANGLE ) {
			GraphicObject go = objectManager.getPrototype("Rectangle");
			Point2D dimensioni = getDimension();
			((RectangleObject)go).scale(dimensioni);
			Point2D posizione = getPosition();
			return new Creation(objectManager,go,posizione);
		}
		if( simbolo == Simboli.IMG ) {
			GraphicObject go = objectManager.getPrototype("Image");
			String percorso = getPath();
			((ImageObject) go).setImage(new ImageIcon("C:\\Users\\agazi\\IdeaProjects\\ProgettoIngSoft\\src\\"+percorso));
			Point2D posizione = getPosition();
			return new Creation(objectManager,go,posizione);
		}
		else throw new RuntimeException();
	}

	private Comando raggruppa(){
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		LinkedList<String> listObjId = getListObjId();
		listObjId.addFirst(objId);
		return new Gruop(objectManager,listObjId);
	}

	private Comando cancellazione(){
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new Delete(objectManager,objId);
	}

	private double parseDouble() {
		StringBuilder ris = new StringBuilder();
		ris.append(lexer.getString());
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.PUNTO);
		ris.append("."+lexer.getString());
		return Double.parseDouble(ris.toString());

	}

	private Comando sciogli(){
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new Degroup(objectManager,objId);
	}

	private Comando spostamentoOff(){
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		Point2D posizione = getPosition();
		return new MVOff(objectManager,objId, posizione);
	}

	private Point2D getDimension() {
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_APERTA);
		double puntoA = parseDouble();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.VIRGOLA);
		double puntoB = parseDouble();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return new Point2D.Double(puntoA, puntoB);
	}

	private Comando ridimensiona(){
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		double f = parseDouble();
		simbolo = lexer.prossimoSimbolo();
		return new Scale(objectManager,objId, f);
	}

	private double getRadius() {
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_APERTA);
		double ret = parseDouble();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return ret;
	}

	private String getPath() {
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_APERTA);
		String ret = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return ret;
	}

	private Point2D getPosition() {
		atteso(Simboli.TONDA_APERTA);
		double puntoA =  parseDouble();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.VIRGOLA);
		double puntoB = parseDouble();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return new Point2D.Double(puntoA, puntoB);
	}

	private Comando area(){
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.ALL ) {
			simbolo = lexer.prossimoSimbolo();
			return new AreaAll(objectManager);
		}
		if( simbolo == Simboli.CIRCLE || simbolo == Simboli.RECTANGLE || simbolo == Simboli.IMG ) {
			Simboli s = simbolo;
			simbolo = lexer.prossimoSimbolo();
			return new AreaType(objectManager,s);
		}
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new AreaID(objectManager,objId);
	}

	private Comando perimetro(){
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.ALL ) {
			simbolo = lexer.prossimoSimbolo();
			return new PerimeterAll(objectManager);
		}
		if( simbolo == Simboli.CIRCLE || simbolo == Simboli.RECTANGLE || simbolo == Simboli.IMG ) {
			Simboli s = simbolo;
			simbolo = lexer.prossimoSimbolo();
			return new PerimeterType(objectManager,s);
		}
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new PerimeterId(objectManager,objId);
	}

	private LinkedList<String> getListObjId() {
		simbolo = lexer.prossimoSimbolo();
		LinkedList<String> ret = new LinkedList<>();
		while( simbolo == Simboli.VIRGOLA ) {
			simbolo = lexer.prossimoSimbolo();
			ret.add(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
		}
		return ret;
	}


	private void atteso(Simboli s) {
		if (simbolo != s) {
			String msg = "";
			if(simbolo != null)
				msg = " trovato " + simbolo + " mentre si attendeva " + s;
			else
				msg = "Comando non esistente";
			throw new RuntimeException(msg);
		}
		simbolo = lexer.prossimoSimbolo();
	}

}
