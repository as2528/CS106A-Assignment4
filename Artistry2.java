// Creates a map of the solar system
//input:nothimng
//output: Map of the solar system

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Artistry2 extends GraphicsProgram {
	public void run() {
		GOval sun = new GOval(50,150,300,300);
		sun.setFilled(true);
		sun.setFillColor(Color.YELLOW);
		add(sun);
		GOval mercury = new GOval(200,200, 10,10);
		mercury.setFilled(true);;
		add(mercury);
		GOval venus = new GOval(250,200, 20,20);
		venus.setFilled(true);
		venus.setFillColor(Color.ORANGE);
		add(venus);
		GOval earth = new GOval(300,200, 25,25);
		earth.setFilled(true);
		earth.setFillColor(Color.CYAN);
		add(earth);
		GOval mars = new GOval(350,200, 23,23);
		mars.setFilled(true);
		mars.setFillColor(Color.RED);
		add(mars);
		GOval jupiter = new GOval(400,200, 100,100);
		jupiter.setFilled(true);
		jupiter.setFillColor(Color.ORANGE);
		add(jupiter);
		GOval saturn = new GOval(550,200, 90,90);
		saturn.setFilled(true);
		saturn.setFillColor(Color.ORANGE);
		add(saturn);
		GRect satRings = new GRect(550,230, 90,20);
		satRings.setFilled(true);
		satRings.setFillColor(Color.PINK);
		add(satRings);
		GOval neptune = new GOval(650,200, 30,30);
		neptune.setFilled(true);
		neptune.setFillColor(Color.BLUE);
		add(neptune);
		GOval uranus = new GOval(700,200, 40,20);
		uranus.setFilled(true);
		uranus.setFillColor(Color.CYAN);
		add(uranus);
		GLabel name = new GLabel("Artistry by Ajit Singh");
		add(name,645,465);
	}
}
