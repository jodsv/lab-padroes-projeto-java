// 1. Padrão Singleton
class Singleton {
    private static class InstanceHolder {
        public static Singleton instancia = new Singleton();
    }
    private Singleton() {}
    public static Singleton getInstancia() {
        return InstanceHolder.instancia;
    }
}

// 2. Padrão Strategy
interface Comportamento {
    void mover();
}

class ComportamentoNormal implements Comportamento {
    public void mover() {
        System.out.println("Movendo-se normalmente...");
    }
}

class ComportamentoAgressivo implements Comportamento {
    public void mover() {
        System.out.println("Movendo-se agressivamente!");
    }
}

class Robo {
    private Comportamento comportamento;
    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }
    public void mover() {
        comportamento.mover();
    }
}

// 3. Padrão Facade
class CrmService {
    public static void gravarCliente(String nome, String cep, String estado, String cidade) {
        System.out.println("Cliente salvo no CRM: " + nome + ", " + cidade + "/" + estado);
    }
}

class CepApi {
    private static CepApi instancia = new CepApi();
    private CepApi() {}
    public static CepApi getInstancia() {
        return instancia;
    }
    public String recuperarCidade(String cep) { return "São Paulo"; }
    public String recuperarEstado(String cep) { return "SP"; }
}

class Facade {
    public void migrarCliente(String nome, String cep) {
        String cidade = CepApi.getInstancia().recuperarCidade(cep);
        String estado = CepApi.getInstancia().recuperarEstado(cep);
        CrmService.gravarCliente(nome, cep, estado, cidade);
    }
}

// Classe Principal
public class Main {
    public static void main(String[] args) {
        // Teste Singleton
        Singleton s1 = Singleton.getInstancia();
        Singleton s2 = Singleton.getInstancia();
        System.out.println("Mesma instância Singleton? " + (s1 == s2));

        // Teste Strategy
        Robo robo = new Robo();
        robo.setComportamento(new ComportamentoNormal());
        robo.mover();
        robo.setComportamento(new ComportamentoAgressivo());
        robo.mover();

        // Teste Facade
        Facade facade = new Facade();
        facade.migrarCliente("Dev", "01001000");
    }
}
