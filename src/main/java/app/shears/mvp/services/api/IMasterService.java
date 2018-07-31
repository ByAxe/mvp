package app.shears.mvp.services.api;

import app.shears.mvp.models.Master;

import java.util.List;

public interface IMasterService {

    List<Master> findAll();

    Master findOne(Long id) throws Exception;

    void save(Master master);

    void delete(Long id);

    long count();

    boolean existsByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);

    Master findByLoginAndPassword(String login, String password);
}
