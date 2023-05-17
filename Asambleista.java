import javax.persistence.*;
import javax.print.DocFlavor;

@Entity
public class Asambleista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1, nullable = false,
            columnDefinition = "CHECK (voto in ('S', 'N', 'A))")
    private char voto;

    @Column(length = 1, nullable = false,
            columnDefinition = "CHECK (region in ('N', 'P', 'E'))")
    private char region;

    public Asambleista(){
    }

    public Asambleista(char voto, char region){
        this.voto = voto;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public char getVoto() {
        return voto;
    }

    public char getRegion() {
        return region;
    }
}
