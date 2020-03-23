package events;

import main.Asmura;

public abstract class TimedEvent {

	Asmura game;
	
	// How long, in frames, the event lasts
	public abstract int getLength();
	
	// When the thing was started
	public int startTime;
	
	public TimedEvent(int time, Asmura game) {
		startTime = time;
		this.game = game;
	}
	
	// Things that happen
	
	// When the event begins.
	public abstract void commence();
	
	// At other stages of the event
	// (time is frames since beginning time)
	public abstract void frame(int time);

}
