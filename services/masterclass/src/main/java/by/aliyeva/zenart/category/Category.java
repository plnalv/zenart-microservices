package by.aliyeva.zenart.category;

import by.aliyeva.zenart.masterclass.Masterclass;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE) //REMOVE чтобы при удалении категории удалялись все связанные продукты
    private List<Masterclass> masterclasses;
}
