package pesquisasequencial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    public static String ler() {
        String e = "";
        BufferedReader dado = new BufferedReader(new InputStreamReader(System.in));
        try {
            e = dado.readLine();
            return e;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("***** Cadastro de Clientes *****");
        System.out.println("Escolha o sistema de armazenamento:");
        System.out.println("[1] Usar Sistema de Tabelas");
        System.out.println("[2] Usar Árvore Binária");
        System.out.println("\r");
        int c = Integer.parseInt(ler());
        if (c == 1) { // para sistema de tabela
            int maxN = 10;
            Tabela T = new Tabela(maxN);
            Cliente reg = null;
            int i = 1000, x;
            do {
                System.out.println("\n[1] Cadastrar\n[2] Pesquisar\n[3] Sair\n");
                x = Integer.parseInt(ler());

                switch (x) {
                    case 1:
                        reg = new Cliente(i); //criação de um objeto Meucliente
                        System.out.print("\nInsira o seu nome: ");
                        String nomeTemp = ler();
                        reg.setNome(nomeTemp); //armazena o nome no cliente
                        System.out.print("\nInsira seu saldo: ");
                        double saldoTemp = Double.parseDouble(ler()); //saldo inicial
                        reg.setSaldo(saldoTemp); //insere o novo cliente na tabela
                        try {
                            long tempoInicial = System.nanoTime();
                            T.insere(reg);
                            long tempoFinal = System.nanoTime();
                            double diferencaTempo = (tempoFinal - tempoInicial);
                            System.out.println("\nConta Criada com sucesso, numero da conta:" + i);
                            System.out.println("(Tempo: " + diferencaTempo + " nanossegundos)");
                            i++;
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 2:
                        int w;
                        int p;
                        System.out.println("Escolha a forma: [1] Sequencial\n[2] Binária");
                        w = Integer.parseInt(ler());
                        System.out.print("\nInsira o numero de conta: ");
                        int num = Integer.parseInt(ler());
                        reg = new Cliente(num);
                        if (w == 1) {
                            long tempoInicial = System.nanoTime();
                            p = T.pesquisa(reg);
                            long tempoFinal = System.nanoTime();
                            double diferencaTempo = (tempoFinal - tempoInicial);
                            System.out.println("(Tempo: " + diferencaTempo + " nanossegundos)");
                        } else if (w == 2) {
                            long tempoInicial = System.nanoTime();
                            p = T.binaria(reg);
                            long tempoFinal = System.nanoTime();
                            double diferencaTempo = (tempoFinal - tempoInicial);
                            System.out.println("(Tempo: " + diferencaTempo + " nanossegundos)");
                        } else {
                            System.out.println("ERRO: Escolha um tipo de pesquisa");
                            p = 0;
                        }
                        if (p == 0) {
                            System.out.println("Conta não encontrada!\n");
                        } else {
                            reg = (Cliente) T.getRegistro(p);
                            System.out.println("\n**************************************");
                            System.out.println("Conta Encontrada!");
                            System.out.println("Conta Nº: " + reg.getNumeroConta());
                            System.out.println("Dono: " + reg.getNome());
                            System.out.println("Saldo: " + reg.getSaldo() + " Reais\n");
                            System.out.println("**************************************");
                        }
                        break;
                    case 3:
                        System.out.println("\nSaindo do Programa!");
                        break;
                    default:
                        System.out.println("Digite uma opção existente\n");
                }

            } while (x != 3);
        } else if (c == 2) { // para sistema com arvore
            ArvoreSBB arvore = new ArvoreSBB();
            int i = 1000, x;
            Cliente reg = null;
            do {
                System.out.println("\n[1] Cadastrar\n[2] Pesquisar\n[3] Excluir\n [4] Sair\n");
                x = Integer.parseInt(ler());

                switch (x) {
                    case 1:
                        reg = new Cliente(i); //criação de um objeto cliente
                        System.out.print("\nInsira o seu nome: ");
                        String nomeTemp = ler();
                        reg.setNome(nomeTemp); //armazena o nome no cliente
                        System.out.print("\nInsira seu saldo: ");
                        double saldoTemp = Double.parseDouble(ler()); //saldo inicial
                        reg.setSaldo(saldoTemp); //insere o novo cliente na tabela
                        try {
                            long tempoInicial = System.nanoTime();
                            arvore.insere(reg);
                            long tempoFinal = System.nanoTime();
                            double diferencaTempo = (tempoFinal - tempoInicial);
                            System.out.println("\nConta Criada com sucesso, numero da conta:" + i);
                            System.out.println("(Tempo: " + diferencaTempo + " nanossegundos)");
                            i++;
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 2:
                        Cliente p;
                        System.out.print("\nInsira o numero de conta: ");
                        int num = Integer.parseInt(ler());
                        reg = new Cliente(num);
                        long tempoInicial = System.nanoTime();
                        p = (Cliente) arvore.pesquisa(reg);
                        long tempoFinal = System.nanoTime();
                        double diferencaTempo = (tempoFinal - tempoInicial);
                        System.out.println("(Tempo: " + diferencaTempo + " nanossegundos)");
                        if (p == null) {
                            System.out.println("Conta não encontrada!\n");
                        } else {
                            System.out.println("\n**************************************");
                            System.out.println("Conta Encontrada!");
                            System.out.println("Conta Nº: " + p.getNumeroConta());
                            System.out.println("Dono: " + p.getNome());
                            System.out.println("Saldo: " + p.getSaldo() + " Reais\n");
                            System.out.println("**************************************");
                        }
                        break;
                    case 3:
                        System.out.print("\nInsira o numero de conta: ");
                        num = Integer.parseInt(ler());
                        reg = new Cliente(num);
                        tempoInicial = System.nanoTime();
                        arvore.retira(reg);
                        tempoFinal = System.nanoTime();
                        diferencaTempo = (tempoFinal - tempoInicial);
                        System.out.println("(Tempo de retirada: " + diferencaTempo + " nanossegundos)");
                        break;
                    case 4:
                        System.out.println("\nSaindo do Programa!");
                        break;
                    default:
                        System.out.println("Digite uma opção existente\n");
                }

            } while (x != 4);
        }

    }
}
