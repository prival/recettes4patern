package recettes.model;

import javax.persistence.*;
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

    @Column(name = "ordre")
    private int ordre;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @OneToMany(mappedBy = "recette", orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recette", orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<Etape> etapes;

    public Recette() {
        ingredients = new ArrayList<Ingredient>();
        Ingredient initIngredient = new Ingredient("", 1);
        ingredients.add(initIngredient);

        etapes = new ArrayList<Etape>();
        Etape initEtape = new Etape("", 1);
        etapes.add(initEtape);
    }

    /**
     * Ajoute un ingrédient
     * @param ingredient
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecette(this);
    }

    /**
     * Ajoute une étape
     * @param etape
     */
    public void addEtape(Etape etape) {
        etapes.add(etape);
        etape.setRecette(this);
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<Etape> etapes) {
        this.etapes = etapes;
    }

    @Override
    public String toString() {
        return String.format("Categorie[id=%d, libelle='%s', ordre='%d']", id, libelle, ordre);
    }
}
