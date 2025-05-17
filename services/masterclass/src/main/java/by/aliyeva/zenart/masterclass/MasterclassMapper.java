package by.aliyeva.zenart.masterclass;

import by.aliyeva.zenart.category.Category;
import org.springframework.stereotype.Service;

@Service
public class MasterclassMapper {
    public Masterclass toMasterclass(MasterclassRequest request) {
        return Masterclass.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .date(request.date())
                .time(request.time())
                .seatsQuantity(request.seatsQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    public MasterclassResponse toMasterclassResponse(Masterclass masterclass) {
        return new MasterclassResponse(
                masterclass.getId(),
                masterclass.getName(),
                masterclass.getDescription(),
                masterclass.getDate(),
                masterclass.getTime(),
                masterclass.getSeatsQuantity(),
                masterclass.getPrice(),
                masterclass.getCategory().getId(),
                masterclass.getCategory().getName(),
                masterclass.getCategory().getDescription()
        );
    }

    public MasterclassPurchaseResponse toMasterclassPurchaseResponse(Masterclass masterclass, Integer quantity) {
        return new MasterclassPurchaseResponse(
                masterclass.getId(),
                masterclass.getName(),
                masterclass.getDescription(),
                masterclass.getDate(),
                masterclass.getTime(),
                masterclass.getPrice(),
                quantity
        );
    }
}
