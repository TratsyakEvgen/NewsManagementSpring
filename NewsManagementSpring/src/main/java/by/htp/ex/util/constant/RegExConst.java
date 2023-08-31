package by.htp.ex.util.constant;

public final class RegExConst {
	private RegExConst() {
	}
	public static final String LOGIN = "^[\\p{L}0-9]([._-](?![._-])|[\\p{L}0-9]){3,43}[\\p{L}0-9]$";
	public static final String PASSWORD = "^(?=.*[0-9])(?=.*[\\p{L}])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,45}$";
	public static final String EMAIL = "^(?=.{1,45})[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][ \\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
	public static final String NAME = "^[\\p{L}'][ \\p{L}'-]*[\\p{L}]$";
	public static final String LINK = "(?=.*[\\/.])[\\p{L}0-9-\\/.:=_&?]{3,100}";

}