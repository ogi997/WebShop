package net.etfbl.ip.beans;

import java.io.Serializable;
import java.util.List;

import net.etfbl.ip.dao.CategoryDAO;
import net.etfbl.ip.dto.Category;

public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CategoryBean() {}
	
	public List<Category> getAllCategories() {
		return CategoryDAO.selectAll();
	}
	public int addNewCategory(Category category) {
		return CategoryDAO.insert(category);
	}
	public Category getCategoryId(Integer id) {
		return CategoryDAO.getCategoryById(id);
	}
	public int updateCategory(Category category) {
		return CategoryDAO.update(category);
	}
	public int deleteCategory(Category category) {
		return CategoryDAO.delete(category);
	}

}
