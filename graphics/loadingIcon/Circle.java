package graphics.loadingIcon;

import java.awt.Rectangle;

class Circle extends Rectangle{
	int cx, cy, radius, vx,vy;
	
	Circle (int cx, int cy, int r) {
		this.cx = cx;
		this.cy = cy;
		radius = r;
		
		vx = 10;
		vy = 10;
	}
	
	void reCalc() {
		height = width = radius*2;
		x = this.cx - radius;
		y = this.cy - radius;
	}
	
	void oscillate(int tick) {
		radius = (int) (50 + 10*Math.sin(tick/5.0));
	}
	
	void move() {
		x += vx;
		y += vy;
		
		if (x == LoadingIconTest.PANW) vx = -10;
		if (y == LoadingIconTest.PANH) vy = -10;
		if (x == 0) vx = 10;
		if (y == 0) vy = 10;
		
	}
}
