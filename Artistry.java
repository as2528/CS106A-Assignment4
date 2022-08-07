// This program creates the Japanese Flag
//input: Nothing
//Output:Japanese Flag

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Artistry extends GraphicsProgram {
	public void run() {
		int width=200;
		int height=80;
		GRect rect = new GRect(7,8,width, height);
		add(rect);
		GOval circle = new GOval(85,25,width/5, height/2);
		circle.setFilled(true);
		circle.setFillColor(Color.RED);
		add(circle);
		GLabel name = new GLabel("Artistry by Ajit Singh");
		add(name,645,465);
	}
}
