package app.shears.mvp.services;

import app.shears.mvp.models.Master;
import app.shears.mvp.repositories.MasterRepository;
import app.shears.mvp.services.api.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
