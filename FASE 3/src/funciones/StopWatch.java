package funciones;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {
    Timer timer;

    public StopWatch(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Task is complete.");
            }
            }, seconds * 1000);
    }

    class StopTask extends TimerTask {
        public void run() {
            System.out.println("Time Up!");
            timer.cancel();
        }
    }
}
