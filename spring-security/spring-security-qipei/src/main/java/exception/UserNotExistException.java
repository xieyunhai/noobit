package exception;

import lombok.Data;

/**
 * @author admin
 * @since 2017/9/22 11:59
 */
@Data
public class UserNotExistException extends RuntimeException {
	private static final long serialVersionUID = 6613568113915530487L;

	private Integer id;

	public UserNotExistException(Integer id) {
		super("user not exist...");
		this.id = id;
	}
}
