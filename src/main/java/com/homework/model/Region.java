package com.homework.model;

import com.homework.convert.ListConverter;
import com.homework.convert.MapConverter;
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
@Entity
@Table(name = "product_region")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Region {
	public Region() { }

	@Builder
	public Region(EliObject object) {
		this.id = object.getInt("region_id");
		this.code = object.getInt("product_code");
		this.imageUrl = object.getString("image_url", "");
		this.gender = object.getString("gender", "");
		this.attributes = new EliObject("data", object.getList("attributes") != null ? object.getList("attributes") : new ArrayList());
		this.category = object.getList("category_names") != null ? (List<String>)object.getList("category_names") : new ArrayList<>();
	}
	
	@Id
	@Column(name = "product_code")
	private int code;
	
	@Column(name = "region_id")
	private int id;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "product_gender", length = 50)
	private String gender;
	
	@Convert(converter = MapConverter.class)
	@Column(name = "product_attribute", columnDefinition = "MEDIUMTEXT")
	private EliObject attributes;
	
	@Convert(converter = ListConverter.class)
	@Column(name = "category_name", columnDefinition = "MEDIUMTEXT")
	private List<String> category;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Region region = (Region) o;
		return Objects.equals(code, region.code);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
