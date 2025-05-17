package by.aliyeva.zenart.masterclass;

import by.aliyeva.zenart.exception.MasterclassPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterclassService {
    private final MasterclassRepository repository;
    private final MasterclassMapper mapper;

    public Integer createMasterclass(MasterclassRequest request) {
        var masterclass = mapper.toMasterclass(request);
        return repository.save(masterclass).getId();
    }

    public List<MasterclassPurchaseResponse> purchaseMasterclasses(List<MasterclassPurchaseRequest> request) {
        var masterclassIds = request
                .stream()
                .map(MasterclassPurchaseRequest::masterclassId)
                .toList();
        var storedMasterclasses = repository.findAllByIdInOrderById(masterclassIds);
        if (masterclassIds.size() != storedMasterclasses.size()) {
            throw new MasterclassPurchaseException("One or more masterclasses are unavailable");
        }

        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(MasterclassPurchaseRequest::masterclassId))
                .toList();
        var purchasedMasterclasses = new ArrayList<MasterclassPurchaseResponse>();
        for (int i = 0; i < storedMasterclasses.size(); i++) {
            var masterclass = storedMasterclasses.get(i);
            var masterclassRequest = storedRequest.get(i);
            if (masterclass.getSeatsQuantity() < masterclassRequest.quantity()){
                throw new MasterclassPurchaseException("Insufficient seats for masterclass with ID " + masterclassRequest.masterclassId());
            }
            var newSeatsQuantity = masterclass.getSeatsQuantity() - masterclassRequest.quantity();
            masterclass.setSeatsQuantity(newSeatsQuantity);
            repository.save(masterclass);
            purchasedMasterclasses.add(mapper.toMasterclassPurchaseResponse(masterclass, masterclassRequest.quantity()));
        }
        return purchasedMasterclasses;
    }

    public MasterclassResponse findById(Integer masterclassId) {
        return repository.findById(masterclassId)
                .map(mapper::toMasterclassResponse)
                .orElseThrow(() -> new EntityNotFoundException("Masterclass is not found with the id " + masterclassId));
    }

    public List<MasterclassResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toMasterclassResponse)
                .collect(Collectors.toList());
    }
}
