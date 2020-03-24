package events;

import main.Asmura;

public abstract class LoopingEvent extends TimedEvent {
	
	// For events that start, run on their own, and then are restarted
	
	public LoopingEvent(Asmura game) {
		super(game);
	}
	
	// Because these things have nothing to update every frame
	@Override
	public void frame(int time) {}

}
