package app.shears.mvp.repositories;

import app.shears.mvp.models.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
    boolean existsByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

    Master findByLoginAndPassword(String login, String password);
}
