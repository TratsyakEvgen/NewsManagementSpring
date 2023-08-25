package by.htp.ex.entity;

import java.io.Serializable;

import by.htp.ex.util.name.StringName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = StringName.CONTENT)
@Data
public class Content implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = StringName.ID)
	private Integer id;

	@Column(name = StringName.LINK)
	private String link;

	@Column(name = StringName.TITLE)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = StringName.NEWS_ID)
	private News news;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = StringName.LOCALE_ID)
	private Locale locale;

}
