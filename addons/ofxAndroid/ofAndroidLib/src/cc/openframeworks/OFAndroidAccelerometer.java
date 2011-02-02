package cc.openframeworks;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class OFAndroidAccelerometer extends OFAndroidObject {
	private SensorManager sensorManager;
    private Sensor accelerometer;
    
    OFAndroidAccelerometer(SensorManager sensorManager){

        this.sensorManager = sensorManager;
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensors.size() > 0)
        {
        	accelerometer = sensors.get(0);
        }
    }

	@Override
	protected void pause() {
		sensorManager.unregisterListener(sensorListener);
		
	}

	@Override
	protected void resume() {
		sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);   
	}

	@Override
	protected void stop() {
		sensorManager.unregisterListener(sensorListener);
	}
	
	
	private final SensorEventListener sensorListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent event) {
			try{
				updateAccelerometer(event.values[0],
				          event.values[1],
				          event.values[2]);
			}catch(UnsatisfiedLinkError e){}
		}
		 
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}
	};

	public static native void updateAccelerometer(float x, float y, float z);
    
}
