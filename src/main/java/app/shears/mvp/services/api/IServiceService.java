package app.shears.mvp.services.api;

import app.shears.mvp.models.Service;

import java.util.List;

public interface IServiceService {

    List<Service> findAll();

}
