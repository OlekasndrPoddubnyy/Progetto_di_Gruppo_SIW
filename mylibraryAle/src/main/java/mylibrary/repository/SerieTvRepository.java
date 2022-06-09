package mylibrary.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mylibrary.model.SerieTv;

public interface SerieTvRepository extends CrudRepository<SerieTv, Long> {
	
	public boolean existsById(long id);
	public void deleteById(long id);
	public List<SerieTv> findAllByNome(String nome);
	public SerieTv findById(long id);

}