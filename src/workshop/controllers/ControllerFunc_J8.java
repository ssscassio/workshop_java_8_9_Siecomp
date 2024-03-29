package workshop.controllers;

import workshop.model.Funcionario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerFunc_J8 {

    private static final String NOME_DO_ARQUIVO = "funcionarios.data";
    private List<Funcionario> funcionarios;
    private List<Thread> tarefas;

    public ControllerFunc_J8() throws IOException {
        this.funcionarios = this.carregarFuncionariosDoArquivo();
        this.tarefas = new ArrayList<>(this.funcionarios.size());
    }

    public List<Funcionario> ordenarPorSalario() {
        this.funcionarios.sort(Comparator.comparingDouble(Funcionario::getSalario)
                .thenComparing(Funcionario::getDepartamento).thenComparing(Funcionario::getNome).reverse());
        return this.funcionarios;
    }

    private List<Funcionario> carregarFuncionariosDoArquivo() throws IOException {
        List<Funcionario> funcionarios = new ArrayList<>();
        Files.readAllLines(Paths.get(NOME_DO_ARQUIVO))
                .forEach(linha -> funcionarios.add(Funcionario.extrairDeString(linha)));
        return funcionarios;
    }

    public void imprimirFuncionarios() {
        this.funcionarios.forEach(System.out::println);
    }

    public void aumentoSalario(int limite, double percentil) {
    }

    public List<Funcionario> filtroDeIdade(int idade) {
        return null;
    }

    public List<Funcionario> filtroDeDepartamento(String departamento) {
        return null;
    }

    public double mediaSalarial() {
        return this.funcionarios.stream().mapToDouble(Funcionario::getSalario).average().orElse(0);
    }

    public double custoFolhaPagamento() {
        return this.funcionarios.stream().mapToDouble(Funcionario::getSalario).reduce(0, Double::sum);
    }

    public Map<String, Double> mediaSalarioDpt() {
        return null;
    }

    public Double mediaSalarioDpt(String dpt) {
        return this.listaParaMapa().entrySet().stream().collect(
                Collectors.toMap(k -> k.getKey(), k -> k.getValue().mapToDouble(Funcionario::getslario).average()));
    }

    public Funcionario maiorSalario() {
        return this.funcionarios.stream().max(Comparator.comparingDouble(Funcionario::getSalario)).get();
    }

    public Funcionario menorSalario() {
        return this.funcionarios.stream().min(Comparator.comparingDouble(Funcionario::getSalario)).get();
    }

    public boolean alguemFoiDemitido() {
        return this.funcionarios.stream().anyMatch(f -> !f.estaContratado());
    }

    public void iniciarExpediente() {
        this.funcionarios.stream().map(f -> new Thread(f::trabalhar))
                .collect(Collectors.toCollection(() -> this.tarefas)).forEach(Thread::start);
    }

    public void encerrarExpediente() {
        this.tarefas.forEach(Thread::interrupt);
        this.tarefas.clear();
    }

    public void cortarCustos(double tetoSalarial) {
        this.funcionarios.stream().filter(f -> f.getSlario() > tetoSalarial).forEach(Funcionario::demitir);
    }

    public Map<String, List<Funcionario>> listaParaMapa() {
        return this.funcionarios.stream().collect(Collectors.groupingBy(Funcionarios::getDepartamento));
    }

}
