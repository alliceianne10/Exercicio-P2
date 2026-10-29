
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LojaTeste {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente(101L, "Maria");
        Cliente cliente2 = new Cliente(102L, "João");
        Cliente cliente3 = new Cliente(103L, "Carlos");

        Produto produto1 = new Produto(1L, "Caneta","Papelaria",2);
        Produto produto2 = new Produto(2L, "GTA 6","Jogo",500);
        Produto produto3 = new Produto(3L, "O Hobbit","Livro",50);;
        Produto produto4 = new Produto(4L, "Estojo","Papelaria",25.5);
        Produto produto5 = new Produto(5L, "Loading","Jogo",49.99);

        Pedido pedido1 = new Pedido(1234L, LocalDate.of(2026, 10, 28), List.of(produto1), cliente1);
        Pedido pedido2 = new Pedido(1235L, LocalDate.of(2026, 9, 10), List.of(produto2, produto5), cliente2);
        Pedido pedido3 = new Pedido(1236L, LocalDate.of(2026, 10, 20), List.of(produto3, produto4), cliente3);
        Pedido pedido4 = new Pedido(1237L, LocalDate.of(2025, 1, 1), List.of(produto1, produto3), cliente1);
        Pedido pedido5 = new Pedido(12348L, LocalDate.of(2026, 2, 12), List.of(produto5), cliente2);

        List<Produto> todosProdutos = new ArrayList<>();
        todosProdutos.add(produto1);
        todosProdutos.add(produto2);
        todosProdutos.add(produto3);
        todosProdutos.add(produto4);
        todosProdutos.add(produto5);

        List<Pedido> todosPedidos = new ArrayList<>();
        todosPedidos.add(pedido1);
        todosPedidos.add(pedido2);
        todosPedidos.add(pedido3);
        todosPedidos.add(pedido4);
        todosPedidos.add(pedido5);

        System.out.println("---- Jogos por menos de R$100 ---");
        todosProdutos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Jogo") && p.getPreco() < 100)
                .forEach(p -> System.out.println(p));
        System.out.println();

        System.out.println("--- Lista de pedidos com produtos da categoria 'Papelaria' ---");

        List<Pedido> pedidosPapelaria = todosPedidos.stream()
                .filter(p -> p.getProdutos().stream()
                        .anyMatch(produto -> produto.getCategoria().equalsIgnoreCase("Papelaria"))).collect(Collectors.toList());

        pedidosPapelaria.stream().forEach(p -> System.out.println(p));
        System.out.println();

        System.out.println("--- Produtos agrupados por categorias ---");
        System.out.println("Jogos: ");
        todosProdutos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Jogo"))
                .forEach(p -> System.out.println(p));

        System.out.println("Papelaria: ");
        todosProdutos.stream().filter(p-> p.getCategoria().equalsIgnoreCase("Papelaria"))
                .forEach(p -> System.out.println(p));

        System.out.println("Livro: ");
        todosProdutos.stream().filter(p-> p.getCategoria().equalsIgnoreCase("Livro"))
                .forEach(p -> System.out.println(p));
        System.out.println();

        System.out.println("Lista de pedidos com o pedido mais recente de cada cliente: ");
        List<Pedido> pedidosMaisRecentes = todosPedidos.stream()
                .collect(Collectors.toMap(p -> p.getCliente(), pedido -> pedido,
                        (p1, p2) -> {
                            if (p1.getDataPedido().isAfter(p2.getDataPedido())) {
                                return p1;
                            } else {
                                return p2;
                            }
                        }
                )).values().stream().toList();

        pedidosMaisRecentes.stream().forEach(p -> System.out.println(p));

    }
}
