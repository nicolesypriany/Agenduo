package agenduo.controller;

import agenduo.dto.request.CategoryRequest;
import agenduo.dto.response.CategoryResponse;
import agenduo.model.Category;
import agenduo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody CategoryRequest request, UriComponentsBuilder uriBuilder) {
        var category = new Category(request);
        repository.save(category);

        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryResponse(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var category = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var categories = repository.findAll();

        List<CategoryResponse> categoryResponseList = categories.stream()
                .map(CategoryResponse::new)
                .toList();

        return ResponseEntity.ok(categoryResponseList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id, @RequestBody CategoryRequest request) {
        var category = repository.getReferenceById(id);
        category.update(request);

        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var category = repository.getReferenceById(id);
        repository.delete(category);
        return ResponseEntity.noContent().build();
    }
}
