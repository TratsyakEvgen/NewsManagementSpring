package by.htp.ex.entity;

import java.io.Serializable;
import java.util.List;

import by.htp.ex.util.name.StringName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = StringName.LOCALE)
@Data
public class Locale implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = StringName.ID)
	private Integer id;
	
	@Column(name = StringName.LOCALE)
	private String locale;	
	
	@OneToMany(mappedBy = StringName.LOCALE)
	private List<Content> contents;

}
