package by.htp.ex.entity;

import java.io.Serializable;
import java.util.List;

import by.htp.ex.util.name.StringName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = StringName.IMAGE)
@Data
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = StringName.ID)
	private Integer id;
	
	@Column(name = StringName.LINK)
	private String link;
	
	@Column(name = StringName.STATUS)
	private Boolean status;
	
	@ManyToMany(mappedBy = StringName.IMAGES)
	private List<News> news;


}
