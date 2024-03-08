package SmartHome.sensors;

import SmartHome.domain.Value;

public class DewPointValue implements Value{
    private int _dewPointValue;


    /**
     * Constructor of the class.
     *
     * @param dewPointValue The value of the dew point.
     */
    public DewPointValue(int dewPointValue) {
        if (dewPointValue < -100)
            throw new IllegalArgumentException("The value of the dew point cannot be lower than -100.");
        this._dewPointValue = dewPointValue;
    }

     /**
     * Gets the value of the dew point.
     *
     * @return The value of the dew point.
     */
    public String toString() {
        return this._dewPointValue + "";
    }



}
