package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    Receita receita;
    String nome;
    String nom;
    Categoria cat;
    int quantidade;
    TipoRendimento tipoRendimento;
    List<Ingrediente> ingredientes;
    List<String> ModoPreparo;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
        this.receita = new Receita(nom, cat);
    }

    public void askNome() {
        System.out.println("Qual o nome da receita?");
        try {
            this.nome = scanner.nextLine();
            if (this.nome.isBlank()) {
                System.out.println("Nome inválido!");
                askNome();
            }
            receita.setNome(this.nome);
        } catch (Exception exception) {
            System.out.println("Nome inválido!");
            askNome();
        }
    }

    public void askCategoria() {
        System.out.println("Qual a categoria da receita?");
        try {
            for (Categoria cat : Categoria.values()) {
                System.out.printf("%d - %s %n", cat.ordinal(), cat.name());
            }
            int categoria = scanner.nextInt();
            if (categoria < 0 || categoria >= Categoria.values().length) {
                System.out.println("Categoria inválida!");
                askCategoria();
            }
            receita.setCategoria(Categoria.values()[categoria]);
        } catch (Exception exception) {
            System.out.println("Valor inválido!");
            askCategoria();
        }
    }

    public void askTempoPreparo() {
        System.out.println("Qual o tempo de preparo?");
        try {
            int tempoPreparo = scanner.nextInt();
            if (tempoPreparo <= 0) {
                System.out.println("Valor inválido!");
                askTempoPreparo();
            }
            receita.setTempoPreparo(tempoPreparo);
        } catch (Exception exception) {
            System.out.println("Tempo de preparo inválido!");
            askTempoPreparo();
        }
    }

    public void askModoPreparo() {
        ModoPreparo = new ArrayList<>();
        System.out.println("Qual o modo de preparo?");
        try {
            String modo = scanner.nextLine();

            if (modo.isBlank()) {
                System.out.println("Modo de preparo inválido!");
                askModoPreparo();
            }

            ModoPreparo.add(modo);

            receita.setModoPreparo(ModoPreparo.toArray(String[]::new));
        } catch (Exception exception) {
            System.out.println("Modo inválido!");
            askModoPreparo();
        }
    }

    public void askIngredientes() {
        ingredientes = new ArrayList<>();
        System.out.println("Quantos ingredientes tem a receita?");
        try {
            int quantidadeIngredientes = scanner.nextInt();

            for (int i = 0; i < quantidadeIngredientes; i++) {
                System.out.println("Qual o nome do ingrediente?");
                String nome = scanner.next();

                if (nome.isBlank()) {
                    System.out.println("Ingrediente inválido!");
                    askIngredientes();
                }

                System.out.println("Qual o tipo de medida desse ingrediente?");

                for (TipoMedida tipo : TipoMedida.values()) {
                    System.out.printf("%d - %s %n", tipo.ordinal(), tipo.name());
                }

                int valorTipoMedida = scanner.nextInt();

                if (valorTipoMedida < 0 || valorTipoMedida >= TipoMedida.values().length) {
                    System.out.println("Tipo inválido!");
                    askIngredientes();
                }

                System.out.println("Qual a quantidade desse ingrediente?");
                double quantidade = scanner.nextDouble();

                if (quantidade < 0) {
                    System.out.println("Valor inválido!");
                    askIngredientes();
                }

                Ingrediente ingrediente = new Ingrediente(nome, quantidade, TipoMedida.values()[valorTipoMedida]);
                ingredientes.add(ingrediente);
            }

            receita.setIngredientes(ingredientes.toArray(Ingrediente[]::new));
        } catch (Exception exception) {
            System.out.println("Ingrediente inválido!");
            askIngredientes();
        }
    }

    public void askRendimento() {
        Rendimento rendimentoReceita = new Rendimento(quantidade, tipoRendimento);
        System.out.println("Qual o tipo do rendimento?");
        try {
            for (TipoRendimento tipo : TipoRendimento.values()) {
                System.out.printf("%d - %s %n", tipo.ordinal(), tipo.name());
            }
            int tipoRend = scanner.nextInt();

            if (tipoRend < 0 || tipoRend >= TipoRendimento.values().length) {
                System.out.println("Tipo de rendimento inválido!");
                askRendimento();
            }

            System.out.println("Qual o rendimento da receita?");
            quantidade = scanner.nextInt();

            this.tipoRendimento = tipoRendimento.values()[tipoRend];

            receita.setRendimento(rendimentoReceita);
        } catch (Exception exception) {
            System.out.println("Rendimento inválido!");
            askRendimento();
        }
    }
}
