package recettes.model;

import javax.persistence.*;

@Entity
public class Ingredient {

    private static final long serialVersionUID = -3009154432242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "ordre")
    private int ordre;

    protected Ingredient() {
    }

    public Ingredient(String libelle, int ordre) {
        this.libelle = libelle;
        this.ordre = ordre;
    }

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

//    public Recette getRecette() {
//        return recette;
//    }

//    public void setRecette(Recette recette) {
//        this.recette = recette;
//    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return String.format("Ingredient[id=%d, libelle='%s', ordre='%d']", id, libelle, ordre);
    }
}
