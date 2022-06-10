package mylibrary.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mylibrary.model.Episodio;

public interface EpisodioRepository extends CrudRepository<Episodio, Long> {

	public void deleteById(long id);

}
