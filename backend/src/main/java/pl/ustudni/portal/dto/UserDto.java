package pl.ustudni.portal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import pl.ustudni.portal.model.User.Gender;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserDto {
    @Size(min = 3, message = "{user.name}")
    @NotNull
    private String name;

    @Size(min = 8)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // TODO: use @ValidPassword annotation
    private String password;

    @NotEmpty
    @Email
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    @Past
    @NotNull
    private LocalDate dateOfBirth;

    private Gender gender;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @NotNull
    private Double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @NotNull
    private Double longitude;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}