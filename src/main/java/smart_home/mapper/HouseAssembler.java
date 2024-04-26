package smart_home.mapper;

import smart_home.ddd.IAssembler;
import smart_home.domain.house.House;
import smart_home.dto.HouseDTO;
import smart_home.utils.Validator;

import java.util.List;

public class HouseAssembler implements IAssembler<House, HouseDTO> {

    /**
     * Method to convert a House into a HouseDTO.
     *
     * @param house is the House to be converted.
     * @return the HouseDTO.
     */
    @Override
    public HouseDTO domainToDTO(final House house) {
        Validator.validateNotNull(house, "House");
        String address = house.getAddress().toString();
        String gps = house.getGps().toString();

        HouseDTO houseDTO = new HouseDTO(address, gps);
        return houseDTO;
    }

    /**
     * Method to convert a list of Houses into a list of HouseDTOs.
     *
     * @param houses is the list of Houses to be converted.
     * @return the list of HouseDTOs.
     */
    @Override
    public List<HouseDTO> domainToDTO(final List<House> houses) {
        if (houses == null || houses.isEmpty())
            throw new IllegalArgumentException("The list of Houses cannot be null or empty.");

        List<HouseDTO> housesDTO = houses.stream().map(this::domainToDTO).toList();
        return housesDTO;
    }

}


