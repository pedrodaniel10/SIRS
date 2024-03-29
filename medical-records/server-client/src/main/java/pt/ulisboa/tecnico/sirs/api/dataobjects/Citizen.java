package pt.ulisboa.tecnico.sirs.api.dataobjects;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Citizen implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    public enum Role {
        PATIENT, DOCTOR, ADMIN, SUPERUSER
    }

    private String citizenId;
    private String citizenName;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String email;
    private byte[] password;
    private String profilePic;
    private String superuserCitizenId;
    private List<Role> roles = new ArrayList<>();

    public Citizen() {}

    public Citizen(boolean dataobject) {
        this.citizenId = "";
        this.citizenName = "";
        this.gender = Gender.MALE;
        this.dateOfBirth = LocalDate.now();
        this.email = "";
        this.password = "".getBytes();
        this.profilePic = "";
        this.superuserCitizenId = "";
        this.roles.add(Role.PATIENT);
    }

    public Citizen(String citizenId, String citizenName, Gender gender, LocalDate dateOfBirth, String email,
                   String password, String profilePic, String superuserCitizenId, List<Role> roles) {
        this.citizenId = citizenId;
        this.citizenName = citizenName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        try {
            this.encodePassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.profilePic = profilePic;
        this.superuserCitizenId = superuserCitizenId;
        this.roles = roles;
    }

    public Citizen(String password){
        try {
            this.encodePassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void encodePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        this.password = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getSuperuserCitizenId() {
        return superuserCitizenId;
    }

    public void setSuperuserCitizenId(String superuserCitizenId) {
        this.superuserCitizenId = superuserCitizenId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public boolean hasRole(String role) {
        return this.roles.contains(Role.valueOf(role));
    }

}