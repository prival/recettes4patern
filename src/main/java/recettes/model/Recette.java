package recettes.model;

import javax.persistence.*;
import org.hibernate.annotations.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recette {

    private static final long serialVersionUID = -3009116532242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "ingredients")
    @Type(type="text")
    private String ingredients;

    @Column(name = "etapes")
    @Type(type="text")
    private String etapes;

    @Column(name = "ordre")
    private int ordre;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    public Recette() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getEtapes() { return etapes; }

    public void setEtapes(String etapes) {
        this.etapes = etapes;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return String.format("Categorie[id=%d, libelle='%s', ordre='%d']", id, libelle, ordre);
    }
}
