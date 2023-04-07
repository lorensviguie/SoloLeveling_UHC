package fr.farkas.Main.General.Timer;

public class TimerManager {
	
	private int timer;
	private boolean isRun;
	
	public TimerManager() {
		this.timer = 0;
		this.isRun = false;
	}
	
	public void ResetTimer() {
		this.timer = 0;
	}
	
	public void Stop() {
		this.isRun = false;
	}
	
	public void Start() {
		this.isRun = true;
	}
	
	public void AddSecond() {
		if(isRun) {
			this.timer += 1;
		}
	}
	
	public String GetTimeString() {
		int min = this.timer/60;
		int sec = this.timer%60;
		String minS = Integer.toString(min);
		String secS = Integer.toString(sec);
		if(min < 10) {
			minS = "0"+minS;
		}
		if(sec < 10) {
			secS = "0"+secS;
		}
		
		if(this.timer == 0) {
			return "00:00";
		}else {
			return String.format("%s:%s",minS,secS);
		}
	}

}
