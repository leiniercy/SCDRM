package trabajodediploma.data.service;

import trabajodediploma.data.repository.GrupoRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trabajodediploma.data.entity.Grupo;

@Service
public class GrupoService {

    private final GrupoRepository repository;

    public GrupoService(@Autowired GrupoRepository repository) {
        this.repository = repository;
    }

    public Optional<Grupo> get(UUID id) {
        return repository.findById(id);
    }

    public Grupo update(Grupo entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<Grupo> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
