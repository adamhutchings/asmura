package events;

import main.Asmura;

public abstract class TimedEvent {

	Asmura game;
	
	// How long, in frames, the event lasts
	public abstract int getLength();
	
	// When the thing was started
	public int startTime;
	
	public TimedEvent(Asmura game) {
		startTime = game.time;
		this.game = game;
		this.game.frame.panel.events.add(this);
		this.commence();
	}
	
	// Things that happen
	
	// When the event begins.
	public abstract void commence();
	
	// At other stages of the event
	// (time is frames since beginning time)
	public abstract void frame(int time);
	
	// When the event ends
	public abstract void end();

}
