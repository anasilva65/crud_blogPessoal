package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meuBlogPessoal.model.TemaModel;

import Repository.TemaRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
@Api(value="Temas")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
	@ApiOperation("Lista de todos os temas")
	public ResponseEntity<List<TemaModel>> getAll() {
		return ResponseEntity.ok(temaRepository.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation("Listar temas por ID")
	public ResponseEntity<TemaModel> getById(@PathVariable long id) {
		return temaRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	@ApiOperation("Listar temas por nome")
	public ResponseEntity<List<TemaModel>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(nome));
	}

	@PostMapping
	@ApiOperation("Inserir tema")
	public ResponseEntity<TemaModel> post(@RequestBody TemaModel tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}

	@PutMapping("/{id}")
	@ApiOperation("Alterar tema")
	public ResponseEntity<TemaModel> put(@RequestBody TemaModel tema) {
		return ResponseEntity.ok(temaRepository.save(tema));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Deletar tema")
	public void delete(@PathVariable long id) {
		temaRepository.deleteById(id);
	}


}
