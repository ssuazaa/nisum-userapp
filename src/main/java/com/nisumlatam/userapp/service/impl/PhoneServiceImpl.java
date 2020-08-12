//package com.nisumlatam.userapp.service.impl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nisumlatam.userapp.dao.PhoneDao;
//import com.nisumlatam.userapp.domain.model.Phone;
//import com.nisumlatam.userapp.service.PhoneService;
//import com.nisumlatam.userapp.web.exceptions.ObjectNotFoundException;
//
//@Service
//@Transactional
//public class PhoneServiceImpl implements PhoneService {
//
//	private final PhoneDao phoneDao;
//
//	public PhoneServiceImpl(PhoneDao phoneDao) {
//		this.phoneDao = phoneDao;
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Optional<Phone> findById(String id) {
//		return Optional.of(phoneDao.findById(id).orElseThrow(() -> new ObjectNotFoundException()));
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public List<Phone> findAll() {
//		return phoneDao.findAll();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public long count() {
//		return phoneDao.count();
//	}
//
//	@Override
//	public Phone save(Phone entity) {
//		entity = entity.toBuilder()
//			.withCreated(LocalDateTime.now())
//			.withLastLogin(LocalDateTime.now())
//			.withIsactive(1)
//			.build();
//		
//		return phoneDao.save(entity);
//	}
//	
//	@Override
//	public void saveAll(List<Phone> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void update(String id, Phone entity) {
//		Phone entityDB = phoneDao.findById(id).orElseThrow(() -> new ObjectNotFoundException());
//		entityDB = entityDB.toBuilder()
//			.withId(id)
//			.withName(entity.getName())
//			.withEmail(entity.getEmail())
//			.withPassword(entity.getPassword())
//			.withModified(LocalDateTime.now())
//			.withIsactive(entity.getIsactive())
//			.build();
//		
//		phoneDao.save(entityDB);
//	}
//
//	@Override
//	public void deleteById(String id) {
//		Phone entity = phoneDao.findById(id).orElseThrow(() -> new ObjectNotFoundException());
//		phoneDao.delete(entity);
//	}
//
//	
//
//}
