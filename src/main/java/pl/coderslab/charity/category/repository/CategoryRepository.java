package pl.coderslab.charity.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);


}
