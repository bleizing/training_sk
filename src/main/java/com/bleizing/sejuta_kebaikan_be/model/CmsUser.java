package com.bleizing.sejuta_kebaikan_be.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cms_users")
public class CmsUser extends BaseModel {
	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
