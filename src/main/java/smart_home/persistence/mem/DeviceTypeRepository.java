package smart_home.persistence.mem;

import smart_home.ddd.IRepository;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.repository.IDeviceTypeRepository;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceTypeID;

import java.util.*;


public class DeviceTypeRepository implements IDeviceTypeRepository {

    // Data structure to store device types
    private final Map<DeviceTypeID, DeviceType> DATA = new LinkedHashMap<>();

    /**
     * Saves a device type to the repository.
     *
     * @param deviceType The device type to save.
     * @return The saved device type.
     * @throws IllegalArgumentException If the deviceType is null or already exists in the repository.
     */
    @Override
    public DeviceType save(DeviceType deviceType) {
        Validator.validateNotNull(deviceType, "DeviceType");

        if (containsOfIdentity(deviceType.getID())) {
            throw new IllegalArgumentException("DeviceType already exists.");
        } else {
            DATA.put(deviceType.getID(), deviceType);
        }
        return deviceType;
    }

    /**
     * Retrieves all device types from the repository.
     *
     * @return A list containing all device types stored in the repository.
     */
    @Override
    public List<DeviceType> findAll() {
        List<DeviceType> allDeviceTypes = new ArrayList<>(DATA.values());
        return allDeviceTypes;
    }

    /**
     * Retrieves a device type from the repository based on its identity.
     *
     * @param deviceTypeID The identity of the device type to retrieve.
     * @return An Optional containing the retrieved device type, or empty if not found.
     */
    @Override
    public Optional<DeviceType> ofIdentity(DeviceTypeID deviceTypeID) {
        return Optional.ofNullable(DATA.get(deviceTypeID));
    }

    /**
     * Checks if a device type with the given identity exists in the repository.
     *
     * @param deviceTypeID The identity of the device type to check.
     * @return true if the device type exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(DeviceTypeID deviceTypeID) {
        return DATA.containsKey(deviceTypeID);
    }
}


