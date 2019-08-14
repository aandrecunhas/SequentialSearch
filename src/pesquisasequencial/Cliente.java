package pesquisasequencial;

import java.io.*;

public class Cliente implements Item {

    private String nome;
    private int numeroConta; //chave
    private double saldo;
    // @{\it outros componentes do registro}@

    public Cliente(int chave) {
        this.numeroConta = chave;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public String getNome(){
        return nome;
    }

    public double getSaldo(){
        return saldo;
    }

    public int getNumeroConta(){
        return numeroConta;
    }

    public int compara(Item it) {
        Cliente item = (Cliente) it;
        if (this.numeroConta < item.numeroConta) {
            return -1;
        } else if (this.numeroConta > item.numeroConta) {
            return 1;
        }
        return 0;
    }

    public void alteraChave(Object chave) {
        Integer ch = (Integer) chave;
        this.numeroConta = ch.intValue();
    }

    public Object recuperaChave() {
        return new Integer(this.numeroConta);
    }

    public String toString() {
        return "" + this.numeroConta;
    }

    public void gravaArq(RandomAccessFile arq) throws IOException {
        arq.writeInt(this.numeroConta);
    }

    public void leArq(RandomAccessFile arq) throws IOException {
        this.numeroConta = arq.readInt();
    }

    public static int tamanho() {
        return 4; /* @{\it 4 bytes}@ */ }
}
