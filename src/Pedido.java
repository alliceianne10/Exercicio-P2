import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {
    private Long id;
    private LocalDate dataPedido;
    private List<Produto> produtos;
    private Cliente cliente;

    @Override
    public String toString() {
        String nomesProdutos = produtos.stream()
                .map(p -> p.getNome())
                .collect(Collectors.joining(", "));

        return String.format("Pedido #%d | Data: %s | %s | Itens: [%s]",
                id, dataPedido, cliente, nomesProdutos);
    }

    public Pedido(long id, LocalDate dataPedido, List<Produto> produtos, Cliente cliente) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.produtos = produtos;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
