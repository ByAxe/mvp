package app.shears.mvp.services;

import app.shears.mvp.models.Master;
import app.shears.mvp.repositories.MasterRepository;
import app.shears.mvp.services.api.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MasterService implements IMasterService {

    private final MasterRepository masterRepository;

    @Autowired
    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();
    }

    @Override
    public Master findOne(Long id) throws Exception {
        Optional<Master> masterOptional = masterRepository.findById(id);

        return masterOptional.orElse(null);
    }

    @Override
    @Transactional
    public void save(Master master) {
        masterRepository.save(master);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        masterRepository.deleteById(id);
    }

    @Override
    public long count() {
        return masterRepository.count();
    }

    @Override
    public boolean existsByLogin(String login) {
        return masterRepository.existsByLogin(login);
    }

    @Override
    public boolean existsByLoginAndPassword(String login, String password) {
        return masterRepository.existsByLoginAndPassword(login, password);
    }

    @Override
    public Master findByLoginAndPassword(String login, String password) {
        return masterRepository.findByLoginAndPassword(login, password);
    }
}
