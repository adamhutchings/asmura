package rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TextRenderer extends Renderable {
	
	// Contains all the fields of text that must be rendered.
	
	public APanel panel;
	
	public TextRenderer(APanel panel) {
		super(panel);
		this.panel = panel;
		this.topText = "Main Menu";
		this.errorText = "";
	}
	
	// What to write at the top
	public String topText;
	
	public String errorText;
	
	public void render(Graphics g) {
		// Write top text
		// BUT in a blue color (for now)
		g.setColor(Color.BLUE);
		Renderable.drawCenteredString(g, topText, titleRect, titleFont);
		g.setColor(Color.RED);
		Renderable.drawCenteredString(g, errorText, errorRect, errorFont);
	}
	
	// Static Font and Rectangle members for specific instances
	
	public static Font titleFont = new Font("TimesRoman", Font.BOLD, 100);
	public static Rectangle titleRect = new Rectangle(0, 0, 800, 100);
	
	public static Font errorFont = new Font("TimesRoman", Font.BOLD, 25);
	public static Rectangle errorRect = new Rectangle(0, 450, 800, 100);

}
