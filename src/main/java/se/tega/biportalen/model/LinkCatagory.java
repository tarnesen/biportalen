package se.tega.biportalen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "link_catagory")
public class LinkCatagory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Column(name = "lang", nullable = true, length = 10)
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
