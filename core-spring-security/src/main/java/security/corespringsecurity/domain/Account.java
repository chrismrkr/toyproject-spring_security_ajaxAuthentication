package security.corespringsecurity.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    private Account(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.age = builder.age;
        this.role = builder.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private String email;
        private String age;
        private String role;

        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder age(String age) {
            this.age = age;
            return this;
        }
        public Builder role(String role) {
            this.role = role;
            return this;
        }
        public Account build() {
            return new Account(this);
        }
    }
}

/* Lombok의 @Data 애노테이션 기능
    Getter, Setter RequiredArgsConstructor, ToString, EqualsAndHashCode 애노테이션.
 */
