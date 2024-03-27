package SmartHomeDDD.domain.MeasurementType;

import SmartHomeDDD.valueObject.MeasurementTypeUnit;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;

/**
 * Factory implementation for creating {@link MeasurementType} instances.
 */
public class ImpMeasurementTypeTypeFactory implements MeasurementTypeFactory {
    /**
     * Creates and returns a new {@link MeasurementType} instance with the provided measurement value, measurement type, and measurement time.
     */

    public MeasurementType createMeasurement(MeasurementTypeDescription measurementTypeDescription, MeasurementTypeUnit measurementTypeUnit) {
        return new MeasurementType(measurementTypeDescription, measurementTypeUnit);
    }
}
