package com.homework.model;

import com.homework.convert.ListConverter;
import com.homework.object.EliObject;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "product_list")
@SuppressWarnings("unchecked")
public class Product {

	@Builder
	public Product(EliObject object) {
		this.code = object.getInt("product_code");
		this.name = object.getString("name", "");
		this.imageUrl = object.getString("image_url", "");
		this.price = object.getInt("price");
		this.category = object.getList("category_names") != null ? (List<String>)object.getList("category_names") : new ArrayList<>();
	}
	
	@Id
	@Column(name = "product_code")
	private int code;
	
	@Column(name = "product_name", length = 100)
	private String name;
	
	@Column(name = "product_price")
	private int price;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Convert(converter = ListConverter.class)
	@Column(name = "category_name", columnDefinition = "MEDIUMTEXT")
	private List<String> category;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Product product = (Product) o;
		return Objects.equals(code, product.code);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
