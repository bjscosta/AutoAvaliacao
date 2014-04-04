package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class User implements Serializable {

    @Size(min = 1, message = "Tem de inserir um nome")
    @NotNull
    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
            + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "E-mail inválido")
    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String email;

    
    @NotNull
    @Size(min = 4, message = "Password demasiado pequena")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
