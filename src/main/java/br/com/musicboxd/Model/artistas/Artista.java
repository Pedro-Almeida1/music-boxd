package br.com.musicboxd.model.artistas;

import static jakarta.persistence.InheritanceType.JOINED;

import java.util.ArrayList;
import java.util.List;

import br.com.musicboxd.model.Musica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = JOINED)
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected String generoMusical;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Musica> musicas = new ArrayList<Musica>();

    public Artista() {
    }

    public Artista(String nome, String generoMusical) {
        this.nome = nome;
        this.generoMusical = generoMusical;
    }

    public void verMusicas() {
        System.out.println("Músicas de " + nome + ":");
        if (musicas.isEmpty()) {
            System.out.println("(Nenhuma música cadastrada)");
        } else {
            for (Musica m : musicas) {
                System.out.println(" - " + m.toString());
            }
        }
    }

    public void removerMusicas(Musica musica) {
        if (musicas.contains(musica)) {
            musicas.remove(musica);
            System.out.println("Musica removida com sucesso");
        } else {
            System.out.println("A musica não foi encontrada na lista");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}
