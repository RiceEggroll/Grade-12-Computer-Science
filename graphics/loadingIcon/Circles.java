package graphics.loadingIcon;

//Circles object class used in loading icon

import java.awt.Rectangle;

class Circles extends Rectangle{
	int cx, cy, radius, angle;

	Circles (int cx, int cy, int r) {
		this.cx = cx;
		this.cy = cy;
		radius = r;

		height = width = radius*2;
		x = this.cx - radius;
		y = this.cy - radius;
	}

	static void resetPosition(int rotation, Circles[] arrCircle, int xValue, int yValue) {
		//Switch case statement to track which rotation it needs to do
		//Sort of hard coded because I couldn't figure out how else to do it using the stupid rotate function
		switch(rotation%4) {
		case 0:
			xValue = 230;
			yValue = 180;
			for (int i = 0; i < arrCircle.length; i++) {
				arrCircle[i].x = xValue;
				arrCircle[i].y = yValue;
				xValue += 100;
			}
			break;

		case 1:
			xValue = 230;
			yValue = 580;
			for (int i = 0; i < arrCircle.length; i++) {
				arrCircle[i].x = xValue;
				arrCircle[i].y = yValue;
				yValue -= 100;
			}
			break;

		case 2:
			xValue = 630;
			yValue = 580;
			for (int i = 0; i < arrCircle.length; i++) {
				arrCircle[i].x = xValue;
				arrCircle[i].y = yValue;
				xValue -= 100;
			}
			break;

		case 3:
			xValue = 630;
			yValue = 180;
			for (int i = 0; i < arrCircle.length; i++) {
				arrCircle[i].x = xValue;
				arrCircle[i].y = yValue;
				yValue += 100;
			}
			break;
		}
	}
}
