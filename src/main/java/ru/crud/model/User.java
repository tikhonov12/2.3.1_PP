package ru.crud.model;


import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private byte age;

    public User(String name, String lastName, byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User() {}

    public Long getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
