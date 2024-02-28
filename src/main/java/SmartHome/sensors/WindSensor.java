package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class WindSensor implements Sensor{

    private final SensorType _sensorType;

    /**
     * Constructor for WindSensor
     * @param catalogue
     * @throws InstantiationException
     */
    public WindSensor(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("WindSpeedAndDirection");
        if( sensorType == null){
            throw new InstantiationException("\"SensorType with description 'WindSpeedAndDirection' does not exist.\"");
        }
        else {
            this._sensorType = sensorType;
        }
    }
    /**
     * Method to get the sensor type
     * @return SensorType
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Method to get the value of the sensor
     * @return
     */
    public Value getValue() {
        Random rand = new Random();
        int speed = rand.nextInt(408); //wind speed world record
        int directionIndex = rand.nextInt(8);
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        String direction = directions[directionIndex];
        return new WindSensorValue(speed, direction);
    }
}
