package com.nisumlatam.userapp.service;

import java.util.List;

import com.nisumlatam.userapp.domain.model.Phone;
import com.nisumlatam.userapp.service.util.CrudService;

public interface PhoneService extends CrudService<Phone, String> {

	void saveAll(List<Phone> entities);

}
