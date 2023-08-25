package by.htp.ex.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import by.htp.ex.util.name.StringName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = StringName.USER)
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = StringName.ID)
	private Integer id;
	
	@Column(name = StringName.NAME)
	private String name;
	
	@Column(name = StringName.SURNAME)
	private String surname;
	
	@Column(name = StringName.EMAIL)
	private String email;
	
	@Column(name = StringName.DATE)
	private Date date;
	
	@Column(name = StringName.STATUS)
	private Boolean status;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="user",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<News> news;

	

}
