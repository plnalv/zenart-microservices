package by.aliyeva.zenart.masterclass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterclassRepository extends JpaRepository<Masterclass, Integer> {
    List<Masterclass> findAllByIdInOrderById(List<Integer> masterclassIds);
}
