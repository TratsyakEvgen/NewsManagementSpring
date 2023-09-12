package by.htp.ex.model.bean;

import java.io.Serializable;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.annatation.NotNull;
import lombok.Data;

@Data
public class ContentData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull(message = ErrorCode.DATA_VALIDATION)
	private Integer idNews;
	@NotNull(message = ErrorCode.DATA_VALIDATION)
	private String locale;
	@NotNull(message = ErrorCode.DATA_VALIDATION)
	private String title;
	@NotNull(message = ErrorCode.DATA_VALIDATION)
	private String link;

}
