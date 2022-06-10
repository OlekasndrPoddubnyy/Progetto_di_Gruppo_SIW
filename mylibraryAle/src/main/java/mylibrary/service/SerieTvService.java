package mylibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mylibrary.model.SerieTv;
import mylibrary.repository.SerieTvRepository;

@Service
public class SerieTvService {
	
	@Autowired
	private SerieTvRepository serieTvRepository;
	
	@Transactional
	public void saveSerieTv(SerieTv serieTv) {
		this.serieTvRepository.save(serieTv);
	}
	
	public void deleteById(long id) {
		this.serieTvRepository.deleteById(id);
	}
	
	public List<SerieTv> findAllByName(String nome){
		return this.serieTvRepository.findAllByNome(nome);
	}
	
	public SerieTv findById(long id) {
		return this.serieTvRepository.findById(id);
	}

}
