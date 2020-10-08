package Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meuBlogPessoal.model.TemaModel;

public interface TemaRepository extends JpaRepository<TemaModel, Long> {

	public List<TemaModel> findAllByDescricaoContainingIgnoreCase(String descricao);

}
