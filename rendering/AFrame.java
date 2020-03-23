package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import events.ErrorMessageEvent;
import events.LoopingEvent;
import events.TimedEvent;
import main.Asmura;

public class AFrame extends JFrame implements KeyListener {
	
	// For reasons unknown to me
	public static final long serialVersionUID = 378294032845375L;
	
	// The size of the window
	public static Dimension windowSize = new Dimension(800, 600);
	
	public APanel panel;
	
	// The state of the window
	private String state;
	
	public Asmura game;
	
	public TextRenderer textRenderer;
	
	// Constructor (like __init__ in Python, roughly equal to C++)
	public AFrame(Asmura game) {
		// Set title to "Return from Asmura"
		super("Return from Asmura");
		// Set size
		super.setPreferredSize(windowSize);
		// Make the window visible (why does this needs to be a thing?)
		super.setVisible(true);
		// Make the program exit when the window closes
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Sizes everything correctly
		super.pack();		
		addKeyListener(this);	
		this.panel = new APanel();
		this.add(this.panel);
		// For menu stuff
		this.state = "menu";
		this.game = game;
		this.textRenderer = new TextRenderer(this.panel);
	}
	
	@Override
	public void repaint() {
		this.panel.repaint();
		// Updating every TimedEvent object
		for (TimedEvent event : panel.events) {
			// If the event is starting, start it
			// If the event's timer has not run out, update it
			// If the event is done, remove it from the list
			// UNLESS the event is a LoopingEvent, in which case reset timer
			if (event.startTime == this.game.time) {
				event.commence();
			} else if (event.startTime > this.game.time - event.getLength()) {
				event.frame(this.game.time - event.startTime);
			} else {
				if (event instanceof LoopingEvent) {
					// For the next loop around
					event.startTime = this.game.time + 1;
				} else {
					event.end();
					panel.events.remove(event);
				}
			}
		}
	}
	
	// Runs on closing the window (AKA exiting the game)
	private void close() {
		// Actually closing the window
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		// TODO: Add cleanup code here if needed
	}
	
	// Run whenever a key is pressed
	public void keyPressed(KeyEvent keyEvent) {
		int key = keyEvent.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			this.close();
		} else if (key == KeyEvent.VK_ENTER) {
			// Enter starts game (changes background to white if in menu)
			if (this.state == "menu") {
				// TODO: Add functions for doing these together
				this.setStateMain();
			}
		} else if (key == KeyEvent.VK_Q) {
			// q toggles between escaped menu and main game
			if (this.state == "main") {
				this.setStateEscaped();
			} else if (this.state == "escaped") {
				this.setStateMain();
			}
		} else if (key == KeyEvent.VK_E) {
			new ErrorMessageEvent(this.game);
		}
	}
	
	// Changing screen states
	public void setStateMain() {
		this.state = "main";
		this.panel.bgColor = Color.WHITE;
		textRenderer.topText = "Main Game";
	}
	
	public void setStateEscaped() {
		this.state = "escaped";
		this.panel.bgColor = Color.GRAY;
		textRenderer.topText = "Escaped Menu";
	}
	
	// Methods we have to implement but don't actually use
	public void keyTyped(KeyEvent keyEvent) {}
	public void keyReleased(KeyEvent keyEvent) {}

}
