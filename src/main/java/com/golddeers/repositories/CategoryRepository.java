package com.golddeers.repositories;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import com.golddeers.model.Category;
public interface CategoryRepository extends CrudRepository<Category, String>{
	@Transactional
	void deleteByName(String name);
}