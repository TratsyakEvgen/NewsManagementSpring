package by.htp.ex.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import by.htp.ex.util.name.StringName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = StringName.NEWS)
@Data
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = StringName.ID)
	private Integer id;

	@Column(name = StringName.DATE_TIME)
	private Timestamp dateTime;

	@Column(name = StringName.STATUS)
	private Boolean status;

	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = StringName.USER_ID)
	private User user;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = StringName.IMAGE_NEWS, 
	joinColumns = @JoinColumn(name = StringName.NEWS_ID), 
	inverseJoinColumns = @JoinColumn(name = StringName.IMAGE_ID))
	private List<Image> images;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = StringName.NEWS)
	private List<Content> contents;


	
	

}
