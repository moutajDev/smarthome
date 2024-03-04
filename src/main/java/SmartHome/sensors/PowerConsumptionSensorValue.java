package SmartHome.sensors;

import SmartHome.domain.Value;

public class PowerConsumptionSensorValue implements Value, Cloneable {
    public double _dValue;

    /**
     * Creates a new PowerConsumptionSensorValue with a given value.
     * @param dValue the value to be set.
     */

    public PowerConsumptionSensorValue(double dValue) {
        setValue(dValue);
    }

    /**
     * Gets the value of the PowerConsumptionSensorValue.
     * @return the value of the PowerConsumptionSensorValue.
     */

    private void setValue(double dValue) {
        if (dValue < 0)
            throw new IllegalArgumentException("Value must be positive");
        this._dValue = dValue;
    }

    /**
     * Clones the PowerConsumptionSensorValue.
     * @return a new PowerConsumptionSensorValue with the same value.
     */

    public String toString() {
        return this._dValue + "";
    }

    /**
     * Clones the PowerConsumptionSensorValue.
     * @return a new PowerConsumptionSensorValue with the same value.
     */

    @Override
    public PowerConsumptionSensorValue clone() {
        try {
            return (PowerConsumptionSensorValue) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
