package event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import event.models.PaymentModel;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {

}
