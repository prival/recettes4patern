package recettes.form;

import recettes.model.Categorie;
import recettes.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class RecetteForm {

    private long categorieId;
    private String libelle;
    private List<Ingredient> ingredients;
    private List<String> etapes;

    public RecetteForm() {
        ingredients = new ArrayList<Ingredient>();
        Ingredient init = new Ingredient("", 1);
        ingredients.add(init);
    }

    public long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(long categorieId) {
        this.categorieId = categorieId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<String> etapes) {
        this.etapes = etapes;
    }
}
