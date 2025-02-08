import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String surname;
    private int age;
    private String username;
    private String password;
    private LocalDateTime inserted_data_at_utc;
    private LocalDateTime updated_data_at_utc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getInserted_data_at_utc() {
        return inserted_data_at_utc;
    }

    public void setInserted_data_at_utc(LocalDateTime inserted_data_at_utc) {
        this.inserted_data_at_utc = inserted_data_at_utc;
    }

    public LocalDateTime getUpdated_data_at_utc() {
        return updated_data_at_utc;
    }

    public void setUpdated_data_at_utc(LocalDateTime updated_data_at_utc) {
        this.updated_data_at_utc = updated_data_at_utc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(inserted_data_at_utc, user.inserted_data_at_utc) && Objects.equals(updated_data_at_utc, user.updated_data_at_utc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, username, password, inserted_data_at_utc, updated_data_at_utc);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", inserted_data_at_utc=" + inserted_data_at_utc +
                ", updated_data_at_utc=" + updated_data_at_utc +
                '}';
    }

    private int umnozitNaDva(int a) {
        return a * 2;
    }
}
