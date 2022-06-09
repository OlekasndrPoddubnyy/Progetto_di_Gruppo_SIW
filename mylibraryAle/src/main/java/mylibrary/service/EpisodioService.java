package mylibrary.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import mylibrary.model.Episodio;
import mylibrary.repository.EpisodioRepository;

@Service
public class EpisodioService {
	
	@Autowired
	private EpisodioRepository episodioRepository;
	
	@Transactional
	public void saveEpisodio(Episodio episodio) {
		this.episodioRepository.save(episodio);
	}
	
	public void deleteById(Long id) {
		this.episodioRepository.deleteById(id);
	}
	
	public Episodio findById(Long id) {
		return this.episodioRepository.findById(id).get();
	}

}
