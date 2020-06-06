package offer.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import offer.model.JourneyOfferRequest;
import offer.rest.model.IntercityOfferRequestDTO;
import offer.rest.model.JourneyOfferDTO;
import offer.rest.model.RegionalOfferRequestDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.model.Address;
import offer.model.JourneyOffer;
import offer.rest.model.AddressDTO;
import offer.rest.model.JourneyOfferRequestDTO;

import static offer.enumeration.TransportTypeEnum.BUS;
import static offer.enumeration.TransportTypeEnum.TRAIN;

@Component
public class OfferMapper {

    private static final Map<String, List<Pair<String, Integer>>> STATION_DATA = ImmutableMap.of(
            "RIGA", Arrays.asList(Pair.of("Pragas iela", 1), Pair.of("Stacijas laukums", 2)),
            "DAUGAVPILS", Arrays.asList(Pair.of("Viestura iela", 10), Pair.of("Viestura iela", 10)),
            "VENTSPILS", Collections.singletonList(Pair.of("Kuldigas iela", 5)),
            "JELGAVA", Arrays.asList(Pair.of("Pasta iela", 26), Pair.of("Stacijas iela", 1))
    );

    public JourneyOfferDTO toDTOOffer(JourneyOffer offer) {
        JourneyOfferDTO dto = new JourneyOfferDTO();

        dto.setAddressFrom(toDTOAddress(offer.getAddressFrom()));
        dto.setAddressTo(toDTOAddress(offer.getAddressTo()));
        dto.setDepartureTime(offer.getDepartureTime());
        dto.setIntercityOffer(offer.getIntercityOffer());
        dto.setRegionalOffers(offer.getRegionalOffer());

        return dto;
    }

    public JourneyOfferRequest toDomainRequest(JourneyOfferRequestDTO requestDTO) {
        JourneyOfferRequest request = new JourneyOfferRequest();

        request.setAddressFrom(toDomainAddress(requestDTO.getAddressFrom()));
        request.setAddressTo(toDomainAddress(requestDTO.getAddressTo()));
        request.setCompanyName(requestDTO.getCompanyName());
        request.setOfferType(OfferTypeEnum.valueOf(requestDTO.getOfferType()));
        request.setPassenger(PassengerTypeEnum.valueOf(requestDTO.getPassenger()));
        if (!CollectionUtils.isEmpty(requestDTO.getTransportTypes()))
            request.setTransportTypes(requestDTO.getTransportTypes().stream().map(TransportTypeEnum::valueOf).collect(Collectors.toList()));
        request.setRouteNumbers(requestDTO.getRouteNumbers());
        request.setNumberOfTickets(requestDTO.getNumberOfTickets());
        request.setDepartureTime(requestDTO.getDepartureTime());
        request.setNumberOfLuggage(requestDTO.getNumberOfLuggage());
        request.setPlaceType(requestDTO.getPlaceType());

        return request;
    }

