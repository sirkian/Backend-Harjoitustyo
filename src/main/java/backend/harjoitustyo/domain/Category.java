package backend.harjoitustyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false, updatable = false)
	private Long categoryId;
	
	@Size(min = 1, max = 30)
	@Column(name = "category_name")
	private String categoryName;
	
	
	@JsonIgnore 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category") private
	List<Image> images;
	 
	
	public Category() {
		
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	public List<Image> getImages() { 
		return images; 
	}
	  
	public void setImages(List<Image> images) { 
		this.images = images; 
	}
	 
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
}
