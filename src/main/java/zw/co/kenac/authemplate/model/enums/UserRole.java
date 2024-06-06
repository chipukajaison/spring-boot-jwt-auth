package zw.co.kenac.authemplate.model.enums;

/**
 * @author Jaison.Chipuka on 6/6/2024
 * @project Auth Template
 * @email chipukajaison@gmail.com
 */
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
