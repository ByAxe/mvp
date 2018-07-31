package app.shears.mvp.repositories;

import app.shears.mvp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByLoginAndPassword(String login, String password);
}
