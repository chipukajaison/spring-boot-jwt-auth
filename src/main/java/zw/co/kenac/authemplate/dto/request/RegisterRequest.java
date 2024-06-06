package zw.co.kenac.authemplate.dto.request;

/**
 * @author Jaison.Chipuka on 6/6/2024
 * @project Auth Template
 * @email chipukajaison@gmail.com
 */
public record RegisterRequest(
        String username,
        String password,
        String role
) {
}
