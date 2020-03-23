package rendering;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

import javax.swing.JPanel;

import events.TimedEvent;

public class APanel extends JPanel {
	
	// Add this for Java knows what reason
	private static final long serialVersionUID = -23721312574526L;
	
	Color bgColor;
	
	// All objects to be rendered onto the screen.
	ArrayList<Renderable> objects;
	
	// All events to be handled
	public ArrayList<TimedEvent> events;
	
	public APanel() {
		super();
		// For menu
		this.bgColor = Color.BLACK;
		objects = new ArrayList<>();
		events = new ArrayList<>();
	}
	
	// repaint calls this with the appropriate Graphics object.
	@Override
	public void paintComponent(Graphics g) {
		// Draw over the entire screen in the background color.
		g.setColor(bgColor);
		g.fillRect(0, 0, 800, 600);
		// Render every Renderable object
		for (Renderable object : objects) {
			object.render(g);
		}
	}

}
