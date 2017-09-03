package com.core.framework.utils;

public class Timer {

private long startTime = 0;
private long stopTime = 0;
private boolean running = false;


public void start() {
    this.startTime = System.currentTimeMillis();
    this.running = true;
}


public void stop() {
    this.stopTime = System.currentTimeMillis();
    this.running = false;
}


//elaspsed time in milliseconds
public long getElapsedTimeMilliSec() {
    long elapsed;
    if (running) {
         elapsed = (System.currentTimeMillis() - startTime);
    }
    else {
        elapsed = (stopTime - startTime);
    }
    return elapsed;
}


	//elaspsed time in seconds
	public long getElapsedTimeSecs() {
	    long elapsed;
	    if (running) {
	        elapsed = ((System.currentTimeMillis() - startTime) / 1000);
	    }
	    else {
	        elapsed = ((stopTime - startTime) / 1000);
	    }  
	    return elapsed;
	}

	public String getElapsedTime()
	{
	    long milliTime = System.currentTimeMillis() - this.startTime;
	    int[] out = new int[]{0, 0, 0, 0};
	    out[0] = (int)(milliTime / 3600000      );
	    out[1] = (int)(milliTime / 60000        ) % 60;
	    out[2] = (int)(milliTime / 1000         ) % 60;
	    out[3] = (int)(milliTime)                 % 1000;
	
	    return out[0] + ":" + out[1] + ":" + out[2];
	}

}

