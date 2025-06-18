package agenduo.dto.response;

import agenduo.model.Category;

public record CategoryResponse(Long id,
                               String name) {

    public CategoryResponse(Category category) {
        this(
                category.getId(),
                category.getName()
        );
    }
}
