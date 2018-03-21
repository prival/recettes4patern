package recettes.model;

import javax.persistence.*;

@Entity
public class Etape {

    private static final long serialVersionUID = -3009157732452241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "ordre")
    private int ordre;

    protected Etape() {
    }

    public Etape(String libelle, int ordre) {
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

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return String.format("Etape[id=%d, libelle='%s', ordre='%d']", id, libelle, ordre);
    }
}
