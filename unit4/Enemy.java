package unit4;

import java.awt.Rectangle;

class Enemy extends Rectangle{

	int speed = 3;
	int damage = 15;
	
	//Needed for the player class
	Enemy(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	
	Enemy() {
		this.x = (int) (Math.random()*(AnimationGameExample.panW-15)) + 10;
		this.y = (int) (Math.random()*100) - 150;
		width = height = 15;
		
		speed = (int) (Math.random()*4);
	}
}
