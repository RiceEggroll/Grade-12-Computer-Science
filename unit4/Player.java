package unit4;

class Player extends Enemy{
	int health = 100;
	Player() {
		this(AnimationGameExample.panW/2, AnimationGameExample.panH-200, 100, 60);
	}
	Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		//change speed here
	}
	
}
