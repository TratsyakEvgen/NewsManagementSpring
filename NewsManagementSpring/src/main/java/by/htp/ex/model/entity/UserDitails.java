package by.htp.ex.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_ditails")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDitails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private Integer id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;

	@Column(name = "date")
	private Date date;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userDitails", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<News> news;

	@Override
	public String toString() {
		return "UserDitails [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", date="
				+ date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDitails other = (UserDitails) obj;
		return Objects.equals(id, other.id);
	}

}
