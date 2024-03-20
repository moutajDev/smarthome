package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.DomainID;

public class ActuatorTypeID implements DomainID {

    private String _id;

    /**
     * Constructor for the ActuatorTypeID class.
     *
     * @param actuatorTypeID is the ID of the actuator type.
     */
    public ActuatorTypeID(String actuatorTypeID) {
        if (actuatorTypeID != null && !actuatorTypeID.isBlank() && !actuatorTypeID.isEmpty())
            this._id = actuatorTypeID;
        else
            throw new IllegalArgumentException("The value of 'actuatorTypeID' should not null, blank, or empty.");
    }

    /**
     * Equals method for ActuatorTypeID.
     *
     * @param object Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof ActuatorTypeID) {
            ActuatorTypeID actuatorTypeID = (ActuatorTypeID) object;

            if (this._id.equals(actuatorTypeID._id))
                return true;
        }
        return false;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of the actuator type.
     */
    @Override
    public String getId() {
        return this._id;
    }

    /**
     * HashCode method for ActuatorTypeID.
     *
     * @return
     */
    public int hashCode() {
        return this._id.hashCode();
    }


}
