package by.aliyeva.zenart.masterclass;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/masterclasses")
@RequiredArgsConstructor
public class MasterclassController {

    private final MasterclassService service;

    @PostMapping
    public ResponseEntity<Integer> createMasterclass(
            @RequestBody @Valid MasterclassRequest request
    ) {
        return  ResponseEntity.ok(service.createMasterclass(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<MasterclassPurchaseResponse>> purchaseMasterclasses(
            @RequestBody List<MasterclassPurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseMasterclasses(request));
    }

    @GetMapping("/{masterclass-id}")
    public ResponseEntity<MasterclassResponse> findById(
            @PathVariable("masterclass-id") Integer masterclassId
    ) {
        return ResponseEntity.ok(service.findById(masterclassId));
    }

    @GetMapping
    public ResponseEntity<List<MasterclassResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
