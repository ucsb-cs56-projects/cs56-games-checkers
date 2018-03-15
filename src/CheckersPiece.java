package edu.ucsb.cs56.projects.games.checkers;

import java.util.*;

public class CheckersPiece{
    private char name;
    private int xcoord;
    private int ycoord;
    private boolean king;

    public CheckersPiece(char n, int x, int y, boolean k){
	name = n;
	xcoord = x;
	ycoord = y;
	king = k;
    }

    public char getName(){
	return name;
    }

    public int getX(){
	return xcoord;
    }

    public int getY(){
	return ycoord;
    }

    public boolean isKing(){
	return king;
    }

    public void setName(char n){
	name = n;
    }
    
    public void setCoord(int x, int y){
	xcoord=x;
	ycoord=y;
    }

    public void setKing(){
	king=true;
    }
}
