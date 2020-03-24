package events;

import main.Asmura;

public class ErrorMessageEvent extends TimedEvent {
	
	Asmura game;
	
	int startTime;
	
	@Override
	public int getLength() {
		return 30;
	}
	
	public ErrorMessageEvent(Asmura game) {
		// Lasts 30 frames, or 1 second.
		super(game);
	}
	
	public void commence() {
		this.game.frame.textRenderer.errorText = "ERROR: No pressing e.";
	}
	
	public void frame(int frameNum) {
		// Does nothing every frame
	}
	
	// Remove error text (make it blank)
	public void end() {
		this.
		game.
		frame.
		textRenderer.
		errorText = "";
	}

}
