package ninja.javahacker;

import java.util.Arrays;
import java.util.List;

@Moderadores({
    @Moderador(name = "Victor")
})
public interface ModeradorStrategy implements RegraDoGrupo {

    public static final List<String> PROIBICOES = List.of(
        "spam", "fake news", "política", "futebol", "religião", "novela", "BBB - big bosta brasil",
        "drogas", "sexo", "sexualidade", "pornografia", "funk", "armas", "bullying", "preconceito",
        "ofensivo", "discurso de ódio", "semeando discórdia", "PHP é melhor que Java"
    );

    public default void analisar(Mensagem m) {
        if (!isCrap(m) && isOnTopic(m) && !isNoob(m)) return;
        Pessoa mane = m.getAutor();
        if (sacoCheio(mane)) {
            expulsar(mane);
        } else {
            advertir(mane);
        }
    }

    public default boolean isCrap(Mensagem m) {
        return PROIBICOES.stream().anyMatch(m.getContent()::relatedTo);
    }

    public default boolean isOnTopic(Mensagem m) {
        return m.getTechnologies().contains("Java")
                || m.getJobOffer().relatedTo("Java")
                || m.getContent().relatedTo("Java");
    }

    public default boolean isNoob(Mensagem m) {
        return m.contains("qual é a diferença entre int e float?")
                || m.contains("porque meu hello world deu NullPointerException?")
                || m.contains("o que é uma variável?")
                || m.contains("prefiro fazer tudo no main e usar switch para simular goto")
                || m.contains("como fasso para raquiar feicebuqui?")
                || m.contains("não consigo entender a interface ModeradorStrategy, alguém pode ajudar?");
    }

    public boolean sacoCheio(Pessoa chato);

    public void expulsar(Pessoa babaca);

    public void advertir(Pessoa vacilao);
}