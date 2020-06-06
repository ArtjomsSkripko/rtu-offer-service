package offer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import offer.exception.OfferNotFoundException;
import offer.model.JourneyOfferRequest;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.IntercityOfferRequestDTO;
import offer.rest.model.RegionalOfferDTO;
import offer.rest.model.RegionalOfferRequestDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import offer.model.JourneyOffer;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final RegionalAndIntercityOfferRestClient restClient;

    @Autowired
    public OfferService(RegionalAndIntercityOfferRestClient restClient) {
        this.restClient = restClient;
    }


    public List<JourneyOffer> createOffers(Pair<List<RegionalOfferRequestDTO>, List<IntercityOfferRequestDTO>> requestData,
                                           JourneyOfferRequest journeyOfferRequest) {
        List<RegionalOfferRequestDTO> regionalOfferRequests = requestData.getLeft();
        List<IntercityOfferRequestDTO> intercityOfferRequests = requestData.getRight();

        boolean regionalOfferSearch = !regionalOfferRequests.isEmpty() && intercityOfferRequests.isEmpty();
        boolean interCityOfferSearch = regionalOfferRequests.isEmpty() && !intercityOfferRequests.isEmpty();
        boolean mixedSearch = !regionalOfferRequests.isEmpty() && !intercityOfferRequests.isEmpty();

        List<RegionalOfferDTO> regionalOffers = new ArrayList<>();
        List<IntercityOfferDTO> intercityOffers = new ArrayList<>();
        regionalOfferRequests.forEach(request -> regionalOffers.addAll(restClient.getRegionalOffers(request)));
        intercityOfferRequests.forEach(request -> intercityOffers.addAll(restClient.getIntercityOffers(request)));

        if ((regionalOfferSearch && regionalOffers.isEmpty())
                || (interCityOfferSearch && intercityOffers.isEmpty())
                || (mixedSearch && regionalOffers.isEmpty() && intercityOffers.isEmpty())) {
            throw new OfferNotFoundException("No offers found");
        }

        List<JourneyOffer> journeyOffers = new ArrayList<>();

        if (mixedSearch || interCityOfferSearch) {
            Map<String, List<RegionalOfferDTO>> regionalOffersByCity = regionalOffers.stream()
                    .collect(Collectors.groupingBy(RegionalOfferDTO::getCity));
            intercityOffers.forEach(o -> {
                JourneyOffer journeyOffer = new JourneyOffer();
                journeyOffer.setAddressFrom(journeyOfferRequest.getAddressFrom());
                journeyOffer.setAddressTo(journeyOfferRequest.getAddressTo());
                journeyOffer.setDepartureTime(journeyOfferRequest.getDepartureTime());
                journeyOffer.setIntercityOffer(o);
                journeyOffers.add(journeyOffer);
            });
            if (!regionalOffers.isEmpty()) {
                String depCity = journeyOfferRequest.getAddressFrom().getCity().toUpperCase();
                String destCity = journeyOfferRequest.getAddressTo().getCity().toUpperCase();
                AtomicInteger counter = new AtomicInteger(0);
                journeyOffers.forEach(journeyOffer -> {
                    Map<String, RegionalOfferDTO> regionalOfferMap =  new HashMap<>();
                    List<RegionalOfferDTO> regionalOfferDTOS = regionalOffersByCity.get(depCity);
                    if(counter.get() == regionalOfferDTOS.size())
                        counter.set(0);
                    regionalOfferMap.put(depCity, regionalOfferDTOS.get(counter.get()));
                    regionalOfferMap.put(destCity, regionalOffersByCity.get(destCity).get(counter.getAndIncrement()));
                    journeyOffer.setRegionalOffer(regionalOfferMap);
                });
            }
        } else {
            regionalOffers.forEach(o -> {
                JourneyOffer journeyOffer = new JourneyOffer();
                journeyOffer.setAddressFrom(journeyOfferRequest.getAddressFrom());
                journeyOffer.setAddressTo(journeyOfferRequest.getAddressTo());
                journeyOffer.setDepartureTime(journeyOfferRequest.getDepartureTime());
                journeyOffer.setRegionalOffer(ImmutableMap.of(o.getCity(), o));
                journeyOffers.add(journeyOffer);
            });
        }

        return journeyOffers;
    }
}