    public Pair<List<RegionalOfferRequestDTO>, List<IntercityOfferRequestDTO>> toRegionalAndIntercityOfferRequests
            (JourneyOfferRequestDTO requestDTO) {
        List<RegionalOfferRequestDTO> regionalOfferRequests = new ArrayList<>();
        List<IntercityOfferRequestDTO> intercityOfferRequests = new ArrayList<>();

        AddressDTO addressFrom = requestDTO.getAddressFrom();
        AddressDTO addressTo = requestDTO.getAddressTo();

        if (addressFrom.getCity().equals(addressTo.getCity())) {
            RegionalOfferRequestDTO offerRequestDTO = new RegionalOfferRequestDTO();
            offerRequestDTO.setAddressFrom(addressFrom);
            offerRequestDTO.setAddressTo(addressTo);
            offerRequestDTO.setCity(addressFrom.getCity());
            offerRequestDTO.setCompanyName(requestDTO.getCompanyName());
            offerRequestDTO.setOfferType(requestDTO.getOfferType());
            offerRequestDTO.setNumberOfTickets(requestDTO.getNumberOfTickets());
            offerRequestDTO.setPassenger(requestDTO.getPassenger());
            offerRequestDTO.setRouteNumbers(requestDTO.getRouteNumbers());
            offerRequestDTO.setTransportTypes(requestDTO.getTransportTypes());
            regionalOfferRequests.add(offerRequestDTO);
        } else if (addressFrom.getStreetName() == null && addressTo.getStreetName() == null) {
            List<String> transportTypes = requestDTO.getTransportTypes();
            if (CollectionUtils.isEmpty(transportTypes)) {
                transportTypes = Arrays.asList(BUS.name(), TRAIN.name());
            }
            transportTypes.forEach(transportType -> {
                IntercityOfferRequestDTO intercityOfferRequest = new IntercityOfferRequestDTO();
                intercityOfferRequest.setCompanyName(requestDTO.getCompanyName());
                intercityOfferRequest.setDepartureTime(requestDTO.getDepartureTime());
                intercityOfferRequest.setDepCity(addressFrom.getCity());
                intercityOfferRequest.setDestCity(addressTo.getCity());
                intercityOfferRequest.setTransportType(transportType);
                intercityOfferRequest.setNumberOfTickets(requestDTO.getNumberOfTickets());
                intercityOfferRequest.setNumberOfLuggage(requestDTO.getNumberOfLuggage());
                intercityOfferRequest.setPassenger(requestDTO.getPassenger());
                intercityOfferRequest.setPlaceType(requestDTO.getPlaceType());
                intercityOfferRequests.add(intercityOfferRequest);
            });
        } else {
            List<Pair<String, Integer>> foundStationDataForDepCity = STATION_DATA.get(addressFrom.getCity().toUpperCase());
            foundStationDataForDepCity.forEach(data -> {
                RegionalOfferRequestDTO offerRequestDTO = new RegionalOfferRequestDTO();
                offerRequestDTO.setCity(addressFrom.getCity());
                offerRequestDTO.setAddressFrom(addressFrom);
                AddressDTO destinationAddress = new AddressDTO();
                destinationAddress.setCity(addressFrom.getCity());
                destinationAddress.setStreetName(data.getLeft());
                destinationAddress.setHomeNumber(data.getRight().toString());
                offerRequestDTO.setAddressTo(destinationAddress);
                offerRequestDTO.setCity(addressFrom.getCity());
                offerRequestDTO.setCompanyName(requestDTO.getCompanyName());
                offerRequestDTO.setOfferType(requestDTO.getOfferType());
                offerRequestDTO.setNumberOfTickets(requestDTO.getNumberOfTickets());
                offerRequestDTO.setPassenger(requestDTO.getPassenger());
                offerRequestDTO.setRouteNumbers(requestDTO.getRouteNumbers());
                offerRequestDTO.setTransportTypes(requestDTO.getTransportTypes());
                regionalOfferRequests.add(offerRequestDTO);
            });

            List<Pair<String, Integer>> foundStationDataDestCity = STATION_DATA.get(addressTo.getCity().toUpperCase());
            foundStationDataDestCity.forEach(data -> {
                RegionalOfferRequestDTO offerRequestDTO = new RegionalOfferRequestDTO();
                offerRequestDTO.setAddressTo(addressTo);
                AddressDTO depAddress = new AddressDTO();
                depAddress.setCity(addressTo.getCity());
                depAddress.setStreetName(data.getKey());
                depAddress.setHomeNumber(data.getRight().toString());
                offerRequestDTO.setAddressFrom(depAddress);
                offerRequestDTO.setCity(addressTo.getCity());
                offerRequestDTO.setCompanyName(requestDTO.getCompanyName());
                offerRequestDTO.setOfferType(requestDTO.getOfferType());
                offerRequestDTO.setNumberOfTickets(requestDTO.getNumberOfTickets());
                offerRequestDTO.setPassenger(requestDTO.getPassenger());
                offerRequestDTO.setRouteNumbers(requestDTO.getRouteNumbers());
                offerRequestDTO.setTransportTypes(requestDTO.getTransportTypes());
                regionalOfferRequests.add(offerRequestDTO);
            });

            List<String> transportTypes = requestDTO.getTransportTypes();
            if (CollectionUtils.isEmpty(transportTypes)) {
                transportTypes = Arrays.asList(BUS.name(), TRAIN.name());
            }
            transportTypes.forEach(transportType -> {
                IntercityOfferRequestDTO intercityOfferRequest = new IntercityOfferRequestDTO();
                intercityOfferRequest.setCompanyName(requestDTO.getCompanyName());
                intercityOfferRequest.setDepartureTime(requestDTO.getDepartureTime());
                intercityOfferRequest.setDepCity(addressFrom.getCity());
                intercityOfferRequest.setDestCity(addressTo.getCity());
                intercityOfferRequest.setTransportType(transportType);
                intercityOfferRequest.setNumberOfTickets(requestDTO.getNumberOfTickets());
                intercityOfferRequest.setNumberOfLuggage(requestDTO.getNumberOfLuggage());
                intercityOfferRequest.setPassenger(requestDTO.getPassenger());
                intercityOfferRequest.setPlaceType(requestDTO.getPlaceType());
                intercityOfferRequests.add(intercityOfferRequest);
            });
        }

        return Pair.of(regionalOfferRequests, intercityOfferRequests);
    }


    private Address toDomainAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setCity(addressDTO.getCity());
        address.setHomeNumber(addressDTO.getHomeNumber());
        address.setStreetName(addressDTO.getStreetName());

        return address;
    }

    private AddressDTO toDTOAddress(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCity(address.getCity());
        addressDTO.setHomeNumber(address.getHomeNumber());
        addressDTO.setStreetName(address.getStreetName());

        return addressDTO;
    }
}
