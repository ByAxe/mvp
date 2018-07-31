package app.shears.mvp.services.api;

import app.shears.mvp.models.Master;

import java.util.List;

public interface IMasterService {

    List<Master> findAll();
}
